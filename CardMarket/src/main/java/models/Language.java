package models;

public class Language
{
   private int languageID;
   private String name;
   private String abbr;

   public Language()
   {
   }

   public Language(int languageID, String name, String abbr)
   {
      this.languageID = languageID;
      this.name = name;
      this.abbr = abbr;
   }

   public int getLanguageID()
   {
      return languageID;
   }

   public void setLanguageID(int languageID)
   {
      this.languageID = languageID;
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