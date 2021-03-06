package CardMarket.models;

public class ShippingAddress
{
   private int addressID;
   private String address;
   private String zipcode;
   private String city;
   private Country country;

   public ShippingAddress()
   {
   }

   public ShippingAddress(String address, String zipcode, String city, Country country)
   {
      this.address = address;
      this.zipcode = zipcode;
      this.city = city;
      this.country = country;
   }

   public int getAddressID()
   {
      return addressID;
   }

   public void setAddressID(int addressID)
   {
      this.addressID = addressID;
   }

   public String getAddress()
   {
      return address;
   }

   public void setAddress(String address)
   {
      this.address = address;
   }

   public String getZipcode()
   {
      return zipcode;
   }

   public void setZipcode(String zipcode)
   {
      this.zipcode = zipcode;
   }

   public String getCity()
   {
      return city;
   }

   public void setCity(String city)
   {
      this.city = city;
   }

   public Country getCountry()
   {
      return country;
   }

   public void setCountry(Country country)
   {
      this.country = country;
   }
}
