package CardMarket.dao.implementations;

import CardMarket.dao.SessionCreator;
import CardMarket.dao.interfaces.IShippingAddressDao;
import CardMarket.models.ShippingAddress;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ShippingAddressDao implements IShippingAddressDao
{
   private Session session = null;

   public ShippingAddressDao()
   {
      session = SessionCreator.getSession();
   }

   @Override
   public List getAllShippingAddresses()
   {
      return session.createQuery("FROM ShippingAddress ").list();
   }

   @Override
   public ShippingAddress getShippingAddress(int shippingAddressID)
   {
      return session.get(ShippingAddress.class, shippingAddressID);
   }

   @Override
   public void updateShippingAddress(ShippingAddress shippingAddress)
   {
      session.update(shippingAddress);
   }

   @Override
   public void deleteShippingAddress(ShippingAddress shippingAddress)
   {
      session.delete(shippingAddress);
   }

   @Override
   public boolean createShippingAddress(ShippingAddress shippingAddress)
   {
      Transaction transaction = null;
      try
      {
         transaction = session.beginTransaction();
         session.save(shippingAddress);
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
}
