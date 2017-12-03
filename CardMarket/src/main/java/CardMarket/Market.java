package CardMarket;

import CardMarket.controllers.*;
import CardMarket.dao.implementations.CardDao;
import CardMarket.dao.implementations.CardOfferDao;
import CardMarket.dao.implementations.CardSetDao;
import CardMarket.dao.implementations.SoldCardDao;
import CardMarket.dao.interfaces.ICardDao;
import CardMarket.dao.interfaces.ICardOfferDao;
import CardMarket.dao.interfaces.ICardSetDao;
import CardMarket.dao.interfaces.ISoldCardDao;
import CardMarket.models.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

//077097100101032098121032080097116114105107032068117114097115101107032038032076117099097110111032090103097110101099
public class Market extends Application
{
   private AnchorPane root;
   private Stage mainStage;

   @Override
   public void start(Stage primaryStage)
   {
      mainStage = primaryStage;
      mainStage.getIcons().add(new Image("images/icon.png"));
      showRegister();
   }

   public static void main(String[] args)
   {
      launch(args);
   }

   public void showBrowse()
   {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Market.class.getResource("/view_templates/Browse.fxml"));
      try
      {
         root = loader.load();
      }
      catch (IOException e)
      {
         e.printStackTrace();
         System.err.println("Error while loading the scene template");
         System.exit(0);
      }
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("/css/components.css").toExternalForm());
      mainStage.setScene(scene);
      mainStage.setTitle("Browse");
      mainStage.setOnCloseRequest(event ->
      {
         Platform.exit();
         System.exit(0);
      });
      mainStage.centerOnScreen();
      mainStage.show();

      BrowseController browseController = loader.getController();
      browseController.setMarket(this);
      browseController.setEvents();
   }

   public void showUniqueCard(Card card)
   {
      try
      {
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(Market.class.getResource("/view_templates/UniqueCard.fxml"));
         AnchorPane anchorPane = loader.load();
         Scene scene = new Scene(anchorPane);
         scene.getStylesheets().add(getClass().getResource("/css/components.css").toExternalForm());

         Stage modalStage = new Stage();
         modalStage.setScene(scene);
         modalStage.getIcons().add(new Image("images/icon.png"));
         modalStage.initModality(Modality.APPLICATION_MODAL);
         modalStage.setTitle(card.getName());
         modalStage.show();


         // Create controller + methods
         UniqueCardController uniqueCardController = loader.getController();
         uniqueCardController.setUniqueCard(card);
         uniqueCardController.updateCardInfo();
         uniqueCardController.updateOfferList();
      }
      catch (IOException e)
      {
         e.printStackTrace();
         System.err.println("Error while loading the scene template");
      }
   }

   public void showRegister()
   {
      try
      {
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(Market.class.getResource("/view_templates/Register.fxml"));
         root = loader.load();

         mainStage.setScene(new Scene(root));
         mainStage.setTitle("CardMarket");
         mainStage.setOnCloseRequest(event ->
         {
            Platform.exit();
            System.exit(0);
         });
         mainStage.show();

         RegisterController registerController = loader.getController();
         registerController.setMarket(this);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   public void showCart()
   {
      try
      {
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(Market.class.getResource("/view_templates/Cart.fxml"));
         AnchorPane anchorPane = loader.load();
         Scene scene = new Scene(anchorPane);
         scene.getStylesheets().add(getClass().getResource("/css/components.css").toExternalForm());

         Stage modalStage = new Stage();
         modalStage.setScene(scene);
         modalStage.getIcons().add(new Image("images/icon.png"));
         modalStage.initModality(Modality.APPLICATION_MODAL);
         modalStage.setTitle("Cart");
         modalStage.show();

         // Create controller + methods
         CartController cartController = loader.getController();
         cartController.setMarket(this);
         cartController.setScene(modalStage);
         cartController.updateCartList();
      }
      catch (IOException e)
      {
         e.printStackTrace();
         System.err.println("Error while loading the scene template");
      }
   }

   public void showUserProfile()
   {
      try
      {
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(Market.class.getResource("/view_templates/User.fxml"));
         AnchorPane anchorPane = loader.load();
         Scene scene = new Scene(anchorPane);
         scene.getStylesheets().add(getClass().getResource("/css/components.css").toExternalForm());

         Stage modalStage = new Stage();
         modalStage.setScene(scene);
         modalStage.getIcons().add(new Image("images/icon.png"));
         modalStage.setTitle("User Profile");
         modalStage.initModality(Modality.APPLICATION_MODAL);
         modalStage.show();

         // Create controller + methods
         UserController userController = loader.getController();
         userController.setMarket(this);
         userController.setStage(modalStage);
         userController.createCardList();
      }
      catch (IOException e)
      {
         e.printStackTrace();
         System.err.println("Error while loading the scene template");
      }
   }

   public void showAddCardOffer()
   {
      try
      {
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(Market.class.getResource("/view_templates/AddCardOffer.fxml"));
         AnchorPane anchorPane = loader.load();
         Scene scene = new Scene(anchorPane);
         scene.getStylesheets().add(getClass().getResource("/css/components.css").toExternalForm());

         Stage modalStage = new Stage();
         modalStage.setScene(scene);
         modalStage.getIcons().add(new Image("images/icon.png"));
         modalStage.initModality(Modality.APPLICATION_MODAL);
         modalStage.setTitle("Add New Card");
         modalStage.show();
         modalStage.setOnCloseRequest(event ->
         {
            showUserProfile();
         });

         // Create controller + methods
         AddCardOfferController addCardOfferController= loader.getController();
         addCardOfferController.setMarket(this);
      }
      catch (IOException e)
      {
         e.printStackTrace();
         System.err.println("Error while loading the scene template");
      }
   }

   public void showOrder()
   {
      try
      {
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(Market.class.getResource("/view_templates/Order.fxml"));
         AnchorPane anchorPane = loader.load();
         Scene scene = new Scene(anchorPane);
         scene.getStylesheets().add(getClass().getResource("/css/components.css").toExternalForm());

         Stage modalStage = new Stage();
         modalStage.setScene(scene);
         modalStage.initModality(Modality.APPLICATION_MODAL);
         modalStage.show();
         modalStage.setOnCloseRequest(event ->
         {
            showCart();
         });

         // Create controller + methods
         OrderController orderController = loader.getController();
         orderController.setMarket(this);
         orderController.setModalStage(modalStage);
      }
      catch (IOException e)
      {
         e.printStackTrace();
         System.err.println("Error while loading the scene template");
      }

   }
}
