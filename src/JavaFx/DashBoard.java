package JavaFx;

import conectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DashBoard {

    static Stage Dash = null;


    public void dashBoard() {

        try {
            Dash = new Stage();
            Parent DashRoot = FXMLLoader.load(DashBoard.class.getResource("../Interfaces/Dashboard.fxml"));
            Dash.setTitle("Dashboard");
            Dash.setScene(new Scene(DashRoot, 700, 400));
            Dash.show();

        } catch (Exception e) {
            System.out.println("stageERR");
        }

    }


    public void openShop(ActionEvent actionEvent) {
        Buy buy = new Buy();
        buy.Cart();
        Dash.hide();

    }

    public void openProfile(ActionEvent actionEvent) {
        Profile.profile();
        Dash.hide();

    }

    public void logOutFromDash(ActionEvent actionEvent) {
        Main.getpStage().show();
        Dash.close();
        Controller.userRegNoReset();
        Buy.cartReset();

    }

}


