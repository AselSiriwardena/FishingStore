package JavaFx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {

    static Stage pStage;

    public static Stage getpStage() {
        return pStage;
    }

    private void background() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../Interfaces/Background.fxml"));
        primaryStage.setTitle("Login");
        Scene loginS = new Scene(root, 1920, 1080);
        primaryStage.setScene(loginS);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        pStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../Interfaces/Login.fxml"));
        primaryStage.setTitle("Login");
        Scene loginS = new Scene(root, 600, 400);
        primaryStage.setScene(loginS);
        background();
        primaryStage.show();
        primaryStage.getIcons().add(new Image(this.getClass().getResource("profile.png").toString()));


    }

    public static void main(String[] args) throws IOException {
        launch(args);

    }
}
