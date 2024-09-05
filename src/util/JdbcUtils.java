package util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author yjl~
 * @version 1.0
 */
public class JdbcUtils {
    private static String driver=null;
    private static String url=null;
    private static String username=null;
    private static String password=null;

    static {
        try{
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(is);

            driver=properties.getProperty("driver");
            url=properties.getProperty("url");
            username=properties.getProperty("username");
            password=properties.getProperty("password");

            //驱动只加载一次
            Class.forName(driver);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

    //释放连接
    public static void release(Connection cnn, Statement stmt, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(cnn!=null){
            try {
                cnn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
