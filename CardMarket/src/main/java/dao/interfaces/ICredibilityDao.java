package dao.interfaces;

import models.Credibility;

import java.util.List;

public interface ICredibilityDao
{
   public List getAllCredibilities();

   public Credibility getCredibility(int credibilityID);

   public void updateCredibility(Credibility credibility);

   public void deleteCredibility(Credibility credibility);
}
