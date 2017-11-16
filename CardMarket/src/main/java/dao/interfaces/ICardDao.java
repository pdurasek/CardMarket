package dao.interfaces;

import models.Card;

import java.util.List;

public interface ICardDao
{
   public List getAllCards();

   public List getAllCards(int startIndex, int pageSize);

   public List getAllCardsLike(String pattern);

   public int getAllCardsCount();

   public Card getCard(int cardID);

   public void updateCard(Card card);

   public void deleteCard(Card card);
}
