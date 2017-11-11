import com.jfoenix.controls.JFXMasonryPane;
import com.sun.istack.internal.Nullable;
import dao.implementations.*;
import dao.interfaces.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import models.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class Market extends Application
{
   private static SessionFactory factory;

   @Override
   public void start(Stage primaryStage) throws Exception
   {
      JFXMasonryPane jfxMasonryPane = new JFXMasonryPane();
      jfxMasonryPane.setStyle("-fx-background-color: #181818");

      List set = TestHibernateConnection();

      for (int i = 0; i < set.size(); ++i)
      {
         Cardset cardset = (Cardset) set.get(i);
         Set cards = cardset.getCards();

         for (Iterator iterator = cards.iterator(); iterator.hasNext();)
         {
            Card card = (Card) iterator.next();

            /*Label label = new Label();
            label.setStyle("-fx-background-color:rgb(" + r.nextInt(255) + "," + r.nextInt(255) + "," + r.nextInt(255) + ")");
            jfxMasonryPane.getChildren().add(label);*/

            VBox cardBox = new VBox();
            String imgName = card.getImageUrl();

            ImageView slikica = new ImageView("images/" +imgName);
            /*slikica.setFitHeight(243);
            slikica.setFitWidth(171);*/
            Label label = new Label(card.getName());
            Label label2 = new Label(card.getDescription());
            label.setTextFill(Paint.valueOf("#bfbfbf"));
            label2.setTextFill(Paint.valueOf("#bfbfbf"));
            //label.setPrefSize(100, 10);
            cardBox.getChildren().addAll(slikica, label, label2);
            cardBox.setPrefSize(171,243 );

            jfxMasonryPane.getChildren().add(cardBox);
         }
      }

      jfxMasonryPane.setCellHeight(150);
      jfxMasonryPane.setHSpacing(20);
      jfxMasonryPane.setVSpacing(1);
      ScrollPane scrollPane = new ScrollPane(jfxMasonryPane);
      scrollPane.setFitToHeight(true);
      scrollPane.setFitToWidth(true);
      scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
      StackPane root = new StackPane(scrollPane);

      Scene scene = new Scene(root, 1150 , 800);

      primaryStage.setTitle("Ayyyy Masonry Pane bby");
      primaryStage.setScene(scene);
      primaryStage.show();
      primaryStage.requestFocus(); // TODO fix scroll not appearing on start even though it is focused
   }

   public static void main(String[] args)
   {
      //TestHibernateConnection();
      testCardDao();
      testCountryDao();
      testPaymentMethodDao();
      testCredibilityDao();
      launch(args);
   }

   private static void testCredibilityDao()
   {
      ICredibilityDao crediblityDao = new CrediblityDao();
      List<Credibility> credibilities = crediblityDao.getAllCredibilities();

      System.out.println("Credibilities: ");

      for (int i = 0; i < credibilities.size(); ++i)
      {
         System.out.println("\t" +credibilities.get(i).getName());
      }
   }

   private static void testPaymentMethodDao()
   {
      IPaymentMethodDao paymentMethodDao = new PaymentMethodDao();
      List<PaymentMethod> payments = paymentMethodDao.getAllPaymentMethods();

      System.out.println("Payment Methods: ");

      for (int i = 0; i < payments.size(); ++i)
      {
         System.out.println("\t" +payments.get(i).getName());
      }
   }

   private static void testCountryDao()
   {
      ICountryDao countryDao = new CountryDao();
      List<Country> countries = countryDao.getAllCountries();

      System.out.println("Countries: ");

      for (int i = 0; i < countries.size(); ++i)
      {
         System.out.println("\t" +countries.get(i).getName());
      }
   }

   private static void testCardDao()
   {
      ICardDao karta = new CardDao();
      List<Card> karteee = karta.getAllCards(0, 15);

      for (int i = 0; i < karteee.size(); i++)
      {
         System.out.println(karteee.get(i).getName() + " | " + karteee.get(i).getType().getName() + " | " + karteee.get(i).getSubtype().getName() + " | "
                 + karteee.get(i).getRarity().getName() + " | " + karteee.get(i).getLanguage().getName() + " | "
                 + karteee.get(i).getSet().getName() + " | " + karteee.get(i).getCondition().getName());
      }

      ICardSetDao set = new CardSetDao();
      List<Cardset> setooo = set.getAllSets();

      for (Cardset cardset: setooo)
      {
         System.out.println(cardset.getName());
      }
   }

   @Nullable
   private static List TestHibernateConnection()
   {
      try
      {
         factory = new Configuration().configure().buildSessionFactory();
      }
      catch (Throwable ex)
      {
         System.err.println("Failed to create sessionFactory object");
         throw new ExceptionInInitializerError(ex);
      }


      Transaction transaction = null;

      try (Session session = factory.openSession())
      {
         transaction = session.beginTransaction();
         ArrayList<Rarity> rarities2 = new ArrayList<>(session.createQuery("FROM Rarity").list());

         for (int i = 0; i < rarities2.size(); ++i)
         {
            Rarity rarity = rarities2.get(i);
            //System.out.print(rarity.getRarityID() +". ");
            //System.out.println(rarity.getName());
         }

         /*List rarities = session.createQuery("FROM Rarity").list();

         for (int i = 0; i < rarities.size(); ++i)
         {
            Rarity rarity = (Rarity) rarities.get(i);
            System.out.print(rarity.getRarityID() +". ");
            System.out.println(rarity.getName());
         }

         for (Iterator iterator = rarities.iterator(); iterator.hasNext();)
         {
            Rarity rarity = (Rarity) iterator.next();
            System.out.print(rarity.getRarityID() +". ");
            System.out.println(rarity.getName());
         }*/

         List sets = session.createQuery("FROM Cardset ").list();

         for (int i = 0; i < sets.size(); ++i)
         {
            Cardset set = (Cardset) sets.get(i);
            //System.out.println("Cardset name: " +set.getName() +"; ");
            Set cards = set.getCards();

            for (Iterator iterator = cards.iterator(); iterator.hasNext();)
            {
               Card card = (Card) iterator.next();
               //System.out.println("\t Card name: " +card.getName());
            }
         }

         return sets;
      }
      catch(HibernateException e)
      {
         if (transaction != null)
         {
            transaction.rollback();
         }
      }

      return null;
   }
}
