package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXMasonryPane;
import dao.implementations.*;
import dao.interfaces.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import models.*;
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
   private ComboBox<Cardset> cardSetCombo;
   @FXML
   private ComboBox<Rarity> rarityCombo;
   @FXML
   private ComboBox<Type> typeCombo;
   @FXML
   private ComboBox<Subtype> subTypeCombo;
   @FXML
   private ComboBox<Condition> conditionCombo;
   @FXML
   private ComboBox<Language> languageCombo;

   @FXML
   private void initialize()
   {
      cardPane = new JFXMasonryPane();
      TextFields.bindAutoCompletion(searchBar, "adaad", "asdasdasa", "asdasdasdsa", "adadasdasdas", "asdasdsa");

      searchBar.setPromptText("Enter card name... ");
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

      populateCardGrid();
      populateFilters();
   }

   public void focusMain()
   {
      scrollPane.requestFocus();
   }

   private void populateCardGrid()
   {
      cardPane.setStyle("-fx-background-color: #181818");

      ICardDao cardDao = new CardDao();
      List<Card> cardList = cardDao.getAllCards(0, 15);

      for (int i = 0; i < cardList.size(); ++i)
      {
         Card card = cardList.get(i);
         VBox cardBox = new VBox();
         String imgName = card.getImageUrl();

         ImageView cardImage = new ImageView("images/" +imgName); // TODO handle image not found exceptions - IllegalArgumentException

         Label label = new Label(card.getName());
         Label label2 = new Label(card.getDescription());
         label.setTextFill(Paint.valueOf("#bfbfbf"));
         label2.setTextFill(Paint.valueOf("#bfbfbf"));
         cardBox.getChildren().addAll(cardImage, label, label2);
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
   }

   private void populateFilters()
   {
      ICardSetDao cardSetDao = new CardSetDao();
      List<Cardset> cardsetList = cardSetDao.getAllSets();

      IRarityDao rarityDao = new RarityDao();
      List<Rarity> rarityList = rarityDao.getAllRarities();

      ITypeDao typeDao = new TypeDao();
      List<Type> typeList = typeDao.getAllTypes();

      ISubtypeDao subtypeDao = new SubtypeDao();
      List<Subtype> subtypeList = subtypeDao.getAllSubtypes();

      IConditionDao conditionDao = new ConditionDao();
      List<Condition> conditionList = conditionDao.getAllConditions();

      ILanguageDao languageDao = new LanguageDao();
      List<Language> languageList = languageDao.getAllLanguages();

      cardSetCombo.getItems().add(new Cardset(-1, "All Sets", "" ));
      cardSetCombo.getSelectionModel().selectFirst();
      cardSetCombo.getItems().addAll(cardsetList);


      rarityCombo.getItems().add(new Rarity(-1, "All Rarities", ""));
      rarityCombo.getSelectionModel().selectFirst();
      rarityCombo.getItems().addAll(rarityList);

      typeCombo.getItems().add(new Type(-1, "All Types"));
      typeCombo.getSelectionModel().selectFirst();
      typeCombo.getItems().addAll(typeList);

      subTypeCombo.getItems().add(new Subtype(-1, "All Sub Types"));
      subTypeCombo.getSelectionModel().selectFirst();
      subTypeCombo.getItems().addAll(subtypeList);

      conditionCombo.getItems().add(new Condition(-1, "All Conditions", ""));
      conditionCombo.getSelectionModel().selectFirst();
      conditionCombo.getItems().addAll(conditionList);

      languageCombo.getItems().add(new Language(-1, "All Languages", ""));
      languageCombo.getSelectionModel().selectFirst();
      languageCombo.getItems().addAll(languageList);
   }
}
