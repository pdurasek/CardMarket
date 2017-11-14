package dao.interfaces;

import models.Rarity;

import java.util.List;

public interface IRarityDao
{
   public List getAllRarities();

   public List getAllRarityNames();

   public Rarity getRarity(int rarityID);

   public void updateRarity(Rarity rarity);

   public void deleteRarity(Rarity rarity);
}
