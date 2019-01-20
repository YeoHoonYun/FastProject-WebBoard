package yun.fast.webproject.board.DAO;

import yun.fast.webproject.board.DAO.Query.BoardDaoSQL;
import yun.fast.webproject.board.DAO.Query.UserDaoSQL;
import yun.fast.webproject.board.DTO.Board;
import yun.fast.webproject.board.DTO.User;
import yun.fast.webproject.board.Util.ConnectionContextHolder;
import yun.fast.webproject.board.Util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-19
 * Github : https://github.com/YeoHoonYun
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void insertUser(User user) {
        Connection conn = ConnectionContextHolder.getConnection();
        try {
            try (PreparedStatement ps1 = conn.prepareStatement(UserDaoSQL.INSERT)) {
                ps1.setString(1, user.getName());
                ps1.setString(2, user.getNickname());
                ps1.setString(3, user.getEmail());
                ps1.setString(4, user.getPasswd());
                ps1.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public String selectPass(String email) {
        Connection conn = ConnectionContextHolder.getConnection();
        String pass = null;
        try {
            try (PreparedStatement ps1 = conn.prepareStatement(UserDaoSQL.SELECT_BY_EMAIL)) {
                ps1.setString(1, email);
                try(ResultSet rs = ps1.executeQuery()){
                    if(rs.next()){
                        pass = rs.getString(1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pass;
    }

    @Override
    public User selectUser(String email) {
        Connection conn = ConnectionContextHolder.getConnection();
        User user = new User();
        try {
            try (PreparedStatement ps1 = conn.prepareStatement(UserDaoSQL.SELECT_BY_USER)) {
                ps1.setString(1, email);
                try(ResultSet rs = ps1.executeQuery()){
                    if(rs.next()){
                        user.setId(rs.getLong(1));
                        user.setName(rs.getString(2));
                        user.setNickname(rs.getString(3));
                        user.setEmail(rs.getString(4));
                        user.setPasswd(rs.getString(5));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
