package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXMasonryPane;
import dao.implementations.*;
import dao.interfaces.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import models.*;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import sun.reflect.generics.scope.Scope;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;

public class BrowseController
{
   @FXML
   private CustomTextField searchBar;
   @FXML
   private JFXButton searchButton;
   @FXML
   private JFXDrawer filterDrawer;
   @FXML
   private StackPane sidePane;
   @FXML
   private StackPane mainPane;
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

   private AutoCompletionBinding bind = null;
   private boolean isSearching = false;

   private final int IMAGE_CARDS_PER_PAGE = 15;

   @FXML
   private void initialize()
   {
      filterDrawer.setSidePane(sidePane);
      filterDrawer.setDefaultDrawerSize(350);
      filterDrawer.setOverLayVisible(true);
      filterDrawer.setOpacity(0.925);
      sidePane.setStyle("-fx-background-color: #181818");
      filterDrawer.close();
      searchButton.setText("Search");
      searchButton.addEventHandler(MOUSE_PRESSED, event -> {
         if (filterDrawer.isShown())
         {
            filterDrawer.close();
         }
         else
         {
            filterDrawer.open();
         }
      });

      mainPane.getChildren().clear();
      mainPane.getChildren().addAll(createCardGrid(), filterDrawer);
      populateFilters();
      populateAutoComplete();
   }

   private void populateAutoComplete()
   {
      searchBar.setPromptText("Enter card name... ");

      searchBar.setOnKeyReleased(event ->
      {
         if (bind != null && searchBar.getText().length() < 3)
         {
            bind.dispose();
            isSearching = false; // TODO revisit autosuggestion logic?
         }

         if (searchBar.getText().length() >= 3 && !isSearching)
         {
            isSearching = true;

            if (bind != null)
            {
               bind.dispose();
            }

            ICardDao cardDao = new CardDao(); // TODO move to global?
            bind = TextFields.bindAutoCompletion(searchBar, cardDao.getAllCardsLike(searchBar.getText()));
         }
      });
   }

   private Pagination createCardGrid()
   {
      ICardDao cardDao = new CardDao();
      int uniqueCardsCount = cardDao.getAllCardsCount();
      int pageCount = (int) Math.ceil((double) uniqueCardsCount / IMAGE_CARDS_PER_PAGE);

      Pagination pagination = new Pagination(pageCount, 0);
      pagination.setPageFactory(param -> populateCardGrid(param));
      pagination.setStyle("-fx-background-color: #181818");

      return pagination;
   }

   private ScrollPane populateCardGrid(int pageIndex)
   {
      JFXMasonryPane masonryPane = new JFXMasonryPane();
      masonryPane.getStyleClass().add("jfx-masonry-pane");
      ICardDao cardDao = new CardDao();
      List<Card> cardList = cardDao.getAllCards(pageIndex * IMAGE_CARDS_PER_PAGE, IMAGE_CARDS_PER_PAGE);

      for (int i = 0; i < cardList.size(); ++i)
      {
         Card card = cardList.get(i);
         VBox cardBox = new VBox();
         String imgName = card.getImageUrl();

         ImageView cardImage = new ImageView("images/" + imgName); // TODO handle image not found exceptions - IllegalArgumentException

         Label label = new Label(card.getName());
         Label label2 = new Label(card.getDescription());
         label.setTextFill(Paint.valueOf("#bfbfbf"));
         label2.setTextFill(Paint.valueOf("#bfbfbf"));
         cardBox.getChildren().addAll(cardImage, label, label2);
         cardBox.setPrefSize(171, 243);

         masonryPane.getChildren().add(cardBox);
      }

      masonryPane.setCellHeight(150);
      masonryPane.setHSpacing(20);
      masonryPane.setVSpacing(1);
      ScrollPane scrollPane = new ScrollPane();
      scrollPane.getStyleClass().add("jfx-masonry-pane");
      scrollPane.setFitToHeight(true);
      scrollPane.setFitToWidth(true);
      scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
      scrollPane.setContent(masonryPane);

      // DO NOT REMOVE THE NEXT TWO LINES (we found no other way of making the scroll bar appear to enable scrolling)
      scrollPane.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> scrollPane.resize(scrollPane.getWidth()+1, scrollPane.getHeight()+1));
      scrollPane.addEventHandler(MouseEvent.MOUSE_EXITED, event -> scrollPane.resize(scrollPane.getWidth()-1, scrollPane.getHeight()-1));

      return scrollPane;
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

      cardSetCombo.getItems().add(new Cardset(-1, "All Sets", ""));
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
