package CardMarket.dao.interfaces;

import CardMarket.models.CardOffer;

import java.util.List;

public interface ICardOfferDao
{
   public List getAllCardOffers();

   public List getAllCardOffers(int startIndex, int pageSize);

   public CardOffer getCardOffer(int cardOfferID);

   public void updateCardOffer(CardOffer cardOffer);

   public void deleteCardOffer(CardOffer cardOffer);
}
