package dao.interfaces;

import models.Subtype;

import java.util.List;

public interface ISubtypeDao
{
   public List getAllSubtypes();

   public Subtype getSubtype(int subtypeID);

   public void updateSubtype(Subtype subtype);

   public void deleteSubtype(Subtype subtype);
}
