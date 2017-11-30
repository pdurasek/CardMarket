package CardMarket.dao.implementations;

import CardMarket.dao.SessionCreator;
import CardMarket.dao.interfaces.IShippingAddressTemplateDao;
import CardMarket.models.ShippingAddressTemplate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ShippingAddressTemplateDao implements IShippingAddressTemplateDao
{
   private Session session = null;

   public ShippingAddressTemplateDao()
   {
      session = SessionCreator.getSession();
   }

   @Override
   public List getAllShippingAddressTemplates()
   {
      return session.createQuery("FROM ShippingAddressTemplate ").list();
   }

   @Override
   public ShippingAddressTemplate getShippingAddressTemplate(int shippingAddressTemplateID)
   {
      return session.get(ShippingAddressTemplate.class, shippingAddressTemplateID);
   }

   @Override
   public boolean updateShippingAddressTemplate(ShippingAddressTemplate shippingAddressTemplate)
   {
      Transaction transaction = null;
      try
      {
         transaction = session.beginTransaction();
         session.update(shippingAddressTemplate);
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
   public boolean createShippingAddressTemplate(ShippingAddressTemplate shippingAddressTemplate)
   {
      Transaction transaction = null;
      try
      {
         transaction = session.beginTransaction();
         session.save(shippingAddressTemplate);
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
   public void deleteShippingAddressTemplate(ShippingAddressTemplate shippingAddressTemplate)
   {
      session.delete(shippingAddressTemplate);
   }
}
