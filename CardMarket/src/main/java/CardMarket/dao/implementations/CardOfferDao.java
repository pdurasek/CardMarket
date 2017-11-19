package CardMarket.dao.implementations;

import CardMarket.dao.SessionCreator;
import CardMarket.dao.interfaces.ICardOfferDao;
import CardMarket.models.CardOffer;
import org.hibernate.Session;
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
   public List getAllCardOffers()
   {
      return session.createQuery("FROM CardOffer ").list();
   }

   @Override
   public List getAllCardOffers(int startIndex, int pageSize)
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
   public void updateCardOffer(CardOffer cardOffer)
   {
      session.update(cardOffer);
   }

   @Override
   public void deleteCardOffer(CardOffer cardOffer)
   {
      session.delete(cardOffer);
   }
}
