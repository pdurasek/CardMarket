package dao.implementations;

import dao.SessionCreator;
import dao.interfaces.ISubtypeDao;
import models.Subtype;
import org.hibernate.Session;

import java.util.List;

public class SubtypeDao implements ISubtypeDao
{
   private Session session = null;

   public SubtypeDao()
   {
      session = SessionCreator.getSession();
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
