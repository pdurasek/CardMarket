package CardMarket.dao.interfaces;

import CardMarket.models.ShippingAddress;

import java.util.List;

public interface IShippingAddressDao
{
   public List getAllShippingAddresses();

   public ShippingAddress getShippingAddress(int shippingAddressID);

   public void updateShippingAddress(ShippingAddress shippingAddress);

   public void deleteShippingAddress(ShippingAddress shippingAddress);
}
