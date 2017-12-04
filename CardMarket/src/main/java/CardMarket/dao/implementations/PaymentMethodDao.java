package CardMarket.dao.implementations;

import CardMarket.dao.SessionCreator;
import CardMarket.dao.interfaces.IPaymentMethodDao;
import CardMarket.models.PaymentMethod;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PaymentMethodDao implements IPaymentMethodDao
{
   private Session session = null;

   public PaymentMethodDao()
   {
      session = SessionCreator.getSession();
   }

   @Override
   public boolean createPaymentMethod(PaymentMethod paymentMethod) {
      Transaction transaction = null;
      try
      {
         transaction = session.beginTransaction();
         session.save(paymentMethod);
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
