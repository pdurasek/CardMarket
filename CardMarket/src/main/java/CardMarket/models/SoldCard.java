package CardMarket.models;

public class SoldCard
{
   private int soldCardID;
   private int quantity;
   private double price;
   private Card card;
   private User buyer;
   private User seller;
   private Order order;

   public int getSoldCardID()
   {
      return soldCardID;
   }

   public void setSoldCardID(int soldCardID)
   {
      this.soldCardID = soldCardID;
   }

   public int getQuantity()
   {
      return quantity;
   }

   public void setQuantity(int quantity)
   {
      this.quantity = quantity;
   }

   public double getPrice()
   {
      return price;
   }

   public void setPrice(double price)
   {
      this.price = price;
   }

   public Card getCard()
   {
      return card;
   }

   public void setCard(Card card)
   {
      this.card = card;
   }

   public User getBuyer()
   {
      return buyer;
   }

   public void setBuyer(User buyer)
   {
      this.buyer = buyer;
   }

   public User getSeller()
   {
      return seller;
   }

   public void setSeller(User seller)
   {
      this.seller = seller;
   }

   public Order getOrder()
   {
      return order;
   }

   public void setOrder(Order order)
   {
      this.order = order;
   }
}
