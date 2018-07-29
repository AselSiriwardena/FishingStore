package JavaFx;

import conectivity.ConnectionClass;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller{
    public TextField SUPassword;
    public TextField SUPasswordCon;
    public Label chkPassAlert;
    public TextField SUEmail;
    public TextField SUName;
    public TextField SUAddress;
    public TextField enteredEmail;
    public PasswordField enteredPassword;


    private static String userRegNo;

    public static String getUserRegNo() {

        return userRegNo;
    }

    /*       ^                 # start-of-string
            (?=.*[0-9])       # a digit must occur at least once
            (?=.*[a-z])       # a lower case letter must occur at least once
            (?=.*[A-Z])       # an upper case letter must occur at least once
            (?=\S+$)          # no whitespace allowed in the entire string
            .{8,}             # anything, at least eight places though
            $                 # end-of-string
      */

    public void SignUp()  {

        ConnectionClass ConClass = new ConnectionClass();
        Connection connection = ConClass.getConnection();
        String getRegNoSQL = "SELECT Regno FROM `customer`;";
        int regNo = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getRegNoSQL);
            while (rs.next()) {
                regNo = (rs.getInt("Regno")) + 1;
            }
            if (SUPassword.getText().equals(SUPasswordCon.getText())){

                if (SUPassword.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")){

                    String newUserSQL = "INSERT INTO `Customer` (`Name`, `Email`, `Address`, `Password`, `RegNo`) VALUES ('" + SUName.getText()
                            + "', '" + SUEmail.getText() + "', '" + SUAddress.getText() + "', '" + SUPassword.getText() + "','" + regNo + "')";
                    statement.executeUpdate(newUserSQL);

                    System.out.println("SignUpSuccess");


                }else {
                    chkPassAlert.setText("Password must be contain \n" +
                            "with at least 8 characters \n" +
                            "including uppercase letter \n" +
                            "and a number !\n");

                }
            }else {
                MessageBox.loginErr();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public void SignIn() {
        String passwordE = enteredPassword.getText();
        String emailE = enteredEmail.getText();


        ConnectionClass ConClass = new ConnectionClass();
        Connection connection = ConClass.getConnection();
        String getUserPassword = "SELECT PASSWORD, RegNo FROM `customer` WHERE Email = '"+emailE+"' ;";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getUserPassword);


            rs.next();
            if (rs.getString("Password").equals(passwordE)) {
                userRegNo = rs.getString("RegNo");
                System.out.println("done");
                DashBoard dashB = new DashBoard();
                dashB.dashBoard();
                Main.getpStage().close();

                System.out.println(userRegNo);
                System.out.println("SignInSuccess");

            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Entry!");
            alert.setHeaderText("Password/Username you entered is Invalid!");
            alert.showAndWait();
        }



    }


}
