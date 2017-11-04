package sample;

import com.jfoenix.controls.JFXMasonryPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.Random;

public class Market extends Application
{
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

        primaryStage.setTitle("Ayyy Masonry Pane bby");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
