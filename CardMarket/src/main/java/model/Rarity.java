package model;

public class Rarity
{
   private int rarityID;
   private String name;
   private String abbr;

   public Rarity()
   {
   }

   public Rarity(int rarityID, String name, String abbr)
   {
      this.rarityID = rarityID;
      this.name = name;
      this.abbr = abbr;
   }

   public int getRarityID()
   {
      return rarityID;
   }

   public void setRarityID(int rarityID)
   {
      this.rarityID = rarityID;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getAbbr()
   {
      return abbr;
   }

   public void setAbbr(String abbr)
   {
      this.abbr = abbr;
   }
}
