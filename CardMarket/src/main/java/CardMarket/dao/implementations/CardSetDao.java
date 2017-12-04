package CardMarket.dao.implementations;

import CardMarket.dao.SessionCreator;
import CardMarket.dao.interfaces.ICardSetDao;
import CardMarket.models.Cardset;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CardSetDao implements ICardSetDao
{
   private Session session = null;

   public CardSetDao()
   {
      session = SessionCreator.getSession();
   }

   @Override
   public boolean createCardset(Cardset cardset) {
      Transaction transaction = null;
      try
      {
         transaction = session.beginTransaction();
         session.save(cardset);
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
   public List getAllSets()
   {
      return session.createQuery("FROM Cardset ").list();
   }

   @Override
   public Cardset getCardSet(int cardSetID)
   {
      return session.get(Cardset.class, cardSetID);
   }

   @Override
   public void updateCardSet(Cardset cardset)
   {
      session.update(cardset);
   }

   @Override
   public void deleteCardSet(Cardset cardset)
   {
      session.delete(cardset);
   }
}
