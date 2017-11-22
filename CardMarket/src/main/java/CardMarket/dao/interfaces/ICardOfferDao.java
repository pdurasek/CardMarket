package CardMarket.dao.interfaces;

import CardMarket.models.CardOffer;

import java.util.List;

public interface ICardOfferDao
{
   public List getAllCardOffers(String cardName);

   public List getAllCardOffers(int startIndex, int pageSize, String cardName);

   public CardOffer getCardOffer(int cardOfferID);

   public boolean updateCardOffer(CardOffer cardOffer);

   public void deleteCardOffer(CardOffer cardOffer);
}
