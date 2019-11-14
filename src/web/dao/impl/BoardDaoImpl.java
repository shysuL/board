package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Paging;
import web.dbutil.DBConn;
import web.dao.face.BoardDao;
import web.dto.Board;

public class BoardDaoImpl implements BoardDao{

	private Connection conn = null;// DB연결 객체
	private PreparedStatement ps = null; // SQL 수행 객체
	private ResultSet rs = null; // SQL수행 결과 객체
	
	@Override
	public List<Board> selectAll() {
	
		conn = DBConn.getConnection(); // DB연결

		// 수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT boardno, title, id, content, hit, writtendate FROM board";
		sql += " ORDER BY boardno DESC";
		
		List list = new ArrayList();
		
		try {
			ps = conn.prepareStatement(sql); // 수행 객체 얻기
			
			rs = ps.executeQuery(); // SQL 수행 결과 얻기

			// SQL 수행결과 처리
			while (rs.next()) {
				Board board = new Board();
				
				board.setBoardno(rs.getInt("boardno"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("ID"));
				board.setContent(rs.getString("content"));
				board.setHit(rs.getInt("hit"));
				board.setWrittendate(rs.getDate("WrittenDate"));
				
				list.add(board);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				//    
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				// ---------------------
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
			
		return list;
	}

	@Override
	public Board selectBoardByBoardno(Board board) {
	
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "SELECT * FROM board";
		sql += " WHERE boardno = ?";
		sql += " ORDER BY boardno ";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,  board.getBoardno());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				board.setBoardno(rs.getInt("boardno"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("ID"));
				board.setContent(rs.getString("content"));
				board.setHit(rs.getInt("hit"));
				board.setWrittendate(rs.getDate("WrittenDate"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null)
				try {
					if(rs != null) rs.close();
					if(ps != null) ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return board;
	}

	@Override
	public void updateHit(Board board) {
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "UPDATE board SET hit = hit + 1";
		sql += " WHERE boardno = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1,  board.getBoardno());
			
			rs = ps.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(ps != null) ps.close();
				if(rs != null) rs.close();
				// ---------------------
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int selectCntAll() {
		conn = DBConn.getConnection(); // DB연결

		// 수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT count(*)";
		sql += " FROM board";
		
		
		// 최종 결과 변수
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql); // 수행 객체 얻기
			
			rs = ps.executeQuery(); // SQL 수행 결과 얻기

			// SQL 수행결과 처리
			while (rs.next()) {
				cnt = rs.getInt(1);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				//    
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				// ---------------------
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
			
		return cnt;
	}

	@Override
	public List<Board> selectAll(Paging paging) {
		conn = DBConn.getConnection(); // DB연결

		// 수행할 SQL 쿼리
		String sql = "";
		sql += " ";
		sql += "  ";
		
		
		// 최종 결과 변수
		List list = new ArrayList();
		
		try {
			ps = conn.prepareStatement(sql); // 수행 객체 얻기
			
			rs = ps.executeQuery(); // SQL 수행 결과 얻기

			// SQL 수행결과 처리
			while (rs.next()) {
				Board board = new Board();
				
				board.setBoardno(rs.getInt("boardno"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("ID"));
				board.setContent(rs.getString("content"));
				board.setHit(rs.getInt("hit"));
				board.setWrittendate(rs.getDate("WrittenDate"));
				
				list.add(board);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				//    
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				// ---------------------
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
			
		return list;
	}

}
