package CardMarket.controllers;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

public class UniqueCardController
{
   @FXML
   private JFXTextArea textArea;

   @FXML
   private void initialize()
   {
      textArea.getStyleClass().add("jfx-text-area");
   }
}
