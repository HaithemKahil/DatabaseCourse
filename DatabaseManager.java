
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;
public class DatabaseManager {
    String url="jdbc:sqlserver://databasesupeccourse.database.windows.net:1433;" +
            "database=TestDatabase;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
    String user="TestDatabase@databasesupeccourse";
    String password="Haithem.1";
    private static final Logger log;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log =Logger.getLogger(DatabaseManager.class.getName());
    }

    public static void main(String[] args) throws Exception {
        log.info("Loading application properties");
        Properties properties = new Properties();
        properties.load(DatabaseManager.class.getClassLoader().getResourceAsStream("application.properties"));

        log.info("Connecting to the database");
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        log.info("Database connection test: " + connection.getCatalog());

        String sql = "if not exists (select * from sysobjects where name='produit' and xtype='U')" +
                "CREATE TABLE PRODUIT" +
                "(id INTEGER not NULL, " +
                " designation VARCHAR(255))";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql);



        /*
        Todo todo = new Todo(1L, "configuration", "congratulations, you have set up JDBC correctly!", true);
        insertData(todo, connection);
        todo = readData(connection);
        todo.setDetails("congratulations, you have updated data!");
        updateData(todo, connection);
        deleteData(todo, connection);
        */

        log.info("Closing database connection");
        connection.close();
    }
    
}
