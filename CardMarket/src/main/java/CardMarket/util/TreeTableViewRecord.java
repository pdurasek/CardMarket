package CardMarket.util;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;

public final class TreeTableViewRecord extends RecursiveTreeObject<TreeTableViewRecord>
{
   public final IntegerProperty id;
   public final StringProperty cardset;
   public final StringProperty cardname;
   public final StringProperty rarity;
   public final StringProperty condition;
   public final StringProperty language;
   public final DoubleProperty price;
   public final IntegerProperty quantity;
   public final StringProperty seller;

   public TreeTableViewRecord(int id, String cardset, String cardname, String rarity, String condition, String language,
                          double price, int quantity, String seller)
   {
      this.id = new SimpleIntegerProperty(id);
      this.cardset = new SimpleStringProperty(cardset);
      this.cardname = new SimpleStringProperty(cardname);
      this.rarity = new SimpleStringProperty(rarity);
      this.condition = new SimpleStringProperty(condition);
      this.language = new SimpleStringProperty(language);
      this.price = new SimpleDoubleProperty(price);
      this.quantity = new SimpleIntegerProperty(quantity);
      this.seller = new SimpleStringProperty(seller);
   }
}
