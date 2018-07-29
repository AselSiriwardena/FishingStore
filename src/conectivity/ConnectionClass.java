package conectivity;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionClass {

    public Connection  getConnection(){
        Connection connection=null;
        String dbName = "jeffs";
        String userName ="root";
        String password ="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             connection=DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}

  /*ConnectionClass ConClass = new ConnectionClass();
        Connection connection = ConClass.getConnection();
        String getRegNoSQL="SELECT Regno FROM `customer`;";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getRegNoSQL);
            while (rs.next()){
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        /*amountCol.setCellValueFactory(new PropertyValueFactory("firstName"));
        amountCol.cellFactoryProperty().setValue("wddae");
        amountCol.getColumns().get(1)*/
