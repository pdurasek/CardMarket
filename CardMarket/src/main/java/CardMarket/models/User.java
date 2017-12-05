package CardMarket.models;

import java.util.Set;

public class User
{
   private int userID;
   private String username;
   private String password;
   private String email;
   private int cardsBought;
   private int cardsSold;
   private ShippingAddressTemplate shippingAddressTemplate;
   private Credibility credibility;
   private Set<ReservedCard> reservedCards;

   public User()
   {
   }

   public User(String username, String password)
   {
      this.username = username;
      this.password = password;
   }

   public User(String username, String password, String email, Credibility credibility)
   {
      this.username = username;
      this.password = password;
      this.email = email;
      this.credibility = credibility;
   }

   public int getUserID()
   {
      return userID;
   }

   public void setUserID(int userID)
   {
      this.userID = userID;
   }

   public String getUsername()
   {
      return username;
   }

   public void setUsername(String username)
   {
      this.username = username;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   public int getCardsBought()
   {
      return cardsBought;
   }

   public void setCardsBought(int cardsBought)
   {
      this.cardsBought = cardsBought;
   }

   public int getCardsSold()
   {
      return cardsSold;
   }

   public void setCardsSold(int cardsSold)
   {
      this.cardsSold = cardsSold;
   }

   public ShippingAddressTemplate getShippingAddressTemplate()
   {
      return shippingAddressTemplate;
   }

   public void setShippingAddressTemplate(ShippingAddressTemplate shippingAddressTemplate)
   {
      this.shippingAddressTemplate = shippingAddressTemplate;
   }

   public Credibility getCredibility()
   {
      return credibility;
   }

   public void setCredibility(Credibility credibility)
   {
      this.credibility = credibility;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public Set getReservedCards()
   {
      return reservedCards;
   }

   public void setReservedCards(Set reservedCards)
   {
      this.reservedCards = reservedCards;
   }
}
