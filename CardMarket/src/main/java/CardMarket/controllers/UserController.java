package CardMarket.controllers;

import CardMarket.Market;
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
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class UserController
{
   @FXML
   private Label totalCardsBoughtLabel, totalCardsSoldLabel, userRatingLabel, countryLabel, usernameLabel;
   @FXML
   private TextField streetAddress, city, zip;
   @FXML
   BorderPane onSalePane, soldPane, boughtPane;
   @FXML
   JFXButton saveTemplateButton, newCardButton;
   @FXML
   ComboBox<Country> countryComboBox;
   @FXML
   GridPane rootPane;

   private ICardOfferDao cardOfferDao = new CardOfferDao();
   private String username;
   private Market market;
   private Stage stage;

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

      newCardButton.setOnAction(event ->
      {
         market.showAddCardOffer();
         stage.close();
      });

      countryComboBox.getItems().addAll(countryDao.getAllCountries());
      countryComboBox.getSelectionModel().selectFirst();

      if (userTemplate != null)
      {
         countryLabel.setText(user.getShippingAddressTemplate().getCountry().getName());
         city.setText(userTemplate.getCity());
         zip.setText(userTemplate.getZipcode());
         streetAddress.setText(userTemplate.getAddress());
      }
      else
      {
         countryLabel.setText("N/A");
      }

      totalCardsBoughtLabel.setText("N/A"); // TODO implement with user stats
      totalCardsSoldLabel.setText("N/A");

      saveTemplateButton.setOnAction(event ->
      {
         ShippingAddressTemplate currentTemplate = user.getShippingAddressTemplate();
         if (currentTemplate != null)
         {
            if (streetAddress.getText().length() > 0 && zip.getText().length() > 0 && city.getText().length() > 0)
            {
               currentTemplate.setAddress(streetAddress.getText());
               currentTemplate.setCity(city.getText());
               currentTemplate.setZipcode(zip.getText());
               currentTemplate.setCountry(countryComboBox.getSelectionModel().getSelectedItem());
               if (shippingAddressTemplateDao.updateShippingAddressTemplate(currentTemplate))
               {
                  JFXSnackbar bar = new JFXSnackbar(rootPane);
                  bar.enqueue(new JFXSnackbar.SnackbarEvent("Address Template successfully updated"));
                  countryLabel.setText(currentTemplate.getCountry().getName());
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
         else
         {
            if (streetAddress.getText().length() > 0 && zip.getText().length() > 0 && city.getText().length() > 0)
            {
               ShippingAddressTemplate newTemplate = new ShippingAddressTemplate();
               newTemplate.setAddress(streetAddress.getText());
               newTemplate.setCity(city.getText());
               newTemplate.setZipcode(zip.getText());
               newTemplate.setCountry(countryComboBox.getSelectionModel().getSelectedItem());
               if (shippingAddressTemplateDao.createShippingAddressTemplate(newTemplate))
               {
                  user.setShippingAddressTemplate(newTemplate);
                  JFXSnackbar bar = new JFXSnackbar(rootPane);
                  bar.enqueue(new JFXSnackbar.SnackbarEvent("Address Template successfully created"));
                  countryLabel.setText(newTemplate.getCountry().getName());
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
         if (cartTableList.getSelectionModel().getSelectedItem() != null && cartList.size() > 0)
         {
            TreeTableViewRecord currentOffer = cartTableList.getSelectionModel().getSelectedItem().getValue();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Card Offer");
            alert.setHeaderText("Delete " + currentOffer.cardname.get() + " from offer list");
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
            {
               CardOffer cardOffer = cardOfferDao.getCardOffer(currentOffer.id.getValue().intValue());

               if(!cardOfferDao.deleteCardOffer(cardOffer))
               {
                  Alert alertError = new Alert(Alert.AlertType.ERROR);
                  alertError.setTitle("Error");
                  alertError.setHeaderText("Unable to delete card offer");
                  alertError.setContentText("Card is reserved!");

                  alertError.showAndWait();
               }
               else
               {
                  TreeItem selectedItem = cartTableList.getSelectionModel().getSelectedItem();
                  selectedItem.getParent().getChildren().remove(selectedItem);
                  cartTableList.getSelectionModel().clearSelection();
               }
            }
         }
      });

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

         cartList.add(new TreeTableViewRecord(cardOffer.getCardOfferID(),
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

   public void setMarket(Market market)
   {
      this.market = market;
   }

   public void setStage(Stage stage)
   {
      this.stage = stage;
   }
}
