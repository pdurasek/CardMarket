package models;

public class Subtype
{
   private int subTypeID;
   private String name;

   public Subtype()
   {
   }

   public Subtype(int subTypeID, String name)
   {
      this.subTypeID = subTypeID;
      this.name = name;
   }

   public int getSubTypeID()
   {
      return subTypeID;
   }

   public void setSubTypeID(int subTypeID)
   {
      this.subTypeID = subTypeID;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }
}
