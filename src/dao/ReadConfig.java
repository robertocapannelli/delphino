package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig  {

    private String user, password, dbUrl, driverUrl;

    public void load() {

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("config.properties"));
        } catch (IOException e) {

            System.out.println("File not found");
            e.printStackTrace();
        }

        this.user = properties.getProperty("USER");
        this.password = properties.getProperty("PASSWORD");
        this.dbUrl = properties.getProperty("DB_URL");
        this.driverUrl = properties.getProperty("DRIVER_CLASS_NAME");

    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }

    public String getDbUrl() {
        return this.dbUrl;
    }

    public String getDriverUrl()  {
        return this.driverUrl;
    }

}
