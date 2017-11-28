package CardMarket.dao.interfaces;

import CardMarket.models.ReservedCard;

import java.util.List;

public interface IReservedCardDao
{
   public boolean createReservedCard(ReservedCard reservedCard);

   public List getAllReservedCards();

   public ReservedCard getReservedCard(int reservedCardID);

   public ReservedCard getReservedCard(int cardOfferID, int userID);

   public boolean updateReservedCard(ReservedCard reservedCard);

   public void deleteReservedCard(ReservedCard reservedCard);
}
