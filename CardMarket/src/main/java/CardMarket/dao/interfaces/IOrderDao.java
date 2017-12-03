package CardMarket.dao.interfaces;

import CardMarket.models.CardOrder;

import java.util.List;

public interface IOrderDao
{
   public List getAllOrders();

   public CardOrder getOrder(int orderID);

   public void updateOrder(CardOrder order);

   public void deleteOrder(CardOrder order);

   public boolean createOrder(CardOrder order);
}
