package dao.implementations;

import dao.SessionCreator;
import dao.interfaces.IShippingAddressTemplateDao;
import models.ShippingAddressTemplate;
import org.hibernate.Session;

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
   public void updateShippingAddressTemplate(ShippingAddressTemplate shippingAddressTemplate)
   {
      session.update(shippingAddressTemplate);
   }

   @Override
   public void deleteShippingAddressTemplate(ShippingAddressTemplate shippingAddressTemplate)
   {
      session.delete(shippingAddressTemplate);
   }
}
