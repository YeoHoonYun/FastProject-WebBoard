package yun.fast.webproject.board.DTO;

import java.util.Date;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-19
 * Github : https://github.com/YeoHoonYun
 */
public class Board {
    private Long id;
    private String title;
    private Long userId;
    private String nickname;
    private String content;
    private String filePath;
    private Date regdate;
    private int readCount;
    private Long groupno;
    private int grpord;
    private int depth;

    public Board(long id, String title, String nickname, Date regdate, int readCount, int depth) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.regdate = regdate;
        this.readCount = readCount;
        this.depth = depth;
    }

    public Board(long id1, String title, String nickname, String content, Date regdate, String filePath, int readCount, Long groupno, int grpord, int depth) {
        this(id1, title, nickname, regdate, readCount, depth);
        this.content = content;
        this.filePath = filePath;
        this.grpord = grpord;
        this.groupno = groupno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public Long getGroupno() {
        return groupno;
    }

    public void setGroupno(Long groupno) {
        this.groupno = groupno;
    }

    public int getGrpord() {
        return grpord;
    }

    public void setGrpord(int grpord) {
        this.grpord = grpord;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
