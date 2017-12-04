package CardMarket.models;

public class SoldCard
{
   private int soldCardID;
   private int quantity;
   private double price;
   private Card card;
   private User buyer;
   private User seller;
   private CardOrder order;

   public SoldCard()
   {
   }

   public SoldCard(int quantity, double price, Card card, User buyer, User seller, CardOrder order)
   {
      this.quantity = quantity;
      this.price = price;
      this.card = card;
      this.buyer = buyer;
      this.seller = seller;
      this.order = order;
   }

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

   public CardOrder getOrder()
   {
      return order;
   }

   public void setOrder(CardOrder order)
   {
      this.order = order;
   }
}
