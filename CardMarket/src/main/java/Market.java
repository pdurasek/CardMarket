import controllers.BrowseController;
import dao.implementations.*;
import dao.interfaces.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.*;

import java.util.List;

//077097100101032098121032080097116114105107032068117114097115101107032038032076117099097110111032090103097110101099
public class Market extends Application
{
   @Override
   public void start(Stage primaryStage) throws Exception
   {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Market.class.getResource("view_templates/Browse.fxml"));
      AnchorPane root = loader.load();
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("css/components.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.setOnCloseRequest(event ->
      {
         Platform.exit();
         System.exit(0);
      });
      primaryStage.show();

      BrowseController browseController = loader.getController();
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
