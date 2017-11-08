package models;

public class Card
{
   private int cardID;
   private String name;
   private boolean firstEd;
   private String description;
   private String imageUrl;
   /*private Type type;
   private Subtype subtype;
   private Rarity rarity;
   private Language language;
   private Cardset set;
   private Condition condition;*/

   /*private int typeID;
   private int subTypeID;
   private int rarityID;
   private int languageID;
   private int setID;
   private int conditionId;*/

   public int getCardID()
   {
      return cardID;
   }

   public void setCardID(int cardID)
   {
      this.cardID = cardID;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public boolean isFirstEd()
   {
      return firstEd;
   }

   public void setFirstEd(boolean firstEd)
   {
      this.firstEd = firstEd;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public String getImageUrl()
   {
      return imageUrl;
   }

   public void setImageUrl(String imageUrl)
   {
      this.imageUrl = imageUrl;
   }
}
