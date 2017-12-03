package CardMarket.dao.implementations;

import CardMarket.dao.SessionCreator;
import CardMarket.dao.interfaces.ICardOfferDao;
import CardMarket.models.CardOffer;
import CardMarket.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CardOfferDao implements ICardOfferDao
{
   private Session session = null;

   public CardOfferDao()
   {
      session = SessionCreator.getSession();
   }

   @Override
   public List getAllCardOffers(String cardName)
   {
      Query query = session.createQuery("FROM CardOffer WHERE card.name = :cardName");
      query.setParameter("cardName", cardName);

      return query.list();
   }

   @Override
   public List getAllCardOffers(int startIndex, int pageSize, String cardName)
   {
      Query query = session.createQuery("FROM CardOffer ");
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);

      return query.list();
   }

   @Override
   public List getAllUserCardOffers(String username)
   {
      Query query = session.createQuery("FROM CardOffer WHERE user.username = :userID");
      query.setParameter("userID", username);

      return query.list();
   }

   @Override
   public CardOffer getCardOffer(int cardOfferID)
   {
      return session.get(CardOffer.class, cardOfferID);
   }

   @Override
   public boolean createCardOffer(CardOffer cardOffer)
   {
      Transaction transaction = null;
      try
      {
         transaction = session.beginTransaction();
         session.save(cardOffer);
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
   public boolean updateCardOffer(CardOffer cardOffer)
   {
      Transaction transaction = null;
      try
      {
         transaction = session.beginTransaction();
         session.update(cardOffer);
         transaction.commit();
      }
      catch (Exception e)
      {
         if (transaction != null)
         {
            transaction.rollback();
         }

         return false;
      }

      return true;
   }

   @Override
   public boolean deleteCardOffer(CardOffer cardOffer)
   {
      Transaction transaction = null;
      try
      {
         transaction = session.beginTransaction();
         session.delete(cardOffer);
         transaction.commit();
      }
      catch (Exception e)
      {
         if (transaction != null)
         {
            transaction.rollback();
         }

         return false;
      }

      return true;
   }

   @Override
   public double getAverageCost(String cardName)
   {
      Query query = session.createQuery("SELECT AVG (price) FROM CardOffer WHERE card.name = :cardName");
      query.setParameter("cardName", cardName);

      double avgCost = 0;
      if(query.uniqueResult() != null)
      {
         avgCost = (double) query.uniqueResult();
      }

      return avgCost;

   }
}
