package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private Connection connection;

    private static DbConnection instance;

    private DbConnection() throws SQLException {

        connection=DriverManager.getConnection("jdbc/mysql://localhost:3306/hotel_reservation_System","root","200004602360");
    }

    public static DbConnection getInstance() throws SQLException {
        if (instance==null){
            instance=new DbConnection();
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }
}
