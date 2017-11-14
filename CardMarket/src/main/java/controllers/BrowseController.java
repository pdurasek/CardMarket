package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;

public class BrowseController
{
   @FXML
   private CustomTextField searchBar;

   @FXML
   private JFXMasonryPane cardPane;

   @FXML
   private JFXButton searchButton;

   @FXML
   private JFXDrawer filterDrawer;

   @FXML
   private StackPane sidePane;

   @FXML
   private void initialize()
   {
      cardPane.setFocusTraversable(true);
      TextFields.bindAutoCompletion(searchBar, "adaad", "asdasdasa", "asdasdasdsa", "adadasdasdas", "asdasdsa");
      TextField test = new TextField();
      searchBar.setPromptText("Enter card name... ");
      searchBar.setFocusTraversable(false);
      filterDrawer.setSidePane(sidePane);
      filterDrawer.setDefaultDrawerSize(350);
      filterDrawer.setOverLayVisible(false);
      filterDrawer.close();
      searchButton.setText("Search");
      searchButton.addEventHandler(MOUSE_PRESSED, event -> {
         if(filterDrawer.isShown())
         {
            filterDrawer.close();
         }
         else
         {
            filterDrawer.open();
         }
      });

   }
}
