package models;

import java.util.Set;

public class Condition
{
   private int conditionId;
   private String name;
   private String abbr;
   private Set cards;

   public Condition()
   {
   }

   public Condition(int conditionId, String name, String abbr)
   {
      this.conditionId = conditionId;
      this.name = name;
      this.abbr = abbr;
   }

   public int getConditionId()
   {
      return conditionId;
   }

   public void setConditionId(int conditionId)
   {
      this.conditionId = conditionId;
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
