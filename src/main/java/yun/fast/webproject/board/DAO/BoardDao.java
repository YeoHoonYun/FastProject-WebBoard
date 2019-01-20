package yun.fast.webproject.board.DAO;

import yun.fast.webproject.board.DTO.Board;

import java.util.List;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-19
 * Github : https://github.com/YeoHoonYun
 */
public interface BoardDao {
    List<Board> selectLists(int startNum, int endNum, String word);

    List<Board> selectLists(int startNum, int endNum);
    Board selectOneBoard(Long id);
    Long lastId(Long id);

    void insertBoard(String title, Long userId, String nickname, String content, String path);
    void deleteBoard(Long id);
    void updateBoard(Long id, String title, String content, String file_path);
    void updateCount(Long id);
    void insertReBoard(String title,Long userId, String nickname, String content, Long groupno, int grpord, int depth);
    void updateGrp(Long groupno, int grpord);

    Long before(Long id);

    Long pre(Long id);

    Long selectView();
}
