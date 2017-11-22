package CardMarket.controllers;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;

public class AdminController
{
    @FXML
    private JFXTreeTableView<Karte> treeTableView;
    @FXML
    private JFXTreeTableColumn<Karte, String> id,name,firstEd,desc,imgURL,type,subtype,rarity,language,set,condition;

    //Create Cards
    private TreeItem<Karte> kartica1 = new TreeItem<>(new Karte("1","Double-Dragon","True","Ovo je najjaca","/home/najjaca.png",
            "Dragon","Fire","Legendary","Chinese","Dragon Set","Ultimate"));
    private TreeItem<Karte> kartica2 = new TreeItem<>(new Karte("2","Freezer","False","Freeze","/home/freeze.png",
            "Machine","Ice","Rare","Croatian","Ice Set","Good"));
    private TreeItem<Karte> kartica3 = new TreeItem<>(new Karte("3","Ninja","False","Shurikeeenn","/home/ninja.png",
            "Human","Gay","Common","English","Gay Set","Bad"));

    private TreeItem<Karte> root = new TreeItem<>(new Karte("id","name","first","desc","img URL","type",
            "subtype","rarity","language","set","condition"));

    @FXML
    public void initialize() {
        root.getChildren().setAll(kartica1,kartica2,kartica3);

        id.setCellValueFactory(param -> param.getValue().getValue().IDProperty());
        name.setCellValueFactory(param -> param.getValue().getValue().nameProperty());
        firstEd.setCellValueFactory(param -> param.getValue().getValue().firstEdProperty());
        desc.setCellValueFactory(param -> param.getValue().getValue().descProperty());
        imgURL.setCellValueFactory(param -> param.getValue().getValue().imgURLProperty());
        type.setCellValueFactory(param -> param.getValue().getValue().typeProperty());
        subtype.setCellValueFactory(param -> param.getValue().getValue().subtypeProperty());
        rarity.setCellValueFactory(param -> param.getValue().getValue().rarityProperty());
        language.setCellValueFactory(param -> param.getValue().getValue().languageProperty());
        set.setCellValueFactory(param -> param.getValue().getValue().setProperty());
        condition.setCellValueFactory(param -> param.getValue().getValue().conditionProperty());

        treeTableView.setRoot(root);
        treeTableView.setShowRoot(false);
    }

    //Karte Class
    private static final class Karte extends RecursiveTreeObject<Karte> {
        final SimpleStringProperty ID;
        final SimpleStringProperty name;
        final SimpleStringProperty firstEd;
        final SimpleStringProperty desc;
        final SimpleStringProperty imgURL;
        final SimpleStringProperty type;
        final SimpleStringProperty subtype;
        final SimpleStringProperty rarity;
        final SimpleStringProperty language;
        final SimpleStringProperty set;
        final SimpleStringProperty condition;

        public SimpleStringProperty IDProperty() {
            return ID;
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public SimpleStringProperty firstEdProperty() {
            return firstEd;
        }

        public SimpleStringProperty descProperty() {
            return desc;
        }

        public SimpleStringProperty imgURLProperty() {
            return imgURL;
        }

        public SimpleStringProperty typeProperty() {
            return type;
        }

        public SimpleStringProperty subtypeProperty() {
            return subtype;
        }

        public SimpleStringProperty rarityProperty() {
            return rarity;
        }

        public SimpleStringProperty languageProperty() {
            return language;
        }

        public SimpleStringProperty setProperty() {
            return set;
        }

        public SimpleStringProperty conditionProperty() {
            return condition;
        }

        public Karte(String ID, String name, String firstEd, String desc, String imgURL, String type, String subtype,
                     String rarity, String language, String set, String condition) {
            this.ID = new SimpleStringProperty(ID);
            this.name = new SimpleStringProperty(name);
            this.firstEd = new SimpleStringProperty(firstEd);
            this.desc = new SimpleStringProperty(desc);
            this.imgURL = new SimpleStringProperty(imgURL);
            this.type = new SimpleStringProperty(type);
            this.subtype = new SimpleStringProperty(subtype);
            this.rarity = new SimpleStringProperty(rarity);
            this.language = new SimpleStringProperty(language);
            this.set = new SimpleStringProperty(set);
            this.condition = new SimpleStringProperty(condition);

        }
    }

}
