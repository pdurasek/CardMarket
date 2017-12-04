package CardMarket.models;

public class PaymentMethod
{
   private int paymentMethodID;
   private String name;
   private String description;

   public int getPaymentMethodID()
   {
      return paymentMethodID;
   }

   public void setPaymentMethodID(int paymentMethodID)
   {
      this.paymentMethodID = paymentMethodID;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   @Override
   public String toString()
   {
      return name;
   }
}
