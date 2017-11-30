package CardMarket.controllers;

import CardMarket.dao.UserCreator;
import CardMarket.dao.implementations.CardOfferDao;
import CardMarket.dao.implementations.CountryDao;
import CardMarket.dao.implementations.ShippingAddressTemplateDao;
import CardMarket.dao.interfaces.ICardOfferDao;
import CardMarket.dao.interfaces.ICountryDao;
import CardMarket.dao.interfaces.IShippingAddressTemplateDao;
import CardMarket.models.*;
import CardMarket.util.TreeTableViewRecord;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.List;

public class UserController
{
   @FXML
   private Label totalCardsBoughtLabel, totalCardsSoldLabel, userRatingLabel, countryLabel, usernameLabel;
   @FXML
   private TextField streetAddress, city, zip;
   @FXML
   BorderPane onSalePane, soldPane, boughtPane;
   @FXML
   JFXButton saveTemplateButton;
   @FXML
   ComboBox<Country> countryComboBox;
   @FXML
   GridPane rootPane;

   private ICardOfferDao cardOfferDao = new CardOfferDao();
   private String username;

   @FXML
   private void initialize()
   {
      User user = UserCreator.getLoggedUser();
      ShippingAddressTemplate userTemplate = user.getShippingAddressTemplate();
      IShippingAddressTemplateDao shippingAddressTemplateDao = new ShippingAddressTemplateDao();
      ICountryDao countryDao = new CountryDao();

      username = user.getUsername();
      usernameLabel.setText(username);
      userRatingLabel.setText(user.getCredibility().getName());
      if (userTemplate != null)
      {
         countryLabel.setText(user.getShippingAddressTemplate().getCountry().getName());
         city.setText(userTemplate.getCity());
         zip.setText(userTemplate.getZipcode());
         streetAddress.setText(userTemplate.getAddress());
         countryComboBox.getItems().addAll(countryDao.getAllCountries());
         countryComboBox.getSelectionModel().selectFirst(); // TODO select user country
      }
      else
      {
         countryLabel.setText("N/A");
      }

      totalCardsBoughtLabel.setText(Integer.toString(user.getCardsBought()));
      totalCardsSoldLabel.setText(Integer.toString(user.getCardsSold()));

      saveTemplateButton.setOnAction(event ->
      {
         if(userTemplate != null)
         {
            if(streetAddress.getText().length() > 0 && zip.getText().length() > 0 && city.getText().length() > 0)
            {
               userTemplate.setAddress(streetAddress.getText());
               userTemplate.setCity(city.getText());
               userTemplate.setZipcode(zip.getText());
               userTemplate.setCountry(countryComboBox.getSelectionModel().getSelectedItem());
               if(shippingAddressTemplateDao.updateShippingAddressTemplate(userTemplate))
               {
                  JFXSnackbar bar = new JFXSnackbar(rootPane);
                  bar.enqueue(new JFXSnackbar.SnackbarEvent("Address Template successfully updated"));
                  countryLabel.setText(userTemplate.getCountry().getName());
               }
            }
            else
            {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Template Error");
               alert.setContentText("All fields must be filled");
               alert.showAndWait();
            }
         }
      });
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
      List<CardOffer> tempCartList = cardOfferDao.getAllUserCardOffers(username);

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
