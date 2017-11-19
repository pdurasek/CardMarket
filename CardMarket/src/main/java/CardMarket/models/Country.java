package CardMarket.models;

public class Country
{
   private int countryID;
   private String name;
   private String abbr;

   public int getCountryID()
   {
      return countryID;
   }

   public void setCountryID(int countryID)
   {
      this.countryID = countryID;
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
