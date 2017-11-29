package CardMarket.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserController
{
   @FXML
   private Label totalCardsBoughtLabel, totalCardsSoldLabel, userRatingLabel, countryLabel;
   @FXML
   private TextField streetAddress, city, country, zip;
   @FXML
   private ComboBox templateComboBox;

   @FXML
   private void initialize()
   {

   }
}
