package CardMarket.dao.interfaces;

import CardMarket.models.Subtype;

import java.util.List;

public interface ISubtypeDao
{
   public boolean createSubType(Subtype subtype);

   public List getAllSubtypes();

   public Subtype getSubtype(int subtypeID);

   public void updateSubtype(Subtype subtype);

   public void deleteSubtype(Subtype subtype);
}
