/**
 * Created by Ivan on 13/11/2017.
 * Description this class will show the view - Admin.fxml
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Admin extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        //Load View
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view_templates/Admin.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Admin");
        primaryStage.setScene(new Scene(root, 1150 , 800));
        primaryStage.show();
    }

}
