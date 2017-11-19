package CardMarket.dao.interfaces;

import CardMarket.models.User;

import java.util.List;

public interface IUserDao
{
   public List getAllUsers();

   public List getAllUsers(int startIndex, int pageSize);

   public User getUser(int userID);

   public void updateUser(User user);

   public void deleteUser(User user);
}
