package CardMarket.dao.implementations;

import CardMarket.dao.SessionCreator;
import CardMarket.dao.interfaces.IShippingAddressDao;
import CardMarket.models.ShippingAddress;
import org.hibernate.Session;

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
}
