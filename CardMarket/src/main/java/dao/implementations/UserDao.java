package dao.implementations;

import dao.SessionCreator;
import dao.interfaces.IUserDao;
import models.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao implements IUserDao
{
   private Session session = null;

   public UserDao()
   {
      session = SessionCreator.getSession();
   }


   @Override
   public List getAllUsers()
   {
      return session.createQuery("FROM User ").list();
   }

   @Override
   public List getAllUsers(int startIndex, int pageSize)
   {
      Query query = session.createQuery("FROM User ");
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);

      return query.list();
   }

   @Override
   public User getUser(int userID)
   {
      return session.get(User.class, userID);
   }

   @Override
   public void updateUser(User user)
   {
      session.update(user);
   }

   @Override
   public void deleteUser(User user)
   {
      session.delete(user);
   }
}
