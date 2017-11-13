package dao.implementations;

import dao.SessionCreator;
import dao.interfaces.ISoldCard;
import models.SoldCard;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SoldCardDao implements ISoldCard
{
   private Session session = null;

   public SoldCardDao()
   {
      session = SessionCreator.getSession();
   }

   @Override
   public List getAllSoldCards()
   {
      return session.createQuery("FROM SoldCard ").list();
   }

   @Override
   public List getAllSoldCard(int startIndex, int pageSize)
   {
      Query query = session.createQuery("FROM SoldCard ");
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);

      return query.list();
   }

   @Override
   public SoldCard getSoldCard(int soldCardID)
   {
      return session.get(SoldCard.class, soldCardID);
   }

   @Override
   public void updateSoldCard(SoldCard soldCard)
   {
      session.update(soldCard);
   }

   @Override
   public void deleteSoldCard(SoldCard soldCard)
   {
      session.delete(soldCard);
   }
}
