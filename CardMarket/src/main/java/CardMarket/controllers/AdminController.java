package CardMarket.controllers;

import CardMarket.dao.implementations.*;
import CardMarket.dao.interfaces.*;
import CardMarket.models.*;
import CardMarket.util.CardRecord;
import CardMarket.util.OneRecord;
import CardMarket.util.TwoRecord;
import CardMarket.util.UserRecord;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.util.List;

// TODO Implement Search for Users and Cards, Read/Create Users(Exception), Update/Delete for every model!
public class AdminController
{
    @FXML
    private JFXTreeTableView<CardRecord> cardTTV;
    @FXML
    private JFXTreeTableColumn<CardRecord, String> cardNameCol, cardDescCol, carImgURLCol, cardTypeCol, cardSubTypeCol, cardRarityCol,
            cardLangCol, cardSetCol, cardCondCol;
    @FXML
    private JFXTreeTableColumn<CardRecord, Number> cardIdCol;
    @FXML
    private JFXTreeTableColumn<CardRecord, Boolean> cardFirstEdCol;
    @FXML
    private JFXTreeTableView<UserRecord> userTTV;
    @FXML
    private JFXTreeTableColumn<UserRecord, String> userNameCol, userPassCol, userEmailCol, userAddCol, userCredCol;
    @FXML
    private JFXTreeTableColumn<UserRecord, Number> userIdCol, userBoughtCol, userSoldCol;
    @FXML
    private JFXTreeTableView<TwoRecord> paymentTTV, credibilityTTV, conditionTTV, rarityTTV, setTTV;
    @FXML
    private JFXTreeTableColumn<TwoRecord, String> payNameCol, payDescCol, credNameCol, credDescCol, condNameCol,
            condAbbCol, rarityNameCol, rarityAbbCol, setNameCol, setAbbCol;
    @FXML
    private JFXTreeTableColumn<TwoRecord, Number> payIdCol, credIdCol, condIdCol, rarityIdCol, setIdCol;
    @FXML
    private JFXTreeTableView<OneRecord> subTypeTTV, typeTTV;
    @FXML
    private JFXTreeTableColumn<OneRecord, String> subTypeNameCol, typeNameCol;
    @FXML
    private JFXTreeTableColumn<OneRecord, Number> subTypeIdCol, typeIdCol;
    @FXML //Get checkbox
    private CheckBox cardFirstEd;
    @FXML //Get text area
    private TextArea cardDesc;
    @FXML //Get combo boxes
    private JFXComboBox<Type> typeCombo;
    @FXML
    private JFXComboBox<Subtype> subTypeCombo;
    @FXML
    private JFXComboBox<Rarity> rarityCombo;
    @FXML
    private JFXComboBox<Language> languageCombo;
    @FXML
    private JFXComboBox<Cardset> setCombo;
    @FXML
    private JFXComboBox<Condition> condCombo;
    @FXML
    private JFXComboBox<Credibility> credCombo;
    @FXML
    private JFXComboBox<ShippingAddressTemplate> shipCombo;
    @FXML //Get text fields
    private TextField cardName, cardImgUrl, payName, payDesc, credName, credDesc, condName, condAbb, rarityName, rarityAbb,
    setName, setAbb, subTypeName, typeName, userName, userPass, userCardsBought, userCardsSold, userEmail;
    @FXML //Button onClick create
    private Button createPayment, createCredibility, createCondition, createRarity, createSet, createSubType, createType, createCard, createUser;
    @FXML //Get main Border - Used for snack bar messages
    private BorderPane borderPane;

    //Initialize Objects
    private Card card;
    private User user;
    private PaymentMethod paymentMtd;
    private Credibility credibility;
    private Condition condition;
    private Rarity rarity;
    private Cardset cardset;
    private Subtype subtype;
    private Type type;

    //Create DAO
    private IPaymentMethodDao paymentMethodDao = new PaymentMethodDao();
    private ICredibilityDao credibilityDao = new CredibilityDao();
    private IConditionDao conditionDao = new ConditionDao();
    private IRarityDao rarityDao = new RarityDao();
    private ICardSetDao cardSetDao = new CardSetDao();
    private ISubtypeDao subtypeDao = new SubtypeDao();
    private ITypeDao typeDao = new TypeDao();
    private ICardDao cardDao = new CardDao();
    private ILanguageDao languageDao = new LanguageDao();
    private IUserDao userDao = new UserDao();
    private IShippingAddressTemplateDao shippingAddressTemplateDao = new ShippingAddressTemplateDao();

