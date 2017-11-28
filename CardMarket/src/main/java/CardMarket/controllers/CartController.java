package CardMarket.controllers;

import CardMarket.dao.implementations.CardDao;
import CardMarket.dao.implementations.CardOfferDao;
import CardMarket.dao.implementations.ReservedCardDao;
import CardMarket.dao.interfaces.ICardDao;
import CardMarket.dao.interfaces.ICardOfferDao;
import CardMarket.dao.interfaces.IReservedCardDao;
import CardMarket.models.Card;
import CardMarket.models.CardOffer;
import CardMarket.models.ReservedCard;
import CardMarket.util.TreeTableViewRecord;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartController
{
   @FXML
   BorderPane tablePane;

   private IReservedCardDao reservedCardDao = new ReservedCardDao();
   private ICardOfferDao cardOfferDao = new CardOfferDao();

   @FXML
   private void initialize()
   {

   }

   public void updateCartList()
   {
      ObservableList<TreeTableViewRecord> cartList = generateCartItems();

      // TODO refactor this copypasta from UniqueCardController
      JFXTreeTableColumn<TreeTableViewRecord, String> idColumn = new JFXTreeTableColumn<>("ID");
      idColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<TreeTableViewRecord, String> col) -> col.getValue().getValue().cardset);

      JFXTreeTableColumn<TreeTableViewRecord, String> cardsetColumn = new JFXTreeTableColumn<>("Set");
      cardsetColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<TreeTableViewRecord, String> col) -> col.getValue().getValue().cardset);

      JFXTreeTableColumn<TreeTableViewRecord, String> cardColumn = new JFXTreeTableColumn<>("Card");
      cardColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<TreeTableViewRecord, String> col) -> col.getValue().getValue().cardname);

      JFXTreeTableColumn<TreeTableViewRecord, String> rarityColumn = new JFXTreeTableColumn<>("Rarity");
      rarityColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<TreeTableViewRecord, String> col) -> col.getValue().getValue().rarity);

      JFXTreeTableColumn<TreeTableViewRecord, String> conditionColumn = new JFXTreeTableColumn<>("Condition");
      conditionColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<TreeTableViewRecord, String> col) -> col.getValue().getValue().condition);

      JFXTreeTableColumn<TreeTableViewRecord, String> languageColumn = new JFXTreeTableColumn<>("Language");
      languageColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<TreeTableViewRecord, String> col) -> col.getValue().getValue().language);

      JFXTreeTableColumn<TreeTableViewRecord, Number> priceColumn = new JFXTreeTableColumn<>("Price");
      priceColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<TreeTableViewRecord, Number> col) -> col.getValue().getValue().price);

      JFXTreeTableColumn<TreeTableViewRecord, Number> quantityColumn = new JFXTreeTableColumn<>("Quantity");
      quantityColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<TreeTableViewRecord, Number> col) -> col.getValue().getValue().quantity);

      JFXTreeTableColumn<TreeTableViewRecord, String> sellerColumn = new JFXTreeTableColumn<>("Seller");
      sellerColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<TreeTableViewRecord, String> col) -> col.getValue().getValue().seller);

      final TreeItem<TreeTableViewRecord> root = new RecursiveTreeItem<>(cartList, RecursiveTreeObject::getChildren);
      JFXTreeTableView<TreeTableViewRecord> cartTableList = new JFXTreeTableView<>(root);
      cartTableList.setOnMouseClicked(event ->
      {
         if (cartList.size() > 0)
         {
            TreeTableViewRecord aRecord = cartTableList.getSelectionModel().getSelectedItem().getValue();
            int quantity = aRecord.quantity.getValue();
            List<Integer> quantityList = new ArrayList<>(quantity);
            for (int i = 1; i <= quantity; i++)
            {
               quantityList.add(i);
            }
            ChoiceDialog removeFromCartDialog = new ChoiceDialog(quantityList.get(0), quantityList);
            removeFromCartDialog.setTitle("Remove from Cart");
            removeFromCartDialog.setHeaderText("Select quantity");
            Optional<Integer> result = removeFromCartDialog.showAndWait();
            if (result.isPresent())
            {
               int quantityRemaining = quantity - result.get();

               if (quantityRemaining >= 0)
               {
                  System.out.println(aRecord.id.getValue());

                  ReservedCard reservedCard = reservedCardDao.getReservedCard(aRecord.id.getValue());
                  reservedCard.setQuantity(reservedCard.getQuantity() - result.get());

                  if (reservedCardDao.updateReservedCard(reservedCard))
                  {
                     aRecord.quantity.setValue(quantityRemaining);
                     CardOffer cardOffer = cardOfferDao.getCardOffer(reservedCard.getCardOffer().getCardOfferID());

                     if (cardOffer != null)
                     {
                        cardOffer.setQuantity(cardOffer.getQuantity() + result.get());
                        cardOfferDao.updateCardOffer(cardOffer);
                     }
                     else
                     {
                        System.err.println("Unable to find offer card to update");
                     }
                  }

                  if (quantityRemaining <= 0)
                  {
                     TreeItem selectedItem = cartTableList.getSelectionModel().getSelectedItem();
                     selectedItem.getParent().getChildren().remove(selectedItem);
                     reservedCardDao.deleteReservedCard(reservedCard);
                  }
               }
            }
         }
      });

      cartTableList.setShowRoot(false);
      cartTableList.setEditable(false);
      cartTableList.getColumns().setAll(cardsetColumn, cardColumn, rarityColumn, conditionColumn, languageColumn, priceColumn, quantityColumn, sellerColumn);
      tablePane.setCenter(cartTableList);
   }

   private ObservableList<TreeTableViewRecord> generateCartItems()
   {
      ObservableList<TreeTableViewRecord> cartList = FXCollections.observableArrayList();
      List<ReservedCard> tempCartList = reservedCardDao.getAllReservedCards(); // TODO use method with use

      for (ReservedCard reservedCard : tempCartList)
      {
         Card card = reservedCard.getCardOffer().getCard();

         cartList.add(new TreeTableViewRecord(reservedCard.getReservedCardID(),
                 card.getCardset().getName(),
                 card.getName(),
                 card.getRarity().getName(),
                 card.getCondition().getName(),
                 card.getLanguage().getName(),
                 reservedCard.getCardOffer().getPrice(),
                 reservedCard.getQuantity(),
                 reservedCard.getUser().getUsername()));
      }

      return cartList;
   }
}
