package models;

import java.util.Set;

public class Type
{
   private int typeID;
   private String name;
   private Set cards;

   public Type()
   {
   }

   public Type(int typeID, String name)
   {
      this.typeID = typeID;
      this.name = name;
   }

   public int getTypeID()
   {
      return typeID;
   }

   public void setTypeID(int typeID)
   {
      this.typeID = typeID;
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
