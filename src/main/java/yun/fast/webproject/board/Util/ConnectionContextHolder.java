package yun.fast.webproject.board.Util;

import java.sql.Connection;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-19
 * Github : https://github.com/YeoHoonYun
 */
public class ConnectionContextHolder {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static void setConnection(Connection connection){
        threadLocal.set(connection);
    }

    public static Connection getConnection(){
        return threadLocal.get();
    }
}
