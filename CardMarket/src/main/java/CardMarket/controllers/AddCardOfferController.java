package CardMarket.controllers;

import CardMarket.Market;
import CardMarket.dao.UserCreator;
import CardMarket.dao.implementations.CardDao;
import CardMarket.dao.implementations.CardOfferDao;
import CardMarket.dao.interfaces.ICardDao;
import CardMarket.dao.interfaces.ICardOfferDao;
import CardMarket.models.Card;
import CardMarket.models.CardOffer;
import CardMarket.models.User;
import CardMarket.util.TreeTableViewRecord;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class AddCardOfferController
{
   @FXML
   private BorderPane tableRoot;
   @FXML
   private CustomTextField searchBar;
   @FXML
   JFXButton searchButton;

   JFXTreeTableView<TreeTableViewRecord> cardTable = new JFXTreeTableView<>();

   private AutoCompletionBinding bind = null;
   private boolean isSearching = false;
   private ICardDao cardDao = new CardDao();
   private Market market;
   private User user;

   @FXML
   private void initialize()
   {

      populateAutoComplete();

      searchButton.setOnAction(event ->
      {
         createCardList();
      });
   }

   private void populateAutoComplete() // TODO apply DRY
   {
      searchBar.setPromptText("Enter card name... ");
      searchBar.setOnKeyReleased(event ->
      {
         if (bind != null && searchBar.getText().length() < 3)
         {
            bind.dispose();
            isSearching = false;
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

   private void createCardList()
   {
      ObservableList<TreeTableViewRecord> cartList = generateCardList();

      JFXTreeTableColumn<TreeTableViewRecord, String> idColumn = new JFXTreeTableColumn<>("ID");
      idColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<TreeTableViewRecord, String> col) -> col.getValue().getValue().cardset);

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

      final TreeItem<TreeTableViewRecord> root = new RecursiveTreeItem<>(cartList, RecursiveTreeObject::getChildren);
      cardTable = new JFXTreeTableView<>(root);
      cardTable.setOnMouseClicked(event ->
      {
         Optional<Pair<String, String>> result = generateInputDialog();
         ICardOfferDao cardOfferDao = new CardOfferDao();
         result.ifPresent(quantityPrice -> {
            CardOffer cardOffer = new CardOffer(cardDao.getCard(cardTable.getSelectionModel().getSelectedItem().getValue().id.getValue().intValue()),
                    UserCreator.getLoggedUser(), Integer.parseInt(quantityPrice.getKey()),
                    Double.parseDouble(quantityPrice.getValue()), false, new HashSet<>());
            cardOfferDao.createCardOffer(cardOffer);

            JFXSnackbar bar = new JFXSnackbar(tableRoot);
            bar.enqueue(new JFXSnackbar.SnackbarEvent("New offer created successfully!"));
         });

      });

      cardTable.setShowRoot(false);
      cardTable.setEditable(false);
      cardTable.getColumns().setAll(cardsetColumn, cardColumn, rarityColumn, conditionColumn, languageColumn);
      tableRoot.setCenter(cardTable);

   }

   private ObservableList<TreeTableViewRecord> generateCardList()
   {
      ObservableList<TreeTableViewRecord> tempCardTable = FXCollections.observableArrayList();
      List<Card> cardList = cardDao.getAllCardsLike(searchBar.getText());

      for (Card card : cardList)
      {
         tempCardTable.add(new TreeTableViewRecord(card.getCardID(),
                 card.getCardset().getName(),
                 card.getName(),
                 card.getRarity().getName(),
                 card.getCondition().getName(),
                 card.getLanguage().getName(),
                 0,
                 0,
                 ""));
      }

      return tempCardTable;
   }

   public void setMarket(Market market)
   {
      this.market = market;
   }

   public void setUser(User user)
   {
      this.user = user;
   }

   private Optional<Pair<String, String>> generateInputDialog()
   {
      // Create the custom dialog.
      Dialog<Pair<String, String>> dialog = new Dialog<>();
      dialog.setTitle("Add New Card Offer");
      dialog.setHeaderText("Select the quantity and price of the card");



      // Set the button types.
      ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
      dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

      // Create the username and password labels and fields.
      GridPane grid = new GridPane();
      grid.setHgap(10);
      grid.setVgap(10);
      grid.setPadding(new Insets(20, 150, 10, 10));

      TextField quantity = new TextField();
      quantity.setPromptText("Quantity");
      TextField price = new TextField();
      price.setPromptText("Price");

      grid.add(new Label("Quantity:"), 0, 0);
      grid.add(quantity, 1, 0);
      grid.add(new Label("Price:"), 0, 1);
      grid.add(price, 1, 1);

      Node addButton = dialog.getDialogPane().lookupButton(addButtonType);


      dialog.getDialogPane().setContent(grid);

      // Request focus on the username field by default.
      Platform.runLater(() -> quantity.requestFocus());

      dialog.setResultConverter(dialogButton -> {
         if (dialogButton == addButtonType) {
            if(quantity.getText().length() > 0 && price.getText().length() > 0)
            {
               return new Pair<>(quantity.getText(), price.getText());
            }
            else
            {
               return null;
            }
         }
         return null;
      });

      Optional<Pair<String, String>> result = dialog.showAndWait();

      return result;
   }
}
