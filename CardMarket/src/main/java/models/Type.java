package models;

public class Type
{
   private int typeID;
   private String name;

   public Type()
   {
   }

   public Type(int typeID, String name)
   {
      this.typeID = typeID;
      this.name = name;
   }

   public int getTypeID()
   {
      return typeID;
   }

   public void setTypeID(int typeID)
   {
      this.typeID = typeID;
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
