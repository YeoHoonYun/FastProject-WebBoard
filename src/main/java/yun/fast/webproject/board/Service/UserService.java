package yun.fast.webproject.board.Service;

import yun.fast.webproject.board.DTO.User;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-19
 * Github : https://github.com/YeoHoonYun
 */
public interface UserService {
    void userAdd(User user);

    String selectPass(String user);

    User selectUser(String email);
}
