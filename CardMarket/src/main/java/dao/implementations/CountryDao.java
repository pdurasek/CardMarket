package dao.implementations;

import dao.SessionCreator;
import dao.interfaces.ICountryDao;
import models.Country;
import org.hibernate.Session;

import java.util.List;

public class CountryDao implements ICountryDao
{
   private Session session = null;

   public CountryDao()
   {
      session = SessionCreator.getSession();
   }

   @Override
   public List getAllCountries()
   {
      return session.createQuery("FROM Country ").list();
   }

   @Override
   public Country getCountry(int countryID)
   {
      return session.get(Country.class, countryID);
   }

   @Override
   public void updateCountry(Country country)
   {
      session.update(country);
   }

   @Override
   public void deleteCountry(Country country)
   {
      session.delete(country);
   }
}