    @FXML
    public void initialize() {
        //Populate Comboboxes
        typeCombo.getItems().addAll(typeDao.getAllTypes());
        typeCombo.getSelectionModel().selectFirst();
        subTypeCombo.getItems().addAll(subtypeDao.getAllSubtypes());
        subTypeCombo.getSelectionModel().selectFirst();
        setCombo.getItems().addAll(cardSetDao.getAllSets());
        setCombo.getSelectionModel().selectFirst();
        rarityCombo.getItems().addAll(rarityDao.getAllRarities());
        rarityCombo.getSelectionModel().selectFirst();
        condCombo.getItems().addAll(conditionDao.getAllConditions());
        condCombo.getSelectionModel().selectFirst();
        languageCombo.getItems().addAll(languageDao.getAllLanguages());
        languageCombo.getSelectionModel().selectFirst();
        shipCombo.getItems().addAll(shippingAddressTemplateDao.getAllShippingAddressTemplates());
        shipCombo.getSelectionModel().selectFirst();
        credCombo.getItems().addAll(credibilityDao.getAllCredibilities());
        credCombo.getSelectionModel().selectFirst();

        //List all data in JFXTreeTableViews
        createCardList();
        //createUserList(); TODO Cannot initialize Users throws Exception
        createPaymentMethodList();
        createCredibilityList();
        createConditionList();
        createRarityList();
        createCardsetList();
        createSubtypeList();
        createTypeList();

        //OnMouseClick Create User
        createUser.setOnAction(event ->
        {
            //New User
            user = new User();

            if( userName.getText().length() > 0 && userPass.getText().length() > 0 && userEmail.getText().length() > 0 && userCardsBought.getText().length() > 0
                    && userCardsSold.getText().length() > 0 ) {
                user.setUsername(userName.getText());
                user.setPassword(userPass.getText());
                user.setCardsBought(Integer.parseInt(userCardsBought.getText()));
                user.setCardsSold(Integer.parseInt(userCardsSold.getText()));
                user.setEmail(userEmail.getText());
                user.setShippingAddressTemplate(shipCombo.getSelectionModel().getSelectedItem());
                user.setCredibility(credCombo.getSelectionModel().getSelectedItem());

                if(userDao.createUser(user)) {
                    JFXSnackbar bar = new JFXSnackbar(borderPane);
                    bar.enqueue(new JFXSnackbar.SnackbarEvent("User created!"));
                    createUserList();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("User creation error!");
                    alert.setContentText("All fields must be filled!");
                    alert.showAndWait();
                }
            }
        });

        //OnMouseClick Create Card
        createCard.setOnAction(event ->
        {
            //New Card
            card = new Card();

            if( cardName.getText().length() > 0 && cardDesc.getText().length() > 0 && cardImgUrl.getText().length() > 0) {
                card.setName(cardName.getText());
                card.setFirstEd(cardFirstEd.isSelected());
                card.setDescription(cardDesc.getText());
                card.setImageUrl(cardImgUrl.getText());
                card.setType(typeCombo.getSelectionModel().getSelectedItem());
                card.setSubtype(subTypeCombo.getSelectionModel().getSelectedItem());
                card.setRarity(rarityCombo.getSelectionModel().getSelectedItem());
                card.setLanguage(languageCombo.getSelectionModel().getSelectedItem());
                card.setCardset(setCombo.getSelectionModel().getSelectedItem());
                card.setCondition(condCombo.getSelectionModel().getSelectedItem());

                if(cardDao.createCard(card)) {
                    JFXSnackbar bar = new JFXSnackbar(borderPane);
                    bar.enqueue(new JFXSnackbar.SnackbarEvent("Card created!"));
                    createCardList();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Card Error!");
                    alert.setContentText("All fields must be filled!");
                    alert.showAndWait();
                }
            }
        });

        //OnMouseClick Create Payment Method
        createPayment.setOnAction(event ->
        {
            //New Payment Method
            paymentMtd = new PaymentMethod();

            if( payName.getText().length() > 0 && payDesc.getText().length() > 0) {
                paymentMtd.setName(payName.getText());
                paymentMtd.setDescription(payDesc.getText());

                if(paymentMethodDao.createPaymentMethod(paymentMtd)) {
                    JFXSnackbar bar = new JFXSnackbar(borderPane);
                    bar.enqueue(new JFXSnackbar.SnackbarEvent("Payment Method created!"));
                    createPaymentMethodList();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Payment method Error!");
                    alert.setContentText("All fields must be filled!");
                    alert.showAndWait();
                }
            }
        });

        //OnMouseClick Create Credibility
        createCredibility.setOnAction(event ->
        {
            //New Credibility
            credibility = new Credibility();

            if( credName.getText().length() > 0 && credDesc.getText().length() > 0) {
                credibility.setName(credName.getText());
                credibility.setDescription(credDesc.getText());

                if(credibilityDao.createCredibility(credibility)) {
                    JFXSnackbar bar = new JFXSnackbar(borderPane);
                    bar.enqueue(new JFXSnackbar.SnackbarEvent("Credibility created!"));
                    createCredibilityList();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Credibility Error!");
                    alert.setContentText("All fields must be filled!");
                    alert.showAndWait();
                }
            }
        });

        //OnMouseClick Create Condition
        createCondition.setOnAction(event ->
        {
            //New Condition
            condition = new Condition();

            if( condName.getText().length() > 0 && condAbb.getText().length() > 0) {
                condition.setName(condName.getText());
                condition.setAbbr(condAbb.getText());

                if(conditionDao.createCondition(condition)) {
                    JFXSnackbar bar = new JFXSnackbar(borderPane);
                    bar.enqueue(new JFXSnackbar.SnackbarEvent("Condition created!"));
                    createConditionList();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Condition Error!");
                    alert.setContentText("All fields must be filled!");
                    alert.showAndWait();
                }
            }
        });

        //OnMouseClick Create Rarity
        createRarity.setOnAction(event ->
        {
            //New Rarity
            rarity = new Rarity();

            if( rarityName.getText().length() > 0 && rarityAbb.getText().length() > 0) {
                rarity.setName(rarityName.getText());
                rarity.setAbbr(rarityAbb.getText());

                if(rarityDao.createRarity(rarity)) {
                    JFXSnackbar bar = new JFXSnackbar(borderPane);
                    bar.enqueue(new JFXSnackbar.SnackbarEvent("Rarity created!"));
                    createRarityList();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Rarity Error!");
                    alert.setContentText("All fields must be filled!");
                    alert.showAndWait();
                }
            }
        });

        //OnMouseClick Create Cardset
        createSet.setOnAction(event ->
        {
            //New Cardset
            cardset = new Cardset();

            if( setName.getText().length() > 0 && setAbb.getText().length() > 0) {
                cardset.setName(setName.getText());
                cardset.setAbbr(setAbb.getText());

                if(cardSetDao.createCardset(cardset)) {
                    JFXSnackbar bar = new JFXSnackbar(borderPane);
                    bar.enqueue(new JFXSnackbar.SnackbarEvent("Card Set created!"));
                    createCardsetList();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Card Set Error!");
                    alert.setContentText("All fields must be filled!");
                    alert.showAndWait();
                }
            }
        });

        //OnMouseClick Create Subtype
        createSubType.setOnAction(event ->
        {
            //New Subtype
            subtype = new Subtype();

            if( subTypeName.getText().length() > 0 ) {
                subtype.setName(subTypeName.getText());

                if(subtypeDao.createSubType(subtype)) {
                    JFXSnackbar bar = new JFXSnackbar(borderPane);
                    bar.enqueue(new JFXSnackbar.SnackbarEvent("Sub Type created!"));
                    createSubtypeList();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Sub Type Error!");
                    alert.setContentText("All fields must be filled!");
                    alert.showAndWait();
                }
            }
        });

        //OnMouseClick Create Type
        createType.setOnAction(event ->
        {
            //New Type
            type = new Type();

            if( typeName.getText().length() > 0 ) {
                type.setName(typeName.getText());

                if(typeDao.createType(type)) {
                    JFXSnackbar bar = new JFXSnackbar(borderPane);
                    bar.enqueue(new JFXSnackbar.SnackbarEvent("Type created!"));
                    createTypeList();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Type Error!");
                    alert.setContentText("All fields must be filled!");
                    alert.showAndWait();
                }
            }
        });
    }

