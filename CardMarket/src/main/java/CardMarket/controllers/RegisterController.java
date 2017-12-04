package CardMarket.controllers;

import CardMarket.Market;
import CardMarket.dao.UserCreator;
import CardMarket.dao.implementations.UserDao;
import CardMarket.dao.interfaces.IUserDao;
import CardMarket.models.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogEvent;
import javafx.scene.layout.AnchorPane;
import org.apache.commons.codec.digest.DigestUtils;

public class RegisterController
{
   @FXML
   private JFXTextField registerNameField, registerEmailField, loginNameField;
   @FXML
   private JFXPasswordField registerPasswordField, loginPasswordField;
   @FXML
   private JFXButton registerButton, loginButton;
   @FXML
   private AnchorPane rootPane;

   private IUserDao userDao = new UserDao();
   private Market market;

   @FXML
   private void initialize()
   {
      registerButton.setOnAction(event ->
      {
         String registerName = registerNameField.getText();
         String registerPassword = registerPasswordField.getText();
         String registerEmail = registerEmailField.getText();

         if (registerName.length() >= 3 && registerPassword.length() >= 5 && registerEmail.length() >= 7)
         {
            User newUser = new User(registerName, DigestUtils.sha256Hex(registerPassword), registerEmail);

            if (userDao.createUser(newUser))
            {
               market.showBrowse();
            }
         }
         else
         {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration Error");
            alert.setContentText("Incorrect registration information");
            alert.showAndWait();
         }
      });

      loginButton.setOnAction(event ->
      {
         String loginName = loginNameField.getText();
         String loginPassword = loginPasswordField.getText();

         User loggedUser = UserCreator.getLoggedUser(loginName, DigestUtils.sha256Hex(loginPassword));

         if (loggedUser != null)
         {
            market.showBrowse();
         }
         else
         {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setContentText("Incorrect login information");
            alert.showAndWait();
         }
      });
   }

   public void setMarket(Market market)
   {
      this.market = market;
   }
}
