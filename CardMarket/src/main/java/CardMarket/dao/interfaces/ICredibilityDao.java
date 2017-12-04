package CardMarket.dao.interfaces;

import CardMarket.models.Credibility;

import java.util.List;

public interface ICredibilityDao
{
   public boolean createCredibility(Credibility credibility);

   public List getAllCredibilities();

   public Credibility getCredibility(int credibilityID);

   public void updateCredibility(Credibility credibility);

   public void deleteCredibility(Credibility credibility);
}
