package JavaFx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    static Stage pStage;

    public static Stage getpStage(){
        return pStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        pStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../Interfaces/Login.fxml"));
        primaryStage.setTitle("Login");
        Scene loginS = new Scene(root, 600, 400);
        //loginS.getStylesheets().add(Main.class.getResource("resources/c.css").toExternalForm());
        primaryStage.setScene(loginS);
        primaryStage.show();
        //primaryStage.setFullScreen(true);
        primaryStage.getIcons().add(new Image(this.getClass().getResource("profile.png").toString()));

    }

    public static void main(String[] args) {
        launch(args);

    }


}
