package CardMarket.dao.implementations;

import CardMarket.dao.SessionCreator;
import CardMarket.dao.interfaces.IRarityDao;
import CardMarket.models.Rarity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RarityDao implements IRarityDao
{
   private Session session = null;

   public RarityDao()
   {
      session = SessionCreator.getSession();
   }

   @Override
   public boolean createRarity(Rarity rarity) {
      Transaction transaction = null;
      try
      {
         transaction = session.beginTransaction();
         session.save(rarity);
         transaction.commit();
      }
      catch (Exception e)
      {
         if(transaction != null)
         {
            transaction.rollback();
         }

         return false;
      }

      return true;
   }

   @Override
   public List getAllRarities()
   {
      return session.createQuery("FROM Rarity ").list();
   }

   @Override
   public List getAllRarityNames()
   {
      return session.createQuery("SELECT name FROM Rarity ").list();
   }

   @Override
   public Rarity getRarity(int rarityID)
   {
      return session.get(Rarity.class, rarityID);
   }

   @Override
   public void updateRarity(Rarity rarity)
   {
      session.update(rarity);
   }

   @Override
   public void deleteRarity(Rarity rarity)
   {
      session.delete(rarity);
   }
}
