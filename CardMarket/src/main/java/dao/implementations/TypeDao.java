package dao.implementations;

import dao.SessionCreator;
import dao.interfaces.ITypeDao;
import models.Type;
import org.hibernate.Session;

import java.util.List;

public class TypeDao implements ITypeDao
{
   private Session session = null;

   public TypeDao()
   {
      session = SessionCreator.getSession();
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
