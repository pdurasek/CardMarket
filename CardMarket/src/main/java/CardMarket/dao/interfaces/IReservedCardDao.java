package CardMarket.dao.interfaces;

import CardMarket.models.ReservedCard;
import CardMarket.models.User;

import java.util.List;

public interface IReservedCardDao
{
   public boolean createReservedCard(ReservedCard reservedCard);

   public List getAllReservedCards();

   public List getAllReservedCards(User user);

   public ReservedCard getReservedCard(int reservedCardID);

   public ReservedCard getReservedCard(int cardOfferID, int userID);

   public boolean updateReservedCard(ReservedCard reservedCard);

   public boolean deleteReservedCard(ReservedCard reservedCard);
}
