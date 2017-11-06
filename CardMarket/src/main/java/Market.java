import com.jfoenix.controls.JFXMasonryPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.Rarity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Market extends Application
{
   private static SessionFactory factory;

   @Override
   public void start(Stage primaryStage) throws Exception
   {


      JFXMasonryPane jfxMasonryPane = new JFXMasonryPane();
      jfxMasonryPane.setStyle("-fx-background-color: #181818");

      Random r = new Random();
      for (int i = 0; i < 20; i++)
      {
            /*Label label = new Label();
            label.setStyle("-fx-background-color:rgb(" + r.nextInt(255) + "," + r.nextInt(255) + "," + r.nextInt(255) + ")");
            jfxMasonryPane.getChildren().add(label);*/

         VBox card = new VBox();
         int kekFactor = r.nextInt(2);
         String imgName = "";
         if(kekFactor == 0)
         {
            imgName = "ash.png";
         }
         else
         {
            imgName = "ghost.png";
         }
         ImageView slikica = new ImageView(imgName);
            /*slikica.setFitHeight(243);
            slikica.setFitWidth(171);*/
         Label label = new Label(imgName);
         label.setTextFill(Paint.valueOf("#bfbfbf"));
         //label.setPrefSize(100, 10);
         card.getChildren().addAll(slikica, label);
         card.setPrefSize(171,243 );

         jfxMasonryPane.getChildren().add(card);
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
      TestHibernateConnection();
      launch(args);
   }

   private static void TestHibernateConnection()
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
         List rarities = session.createQuery("FROM Rarity").list();
         ArrayList<Rarity> rarities2 = new ArrayList<>(session.createQuery("FROM Rarity").list());

         for (int i = 0; i < rarities2.size(); ++i)
         {
            Rarity rarity = (Rarity) rarities2.get(i);
            System.out.println(rarity.getRarityID());
            System.out.println(rarity.getName());
         }

         for (int i = 0; i < rarities.size(); ++i)
         {
            Rarity rarity = (Rarity) rarities.get(i);
            System.out.println(rarity.getRarityID());
            System.out.println(rarity.getName());
         }

         for (Iterator iterator = rarities.iterator(); iterator.hasNext();)
         {
            Rarity rarity = (Rarity) iterator.next();
            System.out.println(rarity.getRarityID());
            System.out.println(rarity.getName());
         }
      }
      catch(HibernateException e)
      {
         if (transaction != null)
         {
            transaction.rollback();
         }
      }
   }
}
