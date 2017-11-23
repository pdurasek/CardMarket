package CardMarket.dao.interfaces;

import CardMarket.models.User;

import java.util.List;

public interface IUserDao
{
   public boolean createUser(User user);

   public List getAllUsers();

   public List getAllUsers(int startIndex, int pageSize);

   public User getUser(int userID);

   public User getAuthenticatedUser(String username, String password);

   public void updateUser(User user);

   public void deleteUser(User user);
}
