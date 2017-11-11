package dao.interfaces;

import models.ShippingAddress;

import java.util.List;

public interface IShippingAddressDao
{
   public List getAllShippingAddresses();

   public ShippingAddress getShippingAddress(int shippingAddressID);

   public void updateShippingAddress(ShippingAddress shippingAddress);

   public void deleteShippingAddress(ShippingAddress shippingAddress);
}
