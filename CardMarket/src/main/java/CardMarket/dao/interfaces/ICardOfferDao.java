package CardMarket.dao.interfaces;

import CardMarket.models.CardOffer;

import java.util.List;

public interface ICardOfferDao
{
   public List getAllCardOffers(String cardName);

   public List getAllCardOffers(int startIndex, int pageSize, String cardName);

   public List getAllUserCardOffers(String username);

   public CardOffer getCardOffer(int cardOfferID);

   public boolean createCardOffer(CardOffer cardOffer);

   public boolean updateCardOffer(CardOffer cardOffer);

   public boolean deleteCardOffer(CardOffer cardOffer);

   public double getAverageCost(String cardName);
}
