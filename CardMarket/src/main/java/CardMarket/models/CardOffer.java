package CardMarket.models;

public class CardOffer
{
   private int cardOfferID;
   private Card card;
   private User user;
   private int quantity;
   private double price;
   private boolean reserved;

   public CardOffer()
   {
   }

   public CardOffer(int cardOfferID, Card card, User user, int quantity, double price, boolean reserved)
   {
      this.cardOfferID = cardOfferID;
      this.card = card;
      this.user = user;
      this.quantity = quantity;
      this.price = price;
      this.reserved = reserved;
   }

   public int getCardOfferID()
   {
      return cardOfferID;
   }

   public void setCardOfferID(int cardOfferID)
   {
      this.cardOfferID = cardOfferID;
   }

   public Card getCard()
   {
      return card;
   }

   public void setCard(Card card)
   {
      this.card = card;
   }

   public User getUser()
   {
      return user;
   }

   public void setUser(User user)
   {
      this.user = user;
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

   public boolean isReserved()
   {
      return reserved;
   }

   public void setReserved(boolean reserved)
   {
      this.reserved = reserved;
   }

}
