import com.jfoenix.controls.JFXMasonryPane;
import dao.implementations.*;
import dao.interfaces.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import models.*;

import java.util.List;

//077097100101032098121032080097116114105107032068117114097115101107032038032076117099097110111032090103097110101099
public class Market extends Application
{
   @Override
   public void start(Stage primaryStage) throws Exception
   {
      //testMasonry(primaryStage);
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Market.class.getResource("view_templates/Browse.fxml"));
      AnchorPane root = loader.load();
      Scene scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.setOnCloseRequest(event ->
      {
         Platform.exit();
         System.exit(0);
      });
      primaryStage.show();
   }

   public static void main(String[] args)
   {
      testCardDao();
      testCountryDao();
      testPaymentMethodDao();
      testCredibilityDao();
      testSoldCardDao();
      testCardOfferDao();
      launch(args);

   }

   private void testMasonry(Stage primaryStage)
   {
      JFXMasonryPane jfxMasonryPane = new JFXMasonryPane();
      jfxMasonryPane.setStyle("-fx-background-color: #181818");

      List karteee = testCardDao();

      for (int i = 0; i < karteee.size(); ++i)
      {
         Card card = (Card) karteee.get(i);

         VBox cardBox = new VBox();
         String imgName = card.getImageUrl();

         ImageView slikica = new ImageView("images/" + imgName);

         Label label = new Label(card.getName());
         Label label2 = new Label(card.getDescription());
         label.setTextFill(Paint.valueOf("#bfbfbf"));
         label2.setTextFill(Paint.valueOf("#bfbfbf"));
         cardBox.getChildren().addAll(slikica, label, label2);
         cardBox.setPrefSize(171, 243);

         jfxMasonryPane.getChildren().add(cardBox);

      }

      jfxMasonryPane.setCellHeight(150);
      jfxMasonryPane.setHSpacing(20);
      jfxMasonryPane.setVSpacing(1);
      ScrollPane scrollPane = new ScrollPane(jfxMasonryPane);
      scrollPane.setFitToHeight(true);
      scrollPane.setFitToWidth(true);
      scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
      StackPane root = new StackPane(scrollPane);

      Scene scene = new Scene(root, 1150, 800);

      primaryStage.setTitle("Ayyyy Masonry Pane bby");
      primaryStage.setScene(scene);
      primaryStage.show();
      primaryStage.requestFocus(); // TODO fix scroll not appearing on start even though it is focused
      primaryStage.getIcons().add(new Image("images/icon.png"));
   }

   private static void testCredibilityDao()
   {
      ICredibilityDao crediblityDao = new CredibilityDao();
      List<Credibility> credibilities = crediblityDao.getAllCredibilities();

      System.out.println("Credibilities: ");

      for (int i = 0; i < credibilities.size(); ++i)
      {
         System.out.println("\t" + credibilities.get(i).getName());
      }
   }

   private static void testPaymentMethodDao()
   {
      IPaymentMethodDao paymentMethodDao = new PaymentMethodDao();
      List<PaymentMethod> payments = paymentMethodDao.getAllPaymentMethods();

      System.out.println("Payment Methods: ");

      for (int i = 0; i < payments.size(); ++i)
      {
         System.out.println("\t" + payments.get(i).getName());
      }
   }

   private static void testCountryDao()
   {
      ICountryDao countryDao = new CountryDao();
      List<Country> countries = countryDao.getAllCountries();

      System.out.println("Countries: ");

      for (int i = 0; i < countries.size(); ++i)
      {
         System.out.println("\t" + countries.get(i).getName());
      }
   }

   private static List testCardDao()
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

      for (Cardset cardset : setooo)
      {
         System.out.println(cardset.getName());
      }

      return karteee;
   }

   private static List testSoldCardDao()
   {
      ISoldCardDao karta = new SoldCardDao();
      List<SoldCard> karteee = karta.getAllSoldCards();

      for (int i = 0; i < karteee.size(); i++)
      {
         System.out.println(karteee.get(i).getCard().getName() + " was bought by " + karteee.get(i).getBuyer().getUsername()
                 + " from " + karteee.get(i).getSeller().getUsername());
      }

      return karteee;
   }

   private static List testCardOfferDao()
   {
      ICardOfferDao karta = new CardOfferDao();
      List<CardOffer> karteee = karta.getAllCardOffers();

      for (int i = 0; i < karteee.size(); i++)
      {
         System.out.println(karteee.get(i).getCard().getName() + " is being sold by " + karteee.get(i).getUser().getUsername());
      }

      return karteee;
   }
}
