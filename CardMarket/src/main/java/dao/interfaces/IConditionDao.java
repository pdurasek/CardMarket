package dao.interfaces;

import models.Condition;

import java.util.List;

public interface IConditionDao
{
   public List getAllConditions();

   public Condition getConditions(int conditionID);

   public void updateCondition(Condition condition);

   public void deleteCondition(Condition condition);
}
