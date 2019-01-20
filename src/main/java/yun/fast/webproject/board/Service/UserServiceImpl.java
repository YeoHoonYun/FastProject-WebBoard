package yun.fast.webproject.board.Service;

import yun.fast.webproject.board.DAO.BoardDao;
import yun.fast.webproject.board.DAO.BoardDaoImpl;
import yun.fast.webproject.board.DAO.UserDao;
import yun.fast.webproject.board.DAO.UserDaoImpl;
import yun.fast.webproject.board.DTO.Board;
import yun.fast.webproject.board.DTO.User;
import yun.fast.webproject.board.Util.ConnectionContextHolder;
import yun.fast.webproject.board.Util.DBUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-19
 * Github : https://github.com/YeoHoonYun
 */
public class UserServiceImpl implements UserService {
    private static UserService instance = new UserServiceImpl();
    private UserServiceImpl(){}
    public static UserService getInstance(){
        return instance;
    }

    @Override
    public void userAdd(User user) {
        Connection conn = null;
        UserDao userDao = new UserDaoImpl();
        try{
            conn = DBUtil.getConnection();
            ConnectionContextHolder.setConnection(conn);
            userDao.insertUser(user);
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.rollback(conn);
            DBUtil.close(conn);
        }
    }
    @Override
    public String selectPass(String email) {
        Connection conn = null;
        UserDao userDao = new UserDaoImpl();
        String pass = null;
        try{
            conn = DBUtil.getConnection();
            ConnectionContextHolder.setConnection(conn);
            pass = userDao.selectPass(email);
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.rollback(conn);
            DBUtil.close(conn);
        }
        return pass;
    }
    @Override
    public User selectUser(String email) {
        Connection conn = null;
        UserDao userDao = new UserDaoImpl();
        User user1 = null;
        try{
            conn = DBUtil.getConnection();
            ConnectionContextHolder.setConnection(conn);
            user1 = userDao.selectUser(email);
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.rollback(conn);
            DBUtil.close(conn);
        }
        return user1;
    }
}
