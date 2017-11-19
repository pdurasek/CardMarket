package CardMarket.dao.interfaces;

import CardMarket.models.Order;

import java.util.List;

public interface IOrderDao
{
   public List getAllOrders();

   public Order getOrder(int orderID);

   public void updateOrder(Order order);

   public void deleteOrder(Order order);
}
