package dao.interfaces;

import models.Rarity;

import java.util.List;

public interface IRariryDao
{
   public List getAllRarities();

   public Rarity getRarity(int rarityID);

   public void updateRarity(Rarity rarity);

   public void deleteRarity(Rarity rarity);
}
