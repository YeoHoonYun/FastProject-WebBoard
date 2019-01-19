package yun.fast.webproject.board.DAO.Query;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-19
 * Github : https://github.com/YeoHoonYun
 */
public class UserDaoSQL {
    public static final String INSERT = "INSERT INTO user (name, nickname, email, passwd) VALUES (?, ?, ?, ?)";
    public static final String SELECT_BY_EMAIL = "SELECT passwd FROM user WHERE email = ?";
    public static final String SELECT_BY_USER = "SELECT id, name, nickname, email, passwd FROM user WHERE email = ?";
}
