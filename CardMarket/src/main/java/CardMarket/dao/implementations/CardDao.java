package CardMarket.dao.implementations;

import CardMarket.dao.SessionCreator;
import CardMarket.dao.interfaces.ICardDao;
import CardMarket.models.Card;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.type.BooleanType;

import java.util.ArrayList;
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
   public List getAllCards(int startIndex, int pageSize, int setValue)
   {
      Query query = session.createQuery("FROM Card WHERE cardset.setID = :setValue");
      if(setValue != -1)
      {
         query.setParameter("setValue", setValue);
      }
      else
      {
         query.setParameter("setValue", true, BooleanType.INSTANCE);
      }
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);

      return query.list();
   }

   @Override
   public List getAllCardsLike(String pattern, int setValue)
   {
      Query query = session.createQuery("FROM Card WHERE cardset.setID = :setValue AND name LIKE :pattern");
      if(setValue != -1)
      {
         query.setParameter("setValue", setValue);
      }
      else
      {
         query.setParameter("setValue", true, BooleanType.INSTANCE);
      }
      query.setParameter("pattern", "%" +pattern +"%");

      return query.list();
   }

   @Override
   public List getAllCardsLike(String pattern, int startIndex, int pageSize, int setValue)
   {
      Query query = session.createQuery("FROM Card WHERE cardset.setID = :setValue AND name LIKE :pattern");
      if(setValue != -1)
      {
         query.setParameter("setValue", setValue);
      }
      else
      {
         query.setParameter("setValue", true, BooleanType.INSTANCE);
      }
      query.setParameter("pattern", "%" +pattern +"%");
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);

      return query.list();
   }

   @Override
   public List getAllCardsFiltered(String pattern, int startIndex, int pageSize, String filterColumn, int filterValue,
                                   int setValue)
   {
      Query query;

      if (filterColumn.equalsIgnoreCase("rarity"))
      {
         query = session.createQuery("FROM Card WHERE rarity.rarityID = :filterValue AND cardset.setID = :setValue AND name LIKE :pattern");
      }
      else if (filterColumn.equalsIgnoreCase("type"))
      {
         query = session.createQuery("FROM Card WHERE type.typeID= :filterValue AND cardset.setID = :setValue AND name LIKE :pattern");
      }
      else if (filterColumn.equalsIgnoreCase("subtype"))
      {
         query = session.createQuery("FROM Card WHERE subtype.subTypeID= :filterValue AND cardset.setID = :setValue AND name LIKE :pattern");
      }
      else if (filterColumn.equalsIgnoreCase("condition"))
      {
         query = session.createQuery("FROM Card WHERE condition.conditionId = :filterValue AND cardset.setID = :setValue AND name LIKE :pattern");
      }
      else if (filterColumn.equalsIgnoreCase("language"))
      {
         query = session.createQuery("FROM Card WHERE language.languageID = :filterValue AND cardset.setID = :setValue AND name LIKE :pattern");
      }
      else
      {
         System.err.println("Unknown filter");
         return new ArrayList();
      }

      query.setParameter("filterValue", filterValue);
      if(setValue != -1)
      {
         query.setParameter("setValue", setValue);
      }
      else
      {
         query.setParameter("setValue", true, BooleanType.INSTANCE);
      }
      query.setParameter("pattern", "%" +pattern +"%");
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);

      return query.list();
   }

   @Override
   public int getAllCardsCount()
   {
      return ((Long) session.createQuery("SELECT COUNT(DISTINCT name) FROM Card ").uniqueResult()).intValue();
   }

   @Override
   public int getAllCardsCount(String pattern, int setValue)
   {
      Query query = session.createQuery("SELECT COUNT(DISTINCT name) FROM Card WHERE cardset.setID = :setValue AND name LIKE :pattern");
      if(setValue != -1)
      {
         query.setParameter("setValue", setValue);
      }
      else
      {
         query.setParameter("setValue", true, BooleanType.INSTANCE);
      }
      query.setParameter("pattern", "%" +pattern +"%");

      return ((Long) query.uniqueResult()).intValue();
   }

   @Override
   public int getAllCardsFilteredCount(String pattern, String filterColumn, int filterValue, int setValue)
   {
      Query query;

      if (filterColumn.equalsIgnoreCase("rarity"))
      {
         query = session.createQuery("SELECT COUNT(DISTINCT name) FROM Card WHERE rarity.rarityID = :filterValue AND cardset.setID = :setValue AND name LIKE :pattern");
      }
      else if (filterColumn.equalsIgnoreCase("type"))
      {
         query = session.createQuery("SELECT COUNT(DISTINCT name) FROM Card WHERE type.typeID = :filterValue AND cardset.setID = :setValue AND name LIKE :pattern");
      }
      else if (filterColumn.equalsIgnoreCase("subtype"))
      {
         query = session.createQuery("SELECT COUNT(DISTINCT name) FROM Card WHERE subtype.subTypeID = :filterValue AND cardset.setID = :setValue AND name LIKE :pattern");
      }
      else if (filterColumn.equalsIgnoreCase("condition"))
      {
         query = session.createQuery("SELECT COUNT(DISTINCT name) FROM Card WHERE condition.conditionId = :filterValue AND cardset.setID = :setValue AND name LIKE :pattern");
      }
      else if (filterColumn.equalsIgnoreCase("language"))
      {
         query = session.createQuery("SELECT COUNT(DISTINCT name) FROM Card WHERE language.languageID = :filterValue AND cardset.setID = :setValue AND name LIKE :pattern");
      }
      else
      {
         System.err.println("Unknown filter");
         return 0;
      }

      query.setParameter("filterValue", filterValue);
      if(setValue != -1)
      {
         query.setParameter("setValue", setValue);
      }
      else
      {
         query.setParameter("setValue", true, BooleanType.INSTANCE);
      }
      query.setParameter("pattern", "%" +pattern +"%");

      return ((Long) query.uniqueResult()).intValue();
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
