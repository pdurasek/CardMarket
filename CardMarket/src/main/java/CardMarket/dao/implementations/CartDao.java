package CardMarket.dao.implementations;

import CardMarket.dao.SessionCreator;
import CardMarket.dao.interfaces.ICartDao;
import CardMarket.models.Cart;
import org.hibernate.Session;

import java.util.List;

public class CartDao implements ICartDao
{
   private Session session = null;

   public CartDao()
   {
      session = SessionCreator.getSession();
   }

   @Override
   public List getAllCarts()
   {
      return session.createQuery("FROM Cart ").list();
   }

   @Override
   public Cart getCart(int cartID)
   {
      return session.get(Cart.class, cartID);
   }

   @Override
   public void updateCart(Cart cart)
   {
      session.update(cart);
   }

   @Override
   public void deleteCart(Cart cart)
   {
      session.delete(cart);
   }
}
