package CardMarket.dao.interfaces;

import CardMarket.models.ShippingAddressTemplate;

import java.util.List;

public interface IShippingAddressTemplateDao
{
   public List getAllShippingAddressTemplates();

   public ShippingAddressTemplate getShippingAddressTemplate(int shippingAddressTemplateID);

   public boolean updateShippingAddressTemplate(ShippingAddressTemplate shippingAddressTemplate);

   public boolean createShippingAddressTemplate(ShippingAddressTemplate shippingAddressTemplate);

   public void deleteShippingAddressTemplate(ShippingAddressTemplate shippingAddressTemplate);

}
