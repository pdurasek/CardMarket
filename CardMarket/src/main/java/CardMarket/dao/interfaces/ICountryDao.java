package CardMarket.dao.interfaces;

import CardMarket.models.Country;

import java.util.List;

public interface ICountryDao
{
   public List getAllCountries();

   public Country getCountry(int countryID);

   public void updateCountry(Country country);

   public void deleteCountry(Country country);
}
