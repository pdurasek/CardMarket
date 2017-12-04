package CardMarket.dao.implementations;

import CardMarket.dao.SessionCreator;
import CardMarket.dao.interfaces.ITypeDao;
import CardMarket.models.Type;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TypeDao implements ITypeDao
{
   private Session session = null;

   public TypeDao()
   {
      session = SessionCreator.getSession();
   }

   @Override
   public boolean createType(Type type) {
      Transaction transaction = null;
      try
      {
         transaction = session.beginTransaction();
         session.save(type);
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
   public List getAllTypes()
   {
      return session.createQuery("FROM Type ").list();
   }

   @Override
   public Type getType(int typeID)
   {
      return session.get(Type.class, typeID);
   }

   @Override
   public void updateType(Type type)
   {
      session.update(type);
   }

   @Override
   public void deleteType(Type type)
   {
      session.delete(type);
   }
}
