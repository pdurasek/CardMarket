package dao.implementations;

import dao.SessionCreator;
import dao.interfaces.IPaymentMethodDao;
import models.PaymentMethod;
import org.hibernate.Session;

import java.util.List;

public class PaymentMethodDao implements IPaymentMethodDao
{
   private Session session = null;

   public PaymentMethodDao()
   {
      session = SessionCreator.getSession();
   }

   @Override
   public List getAllPaymentMethods()
   {
      return session.createQuery("FROM PaymentMethod ").list();
   }

   @Override
   public PaymentMethod getPaymentMethod(int paymentMethodID)
   {
      return session.get(PaymentMethod.class, paymentMethodID);
   }

   @Override
   public void updatePaymentMethod(PaymentMethod paymentMethod)
   {
      session.update(paymentMethod);
   }

   @Override
   public void deletePaymentMethod(PaymentMethod paymentMethod)
   {
      session.delete(paymentMethod);
   }
}
