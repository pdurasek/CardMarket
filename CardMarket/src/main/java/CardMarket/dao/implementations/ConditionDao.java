package CardMarket.dao.implementations;

import CardMarket.dao.SessionCreator;
import CardMarket.dao.interfaces.IConditionDao;
import CardMarket.models.Condition;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ConditionDao implements IConditionDao
{
   private Session session = null;

   public ConditionDao()
   {
      session = SessionCreator.getSession();
   }

   @Override
   public boolean createCondition(Condition condition) {
      Transaction transaction = null;
      try
      {
         transaction = session.beginTransaction();
         session.save(condition);
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
   public List getAllConditions()
   {
      return session.createQuery("FROM Condition ").list();
   }

   @Override
   public Condition getConditions(int conditionID)
   {
      return session.get(Condition.class, conditionID);
   }

   @Override
   public void updateCondition(Condition condition)
   {
      session.update(condition);
   }

   @Override
   public void deleteCondition(Condition condition)
   {
      session.delete(condition);
   }
}
