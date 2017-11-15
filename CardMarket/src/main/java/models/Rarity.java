package models;

import java.util.Set;

public class Rarity
{
   private int rarityID;
   private String name;
   private String abbr;
   private Set cards;

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

   public Set getCards()
   {
      return cards;
   }

   public void setCards(Set cards)
   {
      this.cards = cards;
   }

   @Override
   public String toString()
   {
      return getName();
   }
}
