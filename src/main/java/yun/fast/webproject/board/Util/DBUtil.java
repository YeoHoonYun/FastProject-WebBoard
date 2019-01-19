package yun.fast.webproject.board.Util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-19
 * Github : https://github.com/YeoHoonYun
 */
public class DBUtil {
    private static HikariConfig config = null;
    private static DataSource ds = null;
    private static DBUtil instance = new DBUtil();

    private DBUtil(){
        String configFile = "/datasource.properties";
        HikariConfig config = new HikariConfig(configFile);
        ds = new HikariDataSource(config);
    }
    public static DBUtil getInstance(){
        return instance;
    }

    public static Connection getConnection(){
        Connection conn = null;
        try{
            conn = ds.getConnection();
            conn.setAutoCommit(false);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("DB연결을 할 수 없습니다.");
        }
        return conn;
    }
    public static void rollback(Connection conn){
        try{ conn.rollback(); } catch(Exception ignore){}
    }
    public static void close(Connection conn){
        try{ conn.close(); } catch(Exception ignore){}
    }
}
