package dao.implementations;

import dao.SessionCreator;
import dao.interfaces.IRariryDao;
import models.Rarity;
import org.hibernate.Session;

import java.util.List;

public class RarityDao implements IRariryDao
{
   private Session session = null;

   public RarityDao()
   {
      session = SessionCreator.getSession();
   }

   @Override
   public List getAllRarities()
   {
      return session.createQuery("FROM Rarity ").list();
   }

   @Override
   public Rarity getRarity(int rarityID)
   {
      return session.get(Rarity.class, rarityID);
   }

   @Override
   public void updateRarity(Rarity rarity)
   {
      session.update(rarity);
   }

   @Override
   public void deleteRarity(Rarity rarity)
   {
      session.delete(rarity);
   }
}
