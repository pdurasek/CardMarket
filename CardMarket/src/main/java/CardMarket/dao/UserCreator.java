package CardMarket.dao;

import CardMarket.dao.implementations.UserDao;
import CardMarket.dao.interfaces.IUserDao;
import CardMarket.models.User;

public class UserCreator
{
   private static IUserDao userDao = new UserDao();
   private static User loggedUser = null;

   public static User getLoggedUser(String username, String password)
   {
      if (loggedUser == null)
      {
         loggedUser = userDao.getAuthenticatedUser(username, password);

         if (loggedUser == null)
         {
            System.err.println("Cannot authenticate user");
         }
      }

      return loggedUser;
   }

   public static User getLoggedUser()
   {
      if (loggedUser == null)
      {
         System.err.println("Cannot authenticate user");
      }

      return loggedUser;
   }
}
