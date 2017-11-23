package CardMarket.dao.implementations;

import CardMarket.dao.SessionCreator;
import CardMarket.dao.interfaces.IUserDao;
import CardMarket.models.User;
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
   public boolean createUser(User user)
   {
      Object newUser = session.save(user);

      return newUser != null;
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
   public User getAuthenticatedUser(String username, String password)
   {
      Query query = session.createQuery("FROM User WHERE username = :username AND password = :password");
      query.setParameter("username", username);
      query.setParameter("password", password);

      return (User) query.uniqueResult();
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
