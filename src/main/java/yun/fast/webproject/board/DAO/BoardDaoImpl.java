package yun.fast.webproject.board.DAO;

import yun.fast.webproject.board.DAO.Query.BoardDaoSQL;
import yun.fast.webproject.board.DTO.Board;
import yun.fast.webproject.board.Util.*;

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
public class BoardDaoImpl implements BoardDao {

    @Override
    public List<Board> selectLists() {
        List<Board> boardList = new ArrayList<>();
        Connection conn = ConnectionContextHolder.getConnection();
        try{
            try(PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.SELECT_BY_PAGING)){
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()) {
                        long id = rs.getLong(1);
                        String title = rs.getString(2);
                        String nickname = rs.getString(3);
                        Date regdate = rs.getDate(4);
                        int readCount = rs.getInt(5);
                        int depth = rs.getInt(6);
                        boardList.add(new Board(id, title, nickname, regdate, readCount,depth));
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return boardList;
    }

    @Override
    public Board selectOneBoard(Long id) {
        Board board = null;
        Connection conn = ConnectionContextHolder.getConnection();
        try{
            try(PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.SELECT_BY_ID)){
                System.out.println(id);
                ps.setLong(1, id);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()) {
                        long id1 = rs.getLong(1);
                        String title = rs.getString(2);
                        String nickname = rs.getString(3);
                        String content = rs.getString(4);
                        Date regdate = rs.getDate(5);
                        String filePath = rs.getString(6);
                        int readCount = rs.getInt(7);

                        board = new Board(id1, title, nickname, content, regdate, filePath, readCount);
                    }else{
                        System.out.println("값이 없는데??");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return board;
    }

    @Override
    public Long lastId(Long id) {
        Long lastId = 0L;

        Connection conn = ConnectionContextHolder.getConnection();
        try{
            try(PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.LAST_ID);) {
                ps.setLong(1, id);
                ps.executeUpdate();
                conn.commit();
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }

        return lastId;
    }

    @Override
    public void insertBoard(String title, Long userId, String nickname, String content, Long lastId, String path) {
        Connection conn = ConnectionContextHolder.getConnection();
        try {
            try (PreparedStatement ps1 = conn.prepareStatement(BoardDaoSQL.INSERT)) {
                ps1.setString(1, title);
                ps1.setLong(2, userId);
                ps1.setString(3, nickname);
                ps1.setString(4, content);
//                ps1.setLong(5, lastId);
                ps1.setString(5, path);
                ps1.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteBoard(Long id) {
        Connection conn = ConnectionContextHolder.getConnection();
        try {
            try (PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.DELETE)) {
                ps.setLong(1, id);

                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBoard(Long id, String title, String content, String file_path) {
        Connection conn = ConnectionContextHolder.getConnection();
        try {
            try (PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.UPDATE)) {
                ps.setString(1, title);
                ps.setString(2, content);
                ps.setString(3, file_path);
                ps.setLong(4, id);

                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCount(Long id) {
        Connection conn = ConnectionContextHolder.getConnection();
        try {
            try (PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.UPDATE_COUNT)) {
                ps.setLong(1, id);

                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createGrp(String title, String userId, String content, int groupno, int grpord, int depth) {
        Connection conn = ConnectionContextHolder.getConnection();
        try {
            try (PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.INSERT_GROUP)) {
                ps.setString(1, title);
                ps.setString(2,userId);
                ps.setString(3,content);
                ps.setLong(4,groupno);
                ps.setInt(5,grpord);
                ps.setInt(6,depth);

                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateGrp(int groupno, int grpord) {
        Connection conn = ConnectionContextHolder.getConnection();
        try {
            try (PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.UPDATE_GRPORD)) {
                ps.setLong(1, groupno);
                ps.setInt(2, grpord);

                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
