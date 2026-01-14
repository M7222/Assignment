import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseContext {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/OOP-Assignment";
        String user = "postgres";
        String password = "mirasa8E";
        return DriverManager.getConnection(url, user, password);
    }
}
