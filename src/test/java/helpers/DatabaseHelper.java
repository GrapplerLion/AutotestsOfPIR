package helpers;

import org.junit.jupiter.api.Disabled;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Disabled
public class DatabaseHelper {

    private Connection connection;

    public void connectToDatabase() throws SQLException {

        String url = "jdbc:postgresql://10.1.115.176:5432/pir_nn_0_0_1";
        String user = "pir_admin";
        String password = "pir_admin";
        connection = DriverManager.getConnection(url, user, password);

    }

}
