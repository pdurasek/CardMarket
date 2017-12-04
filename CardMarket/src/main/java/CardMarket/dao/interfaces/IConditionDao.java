package CardMarket.dao.interfaces;

import CardMarket.models.Condition;

import java.util.List;

public interface IConditionDao
{
   public boolean createCondition(Condition condition);

   public List getAllConditions();

   public Condition getConditions(int conditionID);

   public void updateCondition(Condition condition);

   public void deleteCondition(Condition condition);
}
