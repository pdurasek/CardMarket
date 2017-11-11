package dao.interfaces;

import models.PaymentMethod;

import java.util.List;

public interface IPaymentMethodDao
{
   public List getAllPaymentMethods();

   public PaymentMethod getPaymentMethod(int paymentMethodID);

   public void updatePaymentMethod(PaymentMethod paymentMethod);

   public void deletePaymentMethod(PaymentMethod paymentMethod);
}
