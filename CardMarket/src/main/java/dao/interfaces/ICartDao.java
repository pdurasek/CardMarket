package dao.interfaces;

import models.Cart;

import java.util.List;

public interface ICartDao
{
   public List getAllCarts();

   public Cart getCart(int cartID);

   public void updateCart(Cart cart);

   public void deleteCart(Cart cart);
}
