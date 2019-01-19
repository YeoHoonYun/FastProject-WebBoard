package yun.fast.webproject.board.Service;

import yun.fast.webproject.board.DAO.BoardDao;
import yun.fast.webproject.board.DAO.BoardDaoImpl;
import yun.fast.webproject.board.DTO.Board;
import yun.fast.webproject.board.Util.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-19
 * Github : https://github.com/YeoHoonYun
 */
public class BoardServiceImpl implements BoardService {
    @Override
    public List<Board> selectLists() {
        BoardDao boardDao = new BoardDaoImpl();
        List<Board> boardList = new ArrayList<>();
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            ConnectionContextHolder.setConnection(conn);
            boardList = boardDao.selectLists();
            conn.commit();
        }catch (Exception e){
            DBUtil.rollback(conn);
            e.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }
        return boardList;
    }

    @Override
    public Board selectOneBoard(Long id) {
        BoardDao boardDao = new BoardDaoImpl();
        Connection conn = null;
        Board board = null;
        try{
            conn = DBUtil.getConnection();
            ConnectionContextHolder.setConnection(conn);
            board = boardDao.selectOneBoard(id);
            boardDao.updateCount(id);
            conn.commit();
        }catch (Exception e){
            DBUtil.rollback(conn);
            e.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }
        return board;
    }

    @Override
    public void lastId(long id) {
        BoardDao boardDao = new BoardDaoImpl();
        Connection conn = null;
        Long lastId = 0L;
        try{
            conn = DBUtil.getConnection();
            ConnectionContextHolder.setConnection(conn);
            boardDao.lastId(id);
            conn.commit();
        }catch (Exception e){
            DBUtil.rollback(conn);
            e.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public void insertBoard(String title, Long userId, String nickname, String content,Long lastId, String path) {
        Connection conn = null;
        BoardDao boardDao = new BoardDaoImpl();
        try{
            conn = DBUtil.getConnection();
            ConnectionContextHolder.setConnection(conn);

            boardDao.insertBoard(title, userId, nickname, content,lastId ,path);
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.rollback(conn);
            DBUtil.close(conn);
        }
    }

    @Override
    public void deleteBoard(Long id) {
        Connection conn = null;
        BoardDao boardDao = new BoardDaoImpl();
        try{
            conn = DBUtil.getConnection();
            ConnectionContextHolder.setConnection(conn);

            boardDao.deleteBoard(id);
            conn.commit();
        }catch (Exception e){
            DBUtil.rollback(conn);
            e.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public void updateBoard(Long id, String title, String content, String file_path) {
        Connection conn = null;
        BoardDao boardDao = new BoardDaoImpl();
        try{
            conn = DBUtil.getConnection();
            ConnectionContextHolder.setConnection(conn);

            boardDao.updateBoard(id, title, content, file_path);
            conn.commit();
        }catch (Exception e){
            DBUtil.rollback(conn);
            e.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public void updateCount(Long id) {
        Connection conn = null;
        BoardDao boardDao = new BoardDaoImpl();
        try{
            conn = DBUtil.getConnection();
            ConnectionContextHolder.setConnection(conn);

            boardDao.updateCount(id);
            conn.commit();
        }catch (Exception e){
            DBUtil.rollback(conn);
            e.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public void createGrp(String title, String userId, String content, int groupno, int grpord, int depth) {
        Connection conn = null;
        BoardDao boardDao = new BoardDaoImpl();
        try{
            conn = DBUtil.getConnection();
            ConnectionContextHolder.setConnection(conn);

            boardDao.createGrp(title, userId, content, groupno, grpord, depth);
            conn.commit();
        }catch (Exception e){
            DBUtil.rollback(conn);
            e.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public void updateGrp(int groupno, int grpord) {
        Connection conn = null;
        BoardDao boardDao = new BoardDaoImpl();
        try{
            conn = DBUtil.getConnection();
            ConnectionContextHolder.setConnection(conn);

            boardDao.updateGrp(groupno, grpord);
            conn.commit();
        }catch (Exception e){
            DBUtil.rollback(conn);
            e.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }
    }
}
