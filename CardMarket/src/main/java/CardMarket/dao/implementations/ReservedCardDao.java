package CardMarket.dao.implementations;

import CardMarket.dao.SessionCreator;
import CardMarket.dao.interfaces.IReservedCardDao;
import CardMarket.models.ReservedCard;
import CardMarket.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReservedCardDao implements IReservedCardDao
{
   private Session session = null;

   public ReservedCardDao()
   {
      session = SessionCreator.getSession();
   }

   @Override
   public boolean createReservedCard(ReservedCard reservedCard)
   {
      Transaction transaction = null;
      try
      {
         transaction = session.beginTransaction();
         session.save(reservedCard);
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
   public List getAllReservedCards()
   {
      return session.createQuery("FROM ReservedCard ").list();
   }

   @Override
   public List getAllReservedCards(User user)
   {
      Query query = session.createQuery("FROM ReservedCard WHERE user.userID = :userID");
      query.setParameter("userID", user.getUserID());

      return query.list();
   }

   @Override
   public ReservedCard getReservedCard(int reservedCardID)
   {
      return session.get(ReservedCard.class, reservedCardID);
   }

   @Override
   public ReservedCard getReservedCard(int cardOfferID, int userID)
   {
      Query query = session.createQuery("FROM ReservedCard WHERE cardOffer.cardOfferID = :cardOfferID AND user.userID = :userID");
      query.setParameter("cardOfferID", cardOfferID);
      query.setParameter("userID", userID);

      return (ReservedCard) query.uniqueResult();
   }

   @Override
   public boolean updateReservedCard(ReservedCard reservedCard)
   {
      Transaction transaction = null;
      try
      {
         transaction = session.beginTransaction();
         session.update(reservedCard);
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
   public boolean deleteReservedCard(ReservedCard reservedCard)
   {
      Transaction transaction = null;
      try
      {
         transaction = session.beginTransaction();
         session.delete(reservedCard);

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
}
