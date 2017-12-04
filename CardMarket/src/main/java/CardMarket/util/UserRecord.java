package CardMarket.util;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;

public class UserRecord extends RecursiveTreeObject<UserRecord> {

    public final IntegerProperty userID;
    public final StringProperty username;
    public final StringProperty password;
    public final IntegerProperty cardsBought;
    public final IntegerProperty cardsSold;
    public final StringProperty email;
    public final StringProperty address;
    public final StringProperty credibility;

    public UserRecord(int userID, String username, String password, int cardsBought, int cardsSold, String email,
                      String address, String credibility) {
        this.userID = new SimpleIntegerProperty(userID);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.cardsBought = new SimpleIntegerProperty(cardsBought);
        this.cardsSold = new SimpleIntegerProperty(cardsSold);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
        this.credibility = new SimpleStringProperty(credibility);
    }

}
