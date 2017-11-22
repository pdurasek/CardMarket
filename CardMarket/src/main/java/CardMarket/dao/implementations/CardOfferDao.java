package CardMarket.dao.implementations;

import CardMarket.dao.SessionCreator;
import CardMarket.dao.interfaces.ICardOfferDao;
import CardMarket.models.CardOffer;
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
      Query query = session.createQuery("FROM SoldCard ");
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);

      return query.list();
   }

   @Override
   public CardOffer getCardOffer(int cardOfferID)
   {
      return session.get(CardOffer.class, cardOfferID);
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
         if(transaction != null)
         {
            transaction.rollback();
         }

         return false;
      }

      return true;
   }

   @Override
   public void deleteCardOffer(CardOffer cardOffer)
   {
      session.delete(cardOffer);
   }
}
