package CardMarket.dao.interfaces;

import CardMarket.models.Type;

import java.util.List;

public interface ITypeDao
{
   public boolean createType(Type type);

   public List getAllTypes();

   public Type getType(int typeID);

   public void updateType(Type type);

   public void deleteType(Type type);
}