    //Create add list to JFXTableTreeView paymentTTV
    public void createPaymentMethodList() {
        ObservableList<TwoRecord> paymentList = generatePaymentMethods();

        payIdCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<TwoRecord, Number> col) -> col.getValue().getValue().id);
        payNameCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<TwoRecord, String> col) -> col.getValue().getValue().name);
        payDescCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<TwoRecord, String> col) -> col.getValue().getValue().description);

        final TreeItem<TwoRecord> root = new RecursiveTreeItem<>(paymentList, RecursiveTreeObject::getChildren);
        paymentTTV.getColumns().setAll(payIdCol,payNameCol,payDescCol);
        paymentTTV.setRoot(root);
        paymentTTV.setShowRoot(false);
    }
    //Create all Payment Methods
    private ObservableList<TwoRecord> generatePaymentMethods()
    {
        ObservableList<TwoRecord> paymentList = FXCollections.observableArrayList();
        List<PaymentMethod> tempPayments = paymentMethodDao.getAllPaymentMethods();

        for (PaymentMethod paymentMethod : tempPayments) {
            paymentList.add(new TwoRecord(paymentMethod.getPaymentMethodID(),paymentMethod.getName(),
                    paymentMethod.getDescription()));
        }

        return paymentList;
    }

    //Create all Credibilities
    private ObservableList<TwoRecord> generateCredibilities()
    {
        ObservableList<TwoRecord> credibilityList = FXCollections.observableArrayList();
        List<Credibility> tempCredibilities = credibilityDao.getAllCredibilities();

        for (Credibility credibilities : tempCredibilities) {
            credibilityList.add(new TwoRecord(credibilities.getCredibilityID(),credibilities.getName(),
                    credibilities.getDescription()));
        }

        return credibilityList;
    }
    //Create add list to JFXTableTreeView credibilityTTV
    private void createCredibilityList() {
        ObservableList<TwoRecord> credibilityList = generateCredibilities();

        credIdCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<TwoRecord, Number> col) -> col.getValue().getValue().id);
        credNameCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<TwoRecord, String> col) -> col.getValue().getValue().name);
        credDescCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<TwoRecord, String> col) -> col.getValue().getValue().description);

        final TreeItem<TwoRecord> root = new RecursiveTreeItem<>(credibilityList, RecursiveTreeObject::getChildren);
        credibilityTTV.getColumns().setAll(credIdCol,credNameCol,credDescCol);
        credibilityTTV.setRoot(root);
        credibilityTTV.setShowRoot(false);
    }

    //Create all Conditions
    private ObservableList<TwoRecord> generateConditions()
    {
        ObservableList<TwoRecord> conditionList = FXCollections.observableArrayList();
        List<Condition> tempConditions = conditionDao.getAllConditions();

        for (Condition conditions : tempConditions) {
            conditionList.add(new TwoRecord(conditions.getConditionId(),conditions.getName(),
                    conditions.getAbbr()));
        }

        return conditionList;
    }
    //Create add list to JFXTableTreeView conditionTTV
    private void createConditionList() {
        ObservableList<TwoRecord> conditionList = generateConditions();

        condIdCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<TwoRecord, Number> col) -> col.getValue().getValue().id);
        condNameCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<TwoRecord, String> col) -> col.getValue().getValue().name);
        condAbbCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<TwoRecord, String> col) -> col.getValue().getValue().description);

        final TreeItem<TwoRecord> root = new RecursiveTreeItem<>(conditionList, RecursiveTreeObject::getChildren);
        conditionTTV.getColumns().setAll(condIdCol,condNameCol,condAbbCol);
        conditionTTV.setRoot(root);
        conditionTTV.setShowRoot(false);
    }

    //Create all Rarities
    private ObservableList<TwoRecord> generateRarities()
    {
        ObservableList<TwoRecord> rarityList = FXCollections.observableArrayList();
        List<Rarity> tempRarities = rarityDao.getAllRarities();

        for (Rarity rarities : tempRarities) {
            rarityList.add(new TwoRecord(rarities.getRarityID(),rarities.getName(),
                    rarities.getAbbr()));
        }

        return rarityList;
    }
    //Create add list to JFXTableTreeView rarityTTV
    private void createRarityList() {
        ObservableList<TwoRecord> rarityList = generateRarities();

        rarityIdCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<TwoRecord, Number> col) -> col.getValue().getValue().id);
        rarityNameCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<TwoRecord, String> col) -> col.getValue().getValue().name);
        rarityAbbCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<TwoRecord, String> col) -> col.getValue().getValue().description);

        final TreeItem<TwoRecord> root = new RecursiveTreeItem<>(rarityList, RecursiveTreeObject::getChildren);
        rarityTTV.getColumns().setAll(rarityIdCol,rarityNameCol,rarityAbbCol);
        rarityTTV.setRoot(root);
        rarityTTV.setShowRoot(false);
    }

    //Create all Cardsets
    private ObservableList<TwoRecord> generateCardsets()
    {
        ObservableList<TwoRecord> cardsetList = FXCollections.observableArrayList();
        List<Cardset> tempCardsets = cardSetDao.getAllSets();

        for (Cardset cardsets : tempCardsets) {
            cardsetList.add(new TwoRecord(cardsets.getSetID(),cardsets.getName(),
                    cardsets.getAbbr()));
        }

        return cardsetList;
    }
    //Create add list to JFXTableTreeView setTTV
    private void createCardsetList() {
        ObservableList<TwoRecord> cardsetList = generateCardsets();

        setIdCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<TwoRecord, Number> col) -> col.getValue().getValue().id);
        setNameCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<TwoRecord, String> col) -> col.getValue().getValue().name);
        setAbbCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<TwoRecord, String> col) -> col.getValue().getValue().description);

        final TreeItem<TwoRecord> root = new RecursiveTreeItem<>(cardsetList, RecursiveTreeObject::getChildren);
        setTTV.getColumns().setAll(setIdCol,setNameCol,setAbbCol);
        setTTV.setRoot(root);
        setTTV.setShowRoot(false);
    }

    //Create all Subtypes
    private ObservableList<OneRecord> generateSubtypes()
    {
        ObservableList<OneRecord> subtypeList = FXCollections.observableArrayList();
        List<Subtype> tempSubtypes = subtypeDao.getAllSubtypes();

        for (Subtype subtypes : tempSubtypes) {
            subtypeList.add(new OneRecord(subtypes.getSubTypeID(),subtypes.getName()));
        }
        return subtypeList;
    }
    //Create add list to JFXTableTreeView subTypeTTV
    private void createSubtypeList() {
        ObservableList<OneRecord> subtypeList = generateSubtypes();

        subTypeIdCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<OneRecord, Number> col) -> col.getValue().getValue().id);
        subTypeNameCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<OneRecord, String> col) -> col.getValue().getValue().name);

        final TreeItem<OneRecord> root = new RecursiveTreeItem<>(subtypeList, RecursiveTreeObject::getChildren);
        subTypeTTV.getColumns().setAll(subTypeIdCol,subTypeNameCol);
        subTypeTTV.setRoot(root);
        subTypeTTV.setShowRoot(false);
    }

    //Create all Types
    private ObservableList<OneRecord> generateTypes()
    {
        ObservableList<OneRecord> typeList = FXCollections.observableArrayList();
        List<Type> tempTypes = typeDao.getAllTypes();

        for (Type types : tempTypes) {
            typeList.add(new OneRecord(types.getTypeID(),types.getName()));
        }
        return typeList;
    }
    //Create add list to JFXTableTreeView typeTTV
    private void createTypeList() {
        ObservableList<OneRecord> typeList = generateTypes();

        typeIdCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<OneRecord, Number> col) -> col.getValue().getValue().id);
        typeNameCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<OneRecord, String> col) -> col.getValue().getValue().name);

        final TreeItem<OneRecord> root = new RecursiveTreeItem<>(typeList, RecursiveTreeObject::getChildren);
        typeTTV.getColumns().setAll(typeIdCol,typeNameCol);
        typeTTV.setRoot(root);
        typeTTV.setShowRoot(false);
    }

    //Create all Cards
    private ObservableList<CardRecord> generateCards()
    {
        ObservableList<CardRecord> cardList = FXCollections.observableArrayList();
        List<Card> tempCards = cardDao.getAllCards();

        for (Card cards : tempCards) {
            cardList.add(new CardRecord(cards.getCardID(),cards.getName(),cards.isFirstEd(),cards.getDescription(),cards.getImageUrl(),
                    cards.getType().getName(),cards.getSubtype().getName(),cards.getRarity().getName(),cards.getLanguage().getName(),
                    cards.getCardset().getName(),cards.getCondition().getName()));
        }
        return cardList;
    }
    //Create add list to JFXTableTreeView cardTTV
    private void createCardList() {
        ObservableList<CardRecord> cardList = generateCards();

        cardIdCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<CardRecord, Number> col) -> col.getValue().getValue().cardID);
        cardNameCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<CardRecord, String> col) -> col.getValue().getValue().name);
        cardFirstEdCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<CardRecord, Boolean> col) -> col.getValue().getValue().firstEd);
        cardDescCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<CardRecord, String> col) -> col.getValue().getValue().description);
        carImgURLCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<CardRecord, String> col) -> col.getValue().getValue().imageUrl);
        cardTypeCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<CardRecord, String> col) -> col.getValue().getValue().type);
        cardSubTypeCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<CardRecord, String> col) -> col.getValue().getValue().subtype);
        cardRarityCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<CardRecord, String> col) -> col.getValue().getValue().rarity);
        cardLangCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<CardRecord, String> col) -> col.getValue().getValue().language);
        cardSetCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<CardRecord, String> col) -> col.getValue().getValue().cardset);
        cardCondCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<CardRecord, String> col) -> col.getValue().getValue().condition);

        final TreeItem<CardRecord> root = new RecursiveTreeItem<>(cardList, RecursiveTreeObject::getChildren);
        cardTTV.getColumns().setAll(cardIdCol,cardNameCol,cardFirstEdCol,cardDescCol,carImgURLCol,cardTypeCol,cardSubTypeCol,cardRarityCol,cardLangCol,
                cardSetCol,cardCondCol);
        cardTTV.setRoot(root);
        cardTTV.setShowRoot(false);
    }

    //Create all Users
    private ObservableList<UserRecord> generateUsers()
    {
        ObservableList<UserRecord> usersList = FXCollections.observableArrayList();
        List<User> tempUsers = userDao.getAllUsers();

        for (User users : tempUsers) {
            usersList.add(new UserRecord(users.getUserID(),users.getUsername(),users.getPassword(),users.getCardsBought(),
                    users.getCardsSold(),users.getEmail(),users.getShippingAddressTemplate().getAddress(),users.getCredibility().getName()));
        }
        return usersList;
    }
    //Create add list to JFXTableTreeView userTTV
    private void createUserList() {
        ObservableList<UserRecord> userList = generateUsers();

        userIdCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<UserRecord, Number> col) -> col.getValue().getValue().userID);
        userNameCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<UserRecord, String> col) -> col.getValue().getValue().username);
        userPassCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<UserRecord, String> col) -> col.getValue().getValue().password);
        userBoughtCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<UserRecord, Number> col) -> col.getValue().getValue().cardsBought);
        userSoldCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<UserRecord, Number> col) -> col.getValue().getValue().cardsSold);
        userEmailCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<UserRecord, String> col) -> col.getValue().getValue().email);
        userAddCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<UserRecord, String> col) -> col.getValue().getValue().address);
        userCredCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<UserRecord, String> col) -> col.getValue().getValue().credibility);

        final TreeItem<UserRecord> root = new RecursiveTreeItem<>(userList, RecursiveTreeObject::getChildren);
        userTTV.getColumns().setAll(userIdCol,userNameCol,userPassCol,userBoughtCol,userSoldCol,userEmailCol,userAddCol,userCredCol);
        userTTV.setRoot(root);
        userTTV.setShowRoot(false);
    }

}
