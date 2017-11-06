import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main
{

   public static void main(String[] args)
   {
      try
      {
         Class.forName("com.mysql.jdbc.Driver");
         String dbms = "jdbc:mysql://eu-cdbr-azure-west-b.cloudapp.net:3306/card_market";
         String user = "b1846fe151e4be";
         String pass = "89e8ce47";
         //Connection connection = DriverManager.getConnection("Database=card_market;Data Source=eu-cdbr-azure-west-b.cloudapp.net;User Id=b1846fe151e4be;Password=89e8ce47");
         Connection connection = DriverManager.getConnection(dbms, user, pass);
         System.out.println("Connected!");
         Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM card_market.rarity");
         rs.next();
         System.out.println(rs.getObject(2));
         rs.close();
         stmt.close();
         connection.close();

      }
      catch (SQLException e)
      {
         System.err.println("Error connecting!");
         e.printStackTrace();
      }
      catch (ClassNotFoundException e)
      {
         e.printStackTrace();
      }
   }
}
