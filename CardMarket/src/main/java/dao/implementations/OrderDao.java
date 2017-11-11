package dao.implementations;

import dao.SessionCreator;
import dao.interfaces.IOrderDao;
import models.Order;
import org.hibernate.Session;

import java.util.List;

public class OrderDao implements IOrderDao
{
   private Session session = null;

   public OrderDao()
   {
      session = SessionCreator.getSession();
   }

   @Override
   public List getAllOrders()
   {
      return session.createQuery("FROM Order ").list();
   }

   @Override
   public Order getOrder(int orderID)
   {
      return session.get(Order.class, orderID);
   }

   @Override
   public void updateOrder(Order order)
   {
      session.update(order);
   }

   @Override
   public void deleteOrder(Order order)
   {
      session.delete(order);
   }
}
