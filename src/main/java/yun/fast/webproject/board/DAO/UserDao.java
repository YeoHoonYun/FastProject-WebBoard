package yun.fast.webproject.board.DAO;

import yun.fast.webproject.board.DTO.Board;
import yun.fast.webproject.board.DTO.User;

import java.util.List;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-19
 * Github : https://github.com/YeoHoonYun
 */
public interface UserDao {
    public void insertUser(User user);

    String selectPass(String email);

    User selectUser(String email);
}
