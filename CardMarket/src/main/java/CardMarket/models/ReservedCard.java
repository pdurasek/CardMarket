package CardMarket.models;

public class ReservedCard
{
   private int reservedCardID;
   private CardOffer cardOffer;
   private User user;
   private int quantity;

   public ReservedCard()
   {
   }

   public ReservedCard(CardOffer cardOffer, User user, int quantity)
   {
      this.cardOffer = cardOffer;
      this.user = user;
      this.quantity = quantity;
   }

   public CardOffer getCardOffer()
   {
      return cardOffer;
   }

   public void setCardOffer(CardOffer cardOffer)
   {
      this.cardOffer = cardOffer;
   }

   public int getQuantity()
   {
      return quantity;
   }

   public void setQuantity(int quantity)
   {
      this.quantity = quantity;
   }

   public int getReservedCardID()
   {
      return reservedCardID;
   }

   public void setReservedCardID(int reservedCardID)
   {
      this.reservedCardID = reservedCardID;
   }

   public User getUser()
   {
      return user;
   }

   public void setUser(User user)
   {
      this.user = user;
   }
}
