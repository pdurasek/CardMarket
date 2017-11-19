package CardMarket.dao.interfaces;

import CardMarket.models.Language;

import java.util.List;

public interface ILanguageDao
{
   public List getAllLanguages();

   public Language getLanguages(int languageID);

   public void updateLanguage(Language language);

   public void deleteLanguage(Language language);
}
