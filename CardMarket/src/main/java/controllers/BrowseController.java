package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXMasonryPane;
import dao.implementations.CardDao;
import dao.interfaces.ICardDao;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import models.Card;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

import java.util.List;

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
   private ScrollPane scrollPane;

   @FXML
   private void initialize()
   {
      cardPane = new JFXMasonryPane();
      cardPane.setFocusTraversable(true);
      TextFields.bindAutoCompletion(searchBar, "adaad", "asdasdasa", "asdasdasdsa", "adadasdasdas", "asdasdsa");
      TextField test = new TextField();
      searchBar.setPromptText("Enter card name... ");
      searchBar.setFocusTraversable(false);
      filterDrawer.setSidePane(sidePane);
      filterDrawer.setDefaultDrawerSize(350);
      filterDrawer.setOverLayVisible(true);
      filterDrawer.setOpacity(0.925);
      sidePane.setStyle("-fx-background-color: #181818");
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

      testMasonry();
   }

   private void testMasonry()
   {
      cardPane.setStyle("-fx-background-color: #181818");

      ICardDao karta = new CardDao();
      List<Card> karteee = karta.getAllCards(0, 15);

      for (int i = 0; i < karteee.size(); ++i)
      {
         Card card = (Card) karteee.get(i);

         VBox cardBox = new VBox();
         String imgName = card.getImageUrl();

         ImageView slikica = new ImageView("images/" +imgName);

         Label label = new Label(card.getName());
         Label label2 = new Label(card.getDescription());
         label.setTextFill(Paint.valueOf("#bfbfbf"));
         label2.setTextFill(Paint.valueOf("#bfbfbf"));
         cardBox.getChildren().addAll(slikica, label, label2);
         cardBox.setPrefSize(171,243 );

         cardPane.getChildren().add(cardBox);

      }

      cardPane.setCellHeight(150);
      cardPane.setHSpacing(20);
      cardPane.setVSpacing(1);
      scrollPane.setFitToHeight(true);
      scrollPane.setFitToWidth(true);
      scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
      scrollPane.setContent(cardPane);

      System.out.println(cardPane.getWidth() +" | " +cardPane.getHeight());
      System.out.println(scrollPane.getWidth() +" | " +scrollPane.getHeight());
      /*StackPane root = new StackPane(scrollPane);

      Scene scene = new Scene(root, 1150 , 800);

      primaryStage.setTitle("Ayyyy Masonry Pane bby");
      primaryStage.setScene(scene);
      primaryStage.show();
      primaryStage.requestFocus(); // TODO fix scroll not appearing on start even though it is focused
      primaryStage.getIcons().add(new Image("images/icon.png"));*/
   }
}
