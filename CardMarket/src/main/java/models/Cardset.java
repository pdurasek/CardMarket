package models;

import java.util.Set;

public class Cardset
{
   private int setID;
   private String name;
   private String abbr;
   private Set cards;

   public Cardset()
   {
   }

   public Cardset(int setID, String name, String abbr)
   {
      this.setID = setID;
      this.name = name;
      this.abbr = abbr;
   }

   public int getSetID()
   {
      return setID;
   }

   public void setSetID(int setID)
   {
      this.setID = setID;
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
}
