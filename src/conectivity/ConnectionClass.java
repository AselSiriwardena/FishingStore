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
