package CardMarket.models;

public class Cart
{
   private int cartID;
   private User user;

   public int getCartID()
   {
      return cartID;
   }

   public void setCartID(int cartID)
   {
      this.cartID = cartID;
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
