package models;

import java.util.Set;

public class Subtype
{
   private int subTypeID;
   private String name;
   private Set cards;

   public Subtype()
   {
   }

   public Subtype(int subTypeID, String name)
   {
      this.subTypeID = subTypeID;
      this.name = name;
   }

   public int getSubTypeID()
   {
      return subTypeID;
   }

   public void setSubTypeID(int subTypeID)
   {
      this.subTypeID = subTypeID;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
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
