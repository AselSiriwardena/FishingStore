package JavaFx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DashBoard {


    public Label cusName;
    public Label cusRegNo;
    static Stage Dash=null;

    /*public static Stage dash (){
        return Dash;
    }
*/
    public void dashBoard() {

         //cusName.setText("sdvsbd");

        try {
            Dash= new Stage();
            Parent DashRoot = FXMLLoader.load(DashBoard.class.getResource("../Interfaces/Dashboard.fxml"));
            Dash.setTitle("Dashboard");
            Dash.setScene(new Scene(DashRoot, 700, 400));
            Dash.show();

        } catch (Exception e) {
            System.out.println("stageERR");
        }

    }


    public void openShop(ActionEvent actionEvent) {
        Dash.close();
        Buy.Cart();

    }

    public void openProfile(ActionEvent actionEvent) {
        Profile.profile();
    }

    public void openLastTranaction(ActionEvent actionEvent) {

    }



    public void logOut(ActionEvent actionEvent) {

    }
}


