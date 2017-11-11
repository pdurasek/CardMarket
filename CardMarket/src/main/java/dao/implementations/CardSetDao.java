package dao.implementations;

import dao.SessionCreator;
import dao.interfaces.ICardSetDao;
import models.Cardset;
import org.hibernate.Session;

import java.util.List;

public class CardSetDao implements ICardSetDao
{
   private Session session = null;

   public CardSetDao()
   {
      session = SessionCreator.getSession();
   }

   @Override
   public List getAllSets()
   {
      return session.createQuery("FROM Cardset ").list();
   }

   @Override
   public Cardset getCardSet(int cardSetID)
   {
      return session.get(Cardset.class, cardSetID);
   }

   @Override
   public void updateCardSet(Cardset cardset)
   {
      session.update(cardset);
   }

   @Override
   public void deleteCardSet(Cardset cardset)
   {
      session.delete(cardset);
   }
}
