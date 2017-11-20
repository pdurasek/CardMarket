package CardMarket.controllers;

import CardMarket.models.Card;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

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

      final TreeItem<CardOfferRecord> root = new RecursiveTreeItem<>(cardOfferList, RecursiveTreeObject::getChildren);
      JFXTreeTableView<CardOfferRecord> offerList = new JFXTreeTableView<>(root);
      offerList.setOnMouseClicked(event -> System.out.println("Card " + offerList.getSelectionModel().getSelectedItem().getValue().cardname + " with ID: " +
              offerList.getSelectionModel().getSelectedItem().getValue().id));
      offerList.setShowRoot(false);
      offerList.setEditable(false);
      offerList.getColumns().setAll(cardsetColumn, cardColumn, rarityColumn, conditionColumn, languageColumn, priceColumn, quantityColumn);
      tablePane.setCenter(offerList);
   }

   private ObservableList<CardOfferRecord> generateOffers()
   {
      ObservableList<CardOfferRecord> cardOfferList = FXCollections.observableArrayList();

      for (int i = 0; i < 50; i++)
      {
         cardOfferList.add(new CardOfferRecord(i, card.getCardset().getName(), card.getName()+i, card.getRarity().getName(),
                 card.getCondition().getName(), card.getLanguage().getName(), 50.0, 11));
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

      public CardOfferRecord(int id, String cardset, String cardname, String rarity, String condition, String language,
                             double price, int quantity)
      {
         this.id = new SimpleIntegerProperty(id);
         this.cardset = new SimpleStringProperty(cardset);
         this.cardname = new SimpleStringProperty(cardname);
         this.rarity = new SimpleStringProperty(rarity);
         this.condition = new SimpleStringProperty(condition);
         this.language = new SimpleStringProperty(language);
         this.price = new SimpleDoubleProperty(price);
         this.quantity = new SimpleIntegerProperty(quantity);
      }
   }
}
