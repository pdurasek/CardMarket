package models;

public class Card
{
   private int cardID;
   private String name;
   private boolean firstEd;
   private String description;
   private String imageUrl;
   private Type type;
   private Subtype subtype;
   private Rarity rarity;
   private Language language;
   private Cardset set;
   private Condition condition;


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

   public Type getType()
   {
      return type;
   }

   public void setType(Type type)
   {
      this.type = type;
   }

   public Subtype getSubtype()
   {
      return subtype;
   }

   public void setSubtype(Subtype subtype)
   {
      this.subtype = subtype;
   }

   public Rarity getRarity()
   {
      return rarity;
   }

   public void setRarity(Rarity rarity)
   {
      this.rarity = rarity;
   }

   public Language getLanguage()
   {
      return language;
   }

   public void setLanguage(Language language)
   {
      this.language = language;
   }

   public Cardset getSet()
   {
      return set;
   }

   public void setSet(Cardset set)
   {
      this.set = set;
   }

   public Condition getCondition()
   {
      return condition;
   }

   public void setCondition(Condition condition)
   {
      this.condition = condition;
   }

   @Override
   public String toString()
   {
      return getName();
   }
}
