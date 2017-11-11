package dao.interfaces;

import models.Type;

import java.util.List;

public interface ITypeDao
{
   public List getAllTypes();

   public Type getType(int typeID);

   public void updateType(Type type);

   public void deleteType(Type type);
}
