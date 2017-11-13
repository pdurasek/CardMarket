package dao.interfaces;

import models.SoldCard;

import java.util.List;

public interface ISoldCardDao
{
   public List getAllSoldCards();

   public List getAllSoldCard(int startIndex, int pageSize);

   public SoldCard getSoldCard(int soldCardID);

   public void updateSoldCard(SoldCard soldCard);

   public void deleteSoldCard(SoldCard soldCard);
}
