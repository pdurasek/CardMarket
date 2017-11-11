package dao.interfaces;

import models.ShippingAddressTemplate;

import java.util.List;

public interface IShippingAddressTemplateDao
{
   public List getAllShippingAddressTemplates();

   public ShippingAddressTemplate getShippingAddressTemplate(int shippingAddressTemplateID);

   public void updateShippingAddressTemplate(ShippingAddressTemplate shippingAddressTemplate);

   public void deleteShippingAddressTemplate(ShippingAddressTemplate shippingAddressTemplate);
}
