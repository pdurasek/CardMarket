package CardMarket.dao.interfaces;

import CardMarket.models.Cardset;

import java.util.List;

public interface ICardSetDao
{
   public List getAllSets();

   public Cardset getCardSet(int cardSetID);

   public void updateCardSet(Cardset cardset);

   public void deleteCardSet(Cardset cardset);
}
