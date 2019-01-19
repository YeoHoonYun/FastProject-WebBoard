package yun.fast.webproject.board.Service;

import yun.fast.webproject.board.DTO.Board;

import java.util.List;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-19
 * Github : https://github.com/YeoHoonYun
 */
public interface BoardService {
    List<Board> selectLists();
    Board selectOneBoard(Long id);
    void lastId(long id);

    void insertBoard(String title, Long userId, String nickname, String content, Long lastId, String path);
    void deleteBoard(Long id);
    void updateBoard(Long id, String title, String content, String file_path);
    void updateCount(Long id);
    void createGrp(String title, String userId, String content, int groupno, int grpord, int depth);
    void updateGrp(int groupno, int grpord);
}
