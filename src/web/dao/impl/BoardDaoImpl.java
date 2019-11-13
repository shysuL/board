package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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
		sql += " ORDER BY boardno";
		
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
