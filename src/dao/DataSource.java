package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource extends ReadConfig {

    /**
     * Returns a connection to the database
     * @return Connection
     *
     */

    protected Connection getConnection() throws SQLException, ClassNotFoundException{

        ReadConfig rc = new ReadConfig();
        rc.load();

        String USER = rc.getUser();
        String PASSWORD = rc.getPassword();
        String DB_URL = rc.getDbUrl();
        String DRIVER = rc.getDriverUrl();

        Connection connection;

        Class.forName(DRIVER);
        connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

        return connection;
    }

    public static void main(String[] args){

        try{

            DataSource ds = new DataSource();

            System.out.println(ds.getConnection());

        }catch (SQLException | ClassNotFoundException e){

            e.printStackTrace();

        }

    }

}
