package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDao {

    Connection connection;

    public Connection getConnection() {
        try {
            String name = "root";
            String password = "zky0320";
            String url = "jdbc:mysql://localhost:3306/se_clubmanager?useUnicode=true&characterEncoding=utf8";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, name,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
