package dao.interfaces;

import models.Language;

import java.util.List;

public interface ILanguageDao
{
   public List getAllLanguages();

   public Language getLanguages(int languageID);

   public void updateLanguage(Language language);

   public void deleteLanguage(Language language);
}
