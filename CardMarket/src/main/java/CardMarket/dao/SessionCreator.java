package CardMarket.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionCreator
{
    private static SessionFactory factory;
    private static Session session = null;

    public static Session getSession()
    {
        if (factory == null)
        {
            try
            {
                factory = new Configuration().configure().buildSessionFactory();
            }
            catch (Throwable ex)
            {
                System.err.println("Failed to create sessionFactory object");
                ex.printStackTrace(); // TODO handle this
            }
        }

        try
        {
            if (session != null && session.isOpen())
            {
                session.getSessionFactory().getCurrentSession();
            }
            else
            {
                session = factory.openSession();
            }
        }
        catch (HibernateException hex)
        {
            hex.printStackTrace(); // TODO handle this
        }

        return session;
    }
}
