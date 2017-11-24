package CardMarket.controllers;

import CardMarket.dao.implementations.CardOfferDao;
import CardMarket.dao.interfaces.ICardOfferDao;
import CardMarket.models.Card;
import CardMarket.models.CardOffer;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
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
      averageCost.setText("0");
   }

   public void updateOfferList()
   {

      ObservableList<CardOfferRecord> cardOfferList = generateOffers();

      JFXTreeTableColumn<CardOfferRecord, String> idColumn = new JFXTreeTableColumn<>("ID");
      idColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<CardOfferRecord, String> col) -> col.getValue().getValue().cardset);

      JFXTreeTableColumn<CardOfferRecord, String> cardsetColumn = new JFXTreeTableColumn<>("Set");
      cardsetColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<CardOfferRecord, String> col) -> col.getValue().getValue().cardset);

      JFXTreeTableColumn<CardOfferRecord, String> cardColumn = new JFXTreeTableColumn<>("Card");
      cardColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<CardOfferRecord, String> col) -> col.getValue().getValue().cardname);

      JFXTreeTableColumn<CardOfferRecord, String> rarityColumn = new JFXTreeTableColumn<>("Rarity");
      rarityColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<CardOfferRecord, String> col) -> col.getValue().getValue().rarity);

      JFXTreeTableColumn<CardOfferRecord, String> conditionColumn = new JFXTreeTableColumn<>("Condition");
      conditionColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<CardOfferRecord, String> col) -> col.getValue().getValue().condition);

      JFXTreeTableColumn<CardOfferRecord, String> languageColumn = new JFXTreeTableColumn<>("Language");
      languageColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<CardOfferRecord, String> col) -> col.getValue().getValue().language);

      JFXTreeTableColumn<CardOfferRecord, Number> priceColumn = new JFXTreeTableColumn<>("Price");
      priceColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<CardOfferRecord, Number> col) -> col.getValue().getValue().price);

      JFXTreeTableColumn<CardOfferRecord, Number> quantityColumn = new JFXTreeTableColumn<>("Quantity");
      quantityColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<CardOfferRecord, Number> col) -> col.getValue().getValue().quantity);

      JFXTreeTableColumn<CardOfferRecord, String> sellerColumn = new JFXTreeTableColumn<>("Seller");
      sellerColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<CardOfferRecord, String> col) -> col.getValue().getValue().seller);

      final TreeItem<CardOfferRecord> root = new RecursiveTreeItem<>(cardOfferList, RecursiveTreeObject::getChildren);
      JFXTreeTableView<CardOfferRecord> offerList = new JFXTreeTableView<>(root);
      offerList.setOnMouseClicked(event ->
      {
         if (cardOfferList.size() > 0)
         {
            CardOfferRecord aRecord = offerList.getSelectionModel().getSelectedItem().getValue();
            int quantity = aRecord.quantity.getValue();
            List<Integer> quantityList = new ArrayList<>(quantity);
            for (int i = 1; i <= quantity; i++)
            {
               quantityList.add(i);
            }
            ChoiceDialog addToCardDialog = new ChoiceDialog(quantityList.get(0), quantityList);
            addToCardDialog.setTitle("Add to Cart");
            addToCardDialog.setHeaderText("Select quantity");
            Optional<Integer> result = addToCardDialog.showAndWait();
            if (result.isPresent())
            {
               int quantityRemaining = quantity - result.get();

               if (quantityRemaining > 0)
               {
                  System.out.println(result.get());
                  CardOffer offer = cardOfferDao.getCardOffer(aRecord.id.getValue());
                  offer.setQuantity(offer.getQuantity() - result.get());
                  if (cardOfferDao.updateCardOffer(offer))
                  {
                     aRecord.quantity.setValue(quantityRemaining);
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

   private ObservableList<CardOfferRecord> generateOffers() // TODO add pagination on loading?
   {
      ObservableList<CardOfferRecord> cardOfferList = FXCollections.observableArrayList();
      List<CardOffer> tempOfferList = cardOfferDao.getAllCardOffers(card.getName());
      for (CardOffer offer : tempOfferList)
      {
         cardOfferList.add(new CardOfferRecord(offer.getCardOfferID(), offer.getCard().getCardset().getName(), offer.getCard().getName(),
                 offer.getCard().getRarity().getName(), offer.getCard().getCondition().getName(), offer.getCard().getLanguage().getName(),
                 offer.getPrice(), offer.getQuantity(), offer.getUser().getUsername()));
      }


      return cardOfferList;
   }

   private static final class CardOfferRecord extends RecursiveTreeObject<CardOfferRecord>
   {
      final IntegerProperty id;
      final StringProperty cardset;
      final StringProperty cardname;
      final StringProperty rarity;
      final StringProperty condition;
      final StringProperty language;
      final DoubleProperty price;
      final IntegerProperty quantity;
      final StringProperty seller;

      public CardOfferRecord(int id, String cardset, String cardname, String rarity, String condition, String language,
                             double price, int quantity, String seller)
      {
         this.id = new SimpleIntegerProperty(id);
         this.cardset = new SimpleStringProperty(cardset);
         this.cardname = new SimpleStringProperty(cardname);
         this.rarity = new SimpleStringProperty(rarity);
         this.condition = new SimpleStringProperty(condition);
         this.language = new SimpleStringProperty(language);
         this.price = new SimpleDoubleProperty(price);
         this.quantity = new SimpleIntegerProperty(quantity);
         this.seller = new SimpleStringProperty(seller);
      }
   }
}
