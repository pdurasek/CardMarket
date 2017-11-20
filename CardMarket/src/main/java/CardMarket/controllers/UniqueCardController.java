package CardMarket.controllers;

import CardMarket.dao.implementations.CardDao;
import CardMarket.dao.interfaces.ICardDao;
import CardMarket.models.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
   private JFXTreeTableView offerList;

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
      ICardDao cardDao = new CardDao();

      ObservableList<Cardset> cardsets = FXCollections.observableArrayList();
      cardsets.add(new Cardset(1, "random set1", "rs1"));
      cardsets.add(new Cardset(2, "random set2", "rs2"));
      cardsets.add(new Cardset(3, "random set3", "rs3"));

      //TreeItem<Cardset> cardsetTreeItem = new RecursiveTreeItem<>(cardsets, RecursiveTreeObject::getChildren);

      JFXTreeTableColumn<Cardset, String> cardsetColumn = new JFXTreeTableColumn<>("Set");
      JFXTreeTableColumn<Card, String> cardColumn = new JFXTreeTableColumn<>("Card");
      JFXTreeTableColumn<Rarity, String> rarityColumn = new JFXTreeTableColumn<>("Rarity");
      JFXTreeTableColumn<Condition, String> conditionColumn = new JFXTreeTableColumn<>("Condition");
      JFXTreeTableColumn<Language, String> languageColumn = new JFXTreeTableColumn<>("Language");
      JFXTreeTableColumn<JFXButton, String> buttonColumn = new JFXTreeTableColumn<>("");
      offerList.getColumns().setAll(cardsetColumn, cardColumn, rarityColumn, conditionColumn, languageColumn, buttonColumn);
      //cardColumn.setUserData();
   }
}
