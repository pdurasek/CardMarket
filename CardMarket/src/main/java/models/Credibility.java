package models;

public class Credibility
{
   private int credibilityID;
   private String name;
   private String description;

   public int getCredibilityID()
   {
      return credibilityID;
   }

   public void setCredibilityID(int credibilityID)
   {
      this.credibilityID = credibilityID;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }
}
