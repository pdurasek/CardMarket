package CardMarket.dao.implementations;

import CardMarket.dao.SessionCreator;
import CardMarket.dao.interfaces.ISubtypeDao;
import CardMarket.models.Subtype;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SubtypeDao implements ISubtypeDao
{
   private Session session = null;

   public SubtypeDao()
   {
      session = SessionCreator.getSession();
   }

   @Override
   public boolean createSubType(Subtype subtype) {
      Transaction transaction = null;
      try
      {
         transaction = session.beginTransaction();
         session.save(subtype);
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
   public List getAllSubtypes()
   {
      return session.createQuery("FROM Subtype ").list();
   }

   @Override
   public Subtype getSubtype(int subtypeID)
   {
      return session.get(Subtype.class, subtypeID);
   }

   @Override
   public void updateSubtype(Subtype subtype)
   {
      session.update(subtype);
   }

   @Override
   public void deleteSubtype(Subtype subtype)
   {
      session.delete(subtype);
   }
}
