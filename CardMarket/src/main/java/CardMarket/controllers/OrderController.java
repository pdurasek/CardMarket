package CardMarket.controllers;

import CardMarket.Market;
import CardMarket.dao.UserCreator;
import CardMarket.dao.implementations.*;
import CardMarket.dao.interfaces.*;
import CardMarket.models.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

public class OrderController
{
   @FXML
   private JFXButton confirmOrder, cancelOrder, populateAddress;
   @FXML
   private Label totalPrice, numOfItems;
   @FXML
   private ComboBox<PaymentMethod> paymentMethodComboBox;
   @FXML
   private ComboBox<Country> countryComboBox;
   @FXML
   private TextField streetAddress, city, zip;
   @FXML
   private BorderPane basePane;

   private IPaymentMethodDao paymentMethodDao = new PaymentMethodDao();
   private IReservedCardDao reservedCardDao = new ReservedCardDao();
   private ICountryDao countryDao = new CountryDao();
   private ISoldCardDao soldCardDao = new SoldCardDao();
   private IOrderDao orderDao = new OrderDao();
   private IShippingAddressDao shippingAddressDao = new ShippingAddressDao();
   private IShippingAddressTemplateDao shippingAddressTemplateDao = new ShippingAddressTemplateDao();
   private User user = UserCreator.getLoggedUser();
   private Market market;
   private Stage modalStage;

   @FXML
   private void initialize()
   {
      paymentMethodComboBox.getItems().addAll(paymentMethodDao.getAllPaymentMethods());
      totalPrice.setText(Double.toString(reservedCardDao.getTotalPrice(user)));
      numOfItems.setText(Integer.toString(reservedCardDao.getNumberOfItems(user)));
      countryComboBox.getItems().addAll(countryDao.getAllCountries());
      countryComboBox.getSelectionModel().selectFirst();
      if (user.getShippingAddressTemplate() == null)
      {
         populateAddress.setDisable(true);
      }
      else
      {
         populateAddress.setDisable(false);
      }
      populateAddress.setOnAction(event ->
      {
         ShippingAddressTemplate shippingAddressTemplate = shippingAddressTemplateDao.getShippingAddressTemplate(user.getShippingAddressTemplate().getAddressID());
         streetAddress.setText(shippingAddressTemplate.getAddress());
         city.setText(shippingAddressTemplate.getCity());
         zip.setText(shippingAddressTemplate.getZipcode());
      });

      cancelOrder.setOnAction(event ->
      {
         market.showCart();
         modalStage.close();
      });

      confirmOrder.setOnAction(event ->
      {
         List<ReservedCard> reservedCardList = reservedCardDao.getAllReservedCards(user);
         if (streetAddress.getText().length() > 0 && zip.getText().length() > 0 && city.getText().length() > 0)
         {
            ShippingAddress shippingAddress = new ShippingAddress(streetAddress.getText(), zip.getText(), city.getText(), countryComboBox.getSelectionModel().getSelectedItem());
            if (shippingAddressDao.createShippingAddress(shippingAddress))
            {
               CardOrder order = new CardOrder("Test Details", user, shippingAddress, paymentMethodComboBox.getSelectionModel().getSelectedItem());
               if (orderDao.createOrder(order))
               {
                  for (ReservedCard aReservedCard : reservedCardList)
                  {
                     CardOffer currentOffer = aReservedCard.getCardOffer();
                     if(soldCardDao.createSoldCard(new SoldCard(aReservedCard.getQuantity(), currentOffer.getPrice(), currentOffer.getCard(), user, currentOffer.getUser(), order)))
                     {
                        reservedCardDao.deleteReservedCard(aReservedCard);
                        JFXSnackbar bar = new JFXSnackbar(basePane);
                        bar.enqueue(new JFXSnackbar.SnackbarEvent("Cards ordered successfully"));
                     }
                  }
               }
            }
         }
         else
         {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Address Error");
            alert.setContentText("All fields must be filled");
            alert.showAndWait();
         }
      });
   }

   public void setMarket(Market market)
   {
      this.market = market;
   }

   public void setModalStage(Stage modalStage)
   {
      this.modalStage = modalStage;
   }
}
