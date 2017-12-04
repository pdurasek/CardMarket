package CardMarket.dao.interfaces;

import CardMarket.models.PaymentMethod;

import java.util.List;

public interface IPaymentMethodDao
{
   public boolean createPaymentMethod(PaymentMethod paymentMethod);

   public List getAllPaymentMethods();

   public PaymentMethod getPaymentMethod(int paymentMethodID);

   public void updatePaymentMethod(PaymentMethod paymentMethod);

   public void deletePaymentMethod(PaymentMethod paymentMethod);
}
