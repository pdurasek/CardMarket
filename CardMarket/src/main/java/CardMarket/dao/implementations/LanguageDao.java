package CardMarket.dao.implementations;

import CardMarket.dao.SessionCreator;
import CardMarket.dao.interfaces.ILanguageDao;
import CardMarket.models.Language;
import org.hibernate.Session;

import java.util.List;

public class LanguageDao implements ILanguageDao
{
   private Session session = null;

   public LanguageDao()
   {
      session = SessionCreator.getSession();
   }

   @Override
   public List getAllLanguages()
   {
      return session.createQuery("FROM Language ").list();
   }

   @Override
   public Language getLanguages(int languageID)
   {
      return session.get(Language.class, languageID);
   }

   @Override
   public void updateLanguage(Language language)
   {
      session.update(language);
   }

   @Override
   public void deleteLanguage(Language language)
   {
      session.delete(language);
   }
}
