package CardMarket.controllers;

import CardMarket.dao.SessionCreator;
import CardMarket.dao.UserCreator;
import CardMarket.dao.implementations.CardOfferDao;
import CardMarket.dao.implementations.ReservedCardDao;
import CardMarket.dao.interfaces.ICardOfferDao;
import CardMarket.dao.interfaces.IReservedCardDao;
import CardMarket.models.Card;
import CardMarket.models.CardOffer;
import CardMarket.models.ReservedCard;
import CardMarket.models.User;
import CardMarket.util.TreeTableViewRecord;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UniqueCardController
{
   @FXML
   private JFXTextArea description;
   @FXML
   private Label bigName;
   @FXML
   private Label name;
   @FXML
   private Label type;
   @FXML
   private Label subType;
   @FXML
   private Label averageCost;
   @FXML
   private ImageView imageView;
   @FXML
   private BorderPane tablePane;

   private Card card;
   private ICardOfferDao cardOfferDao = new CardOfferDao();
   private IReservedCardDao reservedCardDao = new ReservedCardDao();

   @FXML
   private void initialize()
   {
      description.getStyleClass().add("jfx-text-area");
   }

   public void setUniqueCard(Card card)
   {
      this.card = card;
   }

   public void updateCardInfo()
   {
      bigName.setText(card.getName());
      imageView.setImage(new Image("images/" + card.getImageUrl()));
      name.setText(card.getName());
      description.setText(card.getDescription());
      type.setText(card.getType().getName());
      subType.setText(card.getSubtype().getName());
      averageCost.setText(Double.toString(cardOfferDao.getAverageCost(card.getName()))); // TODO Calculate
   }

   public void updateOfferList()
   {
      ObservableList<TreeTableViewRecord> cardOfferList = generateOffers();

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

      final TreeItem<TreeTableViewRecord> root = new RecursiveTreeItem<>(cardOfferList, RecursiveTreeObject::getChildren);
      JFXTreeTableView<TreeTableViewRecord> offerList = new JFXTreeTableView<>(root);
      offerList.setOnMouseClicked(event ->
      {
         if (offerList.getSelectionModel().getSelectedItem() != null && cardOfferList.size() > 0)
         {
            TreeTableViewRecord aRecord = offerList.getSelectionModel().getSelectedItem().getValue();
            int quantity = aRecord.quantity.getValue();
            List<Integer> quantityList = new ArrayList<>(quantity);
            for (int i = 1; i <= quantity; i++)
            {
               quantityList.add(i);
            }
            ChoiceDialog addToCardDialog = new ChoiceDialog(quantityList.get(0), quantityList);
            addToCardDialog.setTitle("Add to ReservedCard");
            addToCardDialog.setHeaderText("Select quantity");
            Optional<Integer> result = addToCardDialog.showAndWait();
            if (result.isPresent())
            {
               int quantityRemaining = quantity - result.get();

               if (quantityRemaining >= 0)
               {
                  System.out.println(aRecord.id.getValue());
                  CardOffer offer = cardOfferDao.getCardOffer(aRecord.id.getValue());
                  offer.setQuantity(offer.getQuantity() - result.get());
                  if (cardOfferDao.updateCardOffer(offer))
                  {
                     aRecord.quantity.setValue(quantityRemaining);
                     User user = UserCreator.getLoggedUser();
                     ReservedCard reservedCard = reservedCardDao.getReservedCard(offer.getCardOfferID(), user.getUserID());
                     if (reservedCard != null)
                     {
                        reservedCard.setQuantity(reservedCard.getQuantity() + result.get());
                        reservedCardDao.updateReservedCard(reservedCard);
                     }
                     else
                     {
                        ReservedCard rs = new ReservedCard();
                        rs.setCardOffer(offer);
                        rs.setUser(user);
                        rs.setQuantity(result.get());
                        ReservedCard newReservedCard = reservedCardDao.createReservedCard(rs);
                     }

                     JFXSnackbar bar = new JFXSnackbar(tablePane);
                     bar.enqueue(new JFXSnackbar.SnackbarEvent("Added to Cart!"));
                  }
                  else
                  {
                     System.out.println("dickerino");
                  }
               }
            }
         }
      });

      offerList.setShowRoot(false);
      offerList.setEditable(false);
      offerList.getColumns().setAll(cardsetColumn, cardColumn, rarityColumn, conditionColumn, languageColumn, priceColumn, quantityColumn, sellerColumn);
      tablePane.setCenter(offerList);
   }

   private ObservableList<TreeTableViewRecord> generateOffers()
   {
      ObservableList<TreeTableViewRecord> cardOfferList = FXCollections.observableArrayList();
      List<CardOffer> tempOfferList = cardOfferDao.getAllCardOffers(card.getName());
      for (CardOffer offer : tempOfferList)
      {
         cardOfferList.add(new TreeTableViewRecord(offer.getCardOfferID(), offer.getCard().getCardset().getName(), offer.getCard().getName(),
                 offer.getCard().getRarity().getName(), offer.getCard().getCondition().getName(), offer.getCard().getLanguage().getName(),
                 offer.getPrice(), offer.getQuantity(), offer.getUser().getUsername()));
      }

      return cardOfferList;
   }
}
