package dao.interfaces;

import models.Card;

import java.util.List;

public interface ICardDao
{
   public List getAllCards();

   public List getAllCards(int startIndex, int pageSize);

   public List getAllCardsLike(String pattern);

   public List getAllCardsLike(String pattern, int startIndex, int pageSize);

   public List getAllCardsFiltered(String pattern, int startIndex, int pageSize, String filterColumn, int filterValue);

   public int getAllCardsCount();

   public int getAllCardsCount(String pattern);

   public int getAllCardsFilteredCount(String pattern, String filterColumn, int filterValue);

   public Card getCard(int cardID);

   public void updateCard(Card card);

   public void deleteCard(Card card);
}
