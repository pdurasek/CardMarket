package dao.implementations;

import dao.SessionCreator;
import dao.interfaces.ICardDao;
import models.Card;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CardDao implements ICardDao
{
   private Session session = null;

   public CardDao()
   {
      session = SessionCreator.getSession();
   }

   @Override
   public List getAllCards()
   {
      return session.createQuery("FROM Card ").list();
   }

   @Override
   public List getAllCards(int startIndex, int pageSize)
   {
      Query query = session.createQuery("FROM Card ");
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);

      return query.list();
   }

   @Override
   public List getAllCardsLike(String pattern)
   {
      Query query = session.createQuery("FROM Card WHERE name LIKE :pattern");
      query.setParameter("pattern", "%" +pattern +"%");

      return query.list();
   }

   @Override
   public int getAllCardsCount()
   {
      return ((Long) session.createQuery("SELECT COUNT(DISTINCT name) FROM Card ").uniqueResult()).intValue();
   }

   @Override
   public Card getCard(int cardID)
   {
      return session.get(Card.class, cardID);
   }

   @Override
   public void updateCard(Card card)
   {
      session.update(card);
   }

   @Override
   public void deleteCard(Card card)
   {
      session.delete(card);
   }
}
