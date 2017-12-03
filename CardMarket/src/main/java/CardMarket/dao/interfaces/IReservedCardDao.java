package CardMarket.dao.interfaces;

import CardMarket.models.ReservedCard;
import CardMarket.models.User;

import java.util.List;

public interface IReservedCardDao
{
   public ReservedCard createReservedCard(ReservedCard reservedCard);

   public List getAllReservedCards();

   public List getAllReservedCards(User user);

   public ReservedCard getReservedCard(int reservedCardID);

   public ReservedCard getReservedCard(int cardOfferID, int userID);

   public boolean updateReservedCard(ReservedCard reservedCard);

   public boolean deleteReservedCard(ReservedCard reservedCard);

   public double getTotalPrice(User user);

   public int getNumberOfItems(User user);
}
