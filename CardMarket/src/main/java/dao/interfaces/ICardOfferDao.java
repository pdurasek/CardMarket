package dao.interfaces;

import models.CardOffer;

import java.util.List;

public interface ICardOfferDao
{
   public List getAllCardOffers();

   public List getAllCardOffers(int startIndex, int pageSize);

   public CardOffer getSoldCard(int cardOfferID);

   public void updateSoldCard(CardOffer cardOffer);

   public void deleteSoldCard(CardOffer cardOffer);
}
