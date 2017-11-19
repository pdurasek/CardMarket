package CardMarket.dao.implementations;

import CardMarket.dao.SessionCreator;
import CardMarket.dao.interfaces.ICredibilityDao;
import CardMarket.models.Credibility;
import org.hibernate.Session;

import java.util.List;

public class CredibilityDao implements ICredibilityDao
{
   private Session session = null;

   public CredibilityDao()
   {
      session = SessionCreator.getSession();
   }


   @Override
   public List getAllCredibilities()
   {
      return session.createQuery("FROM Credibility ").list();
   }

   @Override
   public Credibility getCredibility(int credibilityID)
   {
      return session.get(Credibility.class, credibilityID);
   }

   @Override
   public void updateCredibility(Credibility credibility)
   {
      session.update(credibility);
   }

   @Override
   public void deleteCredibility(Credibility credibility)
   {
      session.delete(credibility);
   }
}
