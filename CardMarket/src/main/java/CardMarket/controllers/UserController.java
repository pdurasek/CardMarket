package CardMarket.controllers;

import CardMarket.dao.UserCreator;
import CardMarket.dao.implementations.CardOfferDao;
import CardMarket.dao.interfaces.ICardOfferDao;
import CardMarket.models.Card;
import CardMarket.models.CardOffer;
import CardMarket.util.TreeTableViewRecord;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.util.List;

public class UserController
{
   @FXML
   private Label totalCardsBoughtLabel, totalCardsSoldLabel, userRatingLabel, countryLabel, usernameLabel;
   @FXML
   private TextField streetAddress, city, country, zip;
   @FXML
   private ComboBox templateComboBox;
   @FXML
   BorderPane onSalePane, soldPane, boughtPane;

   private ICardOfferDao cardOfferDao = new CardOfferDao();
   private String username;

   @FXML
   private void initialize()
   {
      username = UserCreator.getLoggedUser().getUsername();
      usernameLabel.setText(username);
   }

   public void createCardList()
   {
      ObservableList<TreeTableViewRecord> cartList = generateOnSale();

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

      cartTableList.setShowRoot(false);
      cartTableList.setEditable(false);
      cartTableList.getColumns().setAll(cardsetColumn, cardColumn, rarityColumn, conditionColumn, languageColumn, priceColumn, quantityColumn, sellerColumn);
      onSalePane.setCenter(cartTableList);
   }

   private ObservableList<TreeTableViewRecord> generateOnSale()
   {
      ObservableList<TreeTableViewRecord> cartList = FXCollections.observableArrayList();
      List<CardOffer> tempCartList = cardOfferDao.getAllUserCardOffers(username); // TODO use method with user

      for (CardOffer cardOffer : tempCartList)
      {
         Card card = cardOffer.getCard();

         cartList.add(new TreeTableViewRecord(card.getCardID(),
                 card.getCardset().getName(),
                 card.getName(),
                 card.getRarity().getName(),
                 card.getCondition().getName(),
                 card.getLanguage().getName(),
                 cardOffer.getPrice(),
                 cardOffer.getQuantity(),
                 cardOffer.getUser().getUsername()));
      }

      return cartList;
   }

   public void setUser(String username)
   {
      this.username = username;
   }
}
