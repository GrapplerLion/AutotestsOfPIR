package helpers;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
public class DatabaseTest {

    private DatabaseHelper databaseHelper;

    @Test
    public void testDataRetrieval() throws SQLException {
        databaseHelper = new DatabaseHelper();
        databaseHelper.connectToDatabase();
        String tableName = "acc_view";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://10.1.115.176:5432/pir_nn_0_0_1", "pir_admin", "pir_admin")) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM " + tableName);

            // Получаем метаданные результата запроса
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Проверяем наличие колонок
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                assertTrue(columnName.equals("АС"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
