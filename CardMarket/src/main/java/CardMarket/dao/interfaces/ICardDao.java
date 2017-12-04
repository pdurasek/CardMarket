package CardMarket.dao.interfaces;

import CardMarket.models.Card;

import java.util.List;

public interface ICardDao
{
   public boolean createCard(Card card);

   public List getAllCards();

   public List getAllCards(int startIndex, int pageSize, int setValue);

   public List getAllCardsLike(String pattern, int setValue);

   public List getAllCardsLike(String pattern);

   public List getAllCardsLike(String pattern, int startIndex, int pageSize, int setValue);

   public List getAllCardsFiltered(String pattern, int startIndex, int pageSize, String filterColumn, int filterValue, int setValue);

   public int getAllCardsCount();

   public int getAllCardsCount(String pattern, int setValue);

   public int getAllCardsFilteredCount(String pattern, String filterColumn, int filterValue, int setValue);

   public Card getCard(int cardID);

   public void updateCard(Card card);

   public void deleteCard(Card card);
}
