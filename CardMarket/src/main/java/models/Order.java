package models;

public class Order
{
   private int orderID;
   private String details;
   private User user;
   private ShippingAddress shippingAddress;
   private PaymentMethod paymentMethod;

   public int getOrderID()
   {
      return orderID;
   }

   public void setOrderID(int orderID)
   {
      this.orderID = orderID;
   }

   public String getDetails()
   {
      return details;
   }

   public void setDetails(String details)
   {
      this.details = details;
   }

   public User getUser()
   {
      return user;
   }

   public void setUser(User user)
   {
      this.user = user;
   }

   public ShippingAddress getShippingAddress()
   {
      return shippingAddress;
   }

   public void setShippingAddress(ShippingAddress shippingAddress)
   {
      this.shippingAddress = shippingAddress;
   }

   public PaymentMethod getPaymentMethod()
   {
      return paymentMethod;
   }

   public void setPaymentMethod(PaymentMethod paymentMethod)
   {
      this.paymentMethod = paymentMethod;
   }
}
