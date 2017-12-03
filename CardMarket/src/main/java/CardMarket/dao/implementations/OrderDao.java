package CardMarket.dao.implementations;

import CardMarket.dao.SessionCreator;
import CardMarket.dao.interfaces.IOrderDao;
import CardMarket.models.CardOrder;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
   public CardOrder getOrder(int orderID)
   {
      return session.get(CardOrder.class, orderID);
   }

   @Override
   public void updateOrder(CardOrder order)
   {
      session.update(order);
   }

   @Override
   public void deleteOrder(CardOrder order)
   {
      session.delete(order);
   }

   @Override
   public boolean createOrder(CardOrder order)
   {
      Transaction transaction = null;
      try
      {
         transaction = session.beginTransaction();
         session.save(order);
         transaction.commit();
      }
      catch (Exception e)
      {
         if(transaction != null)
         {
            transaction.rollback();
         }

         return false;
      }

      return true;
   }
}
