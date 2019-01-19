package yun.fast.webproject.board.DAO.Query;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-19
 * Github : https://github.com/YeoHoonYun
 */
public class BoardDaoSQL {
    public static final String SELECT_BY_ID =
            "select id, title, nickname, content, regdate, file_path, read_count " +
                    "from board " +
                    "where id = ? ";
    public static final String SELECT_BY_PAGING =
            "select id, title, nickname, regdate, read_count, depth " +
                    "from board " +
                    "order by groupno desc, grpord";
    public static final String LAST_ID =
            "UPDATE board SET groupno = LAST_INSERT_ID() where id = ?";
    public static final String INSERT =
            "insert into board(title,user_id,nickname,content,groupno                ,grpord,depth,file_path) " +
                    "   select ?    ,      ?,       ?,      ?,IFNULL(MAX(groupno)+1,1)        ,0     ,1    ,? from board limit 1";
    public static final String UPDATE =
            "UPDATE board SET title = ?, content = ?, file_path = ? WHERE id = ?";
    public static final String DELETE =
            "delete from board where id = ?";
    public static final String UPDATE_COUNT =
            "UPDATE board SET read_count = read_count + 1 WHERE id = ?";
    public static final String UPDATE_GRPORD =
            "UPDATE board SET grpord = grpord + 1 where groupno = ? and grpord >= ? + 1";
    public static final String INSERT_GROUP =
            "insert into board (title,user_id,nickname,content,groupno,grpord,depth,file_path) " +
                    "    values (?    ,?      , ?     , ?      , ?      ,? + 1    ,? + 1,?)";
}