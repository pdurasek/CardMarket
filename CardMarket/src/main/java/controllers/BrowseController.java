package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import dao.implementations.*;
import dao.interfaces.*;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import models.*;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

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
   @FXML
   private JFXHamburger hamburgerMenu;

   private AutoCompletionBinding bind = null;
   private boolean isSearching = false;
   private List<Card> lastSearchCardList;
   private ICardDao cardDao = new CardDao();

   private final int IMAGE_CARDS_PER_PAGE = 15;

   @FXML
   private void initialize()
   {
      hamburgerMenu.getStyleClass().add("jfx-hamburger-icon");
      filterDrawer.setSidePane(sidePane);
      filterDrawer.setDefaultDrawerSize(350);
      filterDrawer.setOverLayVisible(true);
      filterDrawer.setOpacity(0.925);
      sidePane.setStyle("-fx-background-color: #181818");
      filterDrawer.close();
      searchButton.setText("Search");
      searchButton.addEventHandler(MOUSE_PRESSED, event ->
      {
         if (searchBar.getLength() > 0)
         {
            mainPane.getChildren().clear();
            mainPane.getChildren().addAll(createCardGrid(searchBar.getText(), false,"",-1), filterDrawer);
         }
      });

      mainPane.getChildren().clear();
      mainPane.getChildren().addAll(createCardGrid("", false,"",-1), filterDrawer);
      createHamburgerMenu();
      populateFilters();
      populateAutoComplete();
   }

   private void createHamburgerMenu()
   {
      HamburgerBackArrowBasicTransition burgerTask2 = new HamburgerBackArrowBasicTransition(hamburgerMenu);
      burgerTask2.setRate(-1);
      hamburgerMenu.addEventHandler(MouseEvent.MOUSE_PRESSED, event ->
      {
         burgerTask2.setRate(burgerTask2.getRate() * -1);
         burgerTask2.play();

         if (filterDrawer.isShown())
         {
            filterDrawer.close();
         }
         else
         {
            filterDrawer.open();
         }
      });
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

            bind = TextFields.bindAutoCompletion(searchBar, cardDao.getAllCardsLike(searchBar.getText()));
         }
      });
   }

   private Pagination createCardGrid(String pattern, boolean isFiltered, String filterColumn, int filterValue)
   {
      int uniqueCardsCount;

      if (isFiltered)
      {
         uniqueCardsCount = cardDao.getAllCardsFilteredCount(pattern, filterColumn, filterValue);
      }
      else if (pattern.length() > 0)
      {
         uniqueCardsCount = cardDao.getAllCardsCount(pattern);
      }
      else
      {
         uniqueCardsCount = cardDao.getAllCardsCount();
      }

      int pageCount = (int) Math.ceil((double) uniqueCardsCount / IMAGE_CARDS_PER_PAGE);
      if (pageCount == 0)
      {
         pageCount = 1;
      }

      Pagination pagination = new Pagination(pageCount, 0);
      pagination.setPageFactory(param -> populateCardGrid(param, pattern, isFiltered, filterColumn, filterValue));
      pagination.setStyle("-fx-background-color: #181818");

      return pagination;
   }

   private ScrollPane populateCardGrid(int pageIndex, String pattern, boolean isFiltered, String filterColumn, int filterValue)
   {
      JFXMasonryPane masonryCardPane;

      if (isFiltered)
      {
         lastSearchCardList = cardDao.getAllCardsFiltered(pattern, pageIndex*IMAGE_CARDS_PER_PAGE, IMAGE_CARDS_PER_PAGE, filterColumn, filterValue);
      }
      else if (pattern.length() > 0)
      {
         lastSearchCardList = cardDao.getAllCardsLike(pattern, pageIndex * IMAGE_CARDS_PER_PAGE, IMAGE_CARDS_PER_PAGE);
      }
      else
      {
         lastSearchCardList = cardDao.getAllCards(pageIndex * IMAGE_CARDS_PER_PAGE, IMAGE_CARDS_PER_PAGE);
      }

      masonryCardPane = populateMasonryPane(lastSearchCardList);

      ScrollPane scrollPane = new ScrollPane();
      scrollPane.getStyleClass().add("jfx-masonry-pane");
      scrollPane.setFitToHeight(true);
      scrollPane.setFitToWidth(true);
      scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
      scrollPane.setContent(masonryCardPane);

      // DO NOT REMOVE THE NEXT TWO LINES (we found no other way of making the scroll bar appear to enable scrolling)
      scrollPane.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> scrollPane.resize(scrollPane.getWidth() + 1, scrollPane.getHeight() + 1));
      scrollPane.addEventHandler(MouseEvent.MOUSE_EXITED, event -> scrollPane.resize(scrollPane.getWidth() - 1, scrollPane.getHeight() - 1));

      return scrollPane;
   }

   private JFXMasonryPane populateMasonryPane(List<Card> cardList)
   {
      JFXMasonryPane masonryPane = new JFXMasonryPane();
      masonryPane.getStyleClass().add("jfx-masonry-pane");

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

      return masonryPane;
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
      rarityCombo.valueProperty().addListener((observable, oldValue, newValue) ->
      {
         mainPane.getChildren().clear();
         mainPane.getChildren().addAll(createCardGrid(searchBar.getText(), true, "rarity", newValue.getRarityID()), filterDrawer);
      });

      typeCombo.getItems().add(new Type(-1, "All Types"));
      typeCombo.getSelectionModel().selectFirst();
      typeCombo.getItems().addAll(typeList);
      typeCombo.valueProperty().addListener((observable, oldValue, newValue) ->
      {
         mainPane.getChildren().clear();
         mainPane.getChildren().addAll(createCardGrid(searchBar.getText(), true, "type", newValue.getTypeID()), filterDrawer);
      });

      subTypeCombo.getItems().add(new Subtype(-1, "All Sub Types"));
      subTypeCombo.getSelectionModel().selectFirst();
      subTypeCombo.getItems().addAll(subtypeList);
      subTypeCombo.valueProperty().addListener((observable, oldValue, newValue) ->
      {
         mainPane.getChildren().clear();
         mainPane.getChildren().addAll(createCardGrid(searchBar.getText(), true, "subtype", newValue.getSubTypeID()), filterDrawer);
      });

      conditionCombo.getItems().add(new Condition(-1, "All Conditions", ""));
      conditionCombo.getSelectionModel().selectFirst();
      conditionCombo.getItems().addAll(conditionList);
      conditionCombo.valueProperty().addListener((observable, oldValue, newValue) ->
      {
         mainPane.getChildren().clear();
         mainPane.getChildren().addAll(createCardGrid(searchBar.getText(), true, "condition", newValue.getConditionId()), filterDrawer);
      });

      languageCombo.getItems().add(new Language(-1, "All Languages", ""));
      languageCombo.getSelectionModel().selectFirst();
      languageCombo.getItems().addAll(languageList);
      languageCombo.valueProperty().addListener((observable, oldValue, newValue) ->
      {
         mainPane.getChildren().clear();
         mainPane.getChildren().addAll(createCardGrid(searchBar.getText(), true, "language", newValue.getLanguageID()), filterDrawer);
      });
   }
}
