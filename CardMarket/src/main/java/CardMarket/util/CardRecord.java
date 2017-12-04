package CardMarket.util;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;

public class CardRecord extends RecursiveTreeObject<CardRecord> {

    public final IntegerProperty cardID;
    public final StringProperty name;
    public final BooleanProperty firstEd;
    public final StringProperty description;
    public final StringProperty imageUrl;
    public final StringProperty type;
    public final StringProperty subtype;
    public final StringProperty rarity;
    public final StringProperty language;
    public final StringProperty cardset;
    public final StringProperty condition;

    public CardRecord(int cardID, String name, boolean firstEd, String description, String imageUrl, String type,
                      String subtype, String rarity, String language, String cardset, String condition)
    {
        this.cardID = new SimpleIntegerProperty(cardID);
        this.name = new SimpleStringProperty(name);
        this.firstEd = new SimpleBooleanProperty(firstEd);
        this.description = new SimpleStringProperty(description);
        this.imageUrl = new SimpleStringProperty(imageUrl);
        this.type = new SimpleStringProperty(type);
        this.subtype = new SimpleStringProperty(subtype);
        this.rarity = new SimpleStringProperty(rarity);
        this.language = new SimpleStringProperty(language);
        this.cardset = new SimpleStringProperty(cardset);
        this.condition = new SimpleStringProperty(condition);
    }

}
