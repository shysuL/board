package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dao.face.CommentDao;
import web.dbutil.DBConn;
import web.dto.Board;
import web.dto.Comment;

public class CommentDaoImpl implements CommentDao{

	private Connection conn = null;// DB연결 객체
	private PreparedStatement ps = null; // SQL 수행 객체
	private ResultSet rs = null; // SQL수행 결과 객체
	
	@Override
	public List selectComment(Board board) {
		conn = DBConn.getConnection();

		String sql
		= "SELECT * FROM ("
				+ "SELECT rownum rnum, B.* FROM ("
				+ "	SELECT"
				+ "		commentno,"
				+ "		boardno,"
				+ "		userid,"
				+ "		content,"
				+ "		writtendate"
				+ "	FROM commentTb"
				+ "	WHERE boardno = ?"
				+ "	ORDER BY writtendate"
				+ "	) B"
				+ ") ORDER BY rnum";

		List commentList = new ArrayList();
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, board.getBoardno() );

			// ResultSet 반환
			rs = ps.executeQuery();

			while( rs.next() ) {
				Comment comment = new Comment();

				comment.setRnum(rs.getInt("rnum"));
				comment.setBoardNo(rs.getInt("boardno"));
				comment.setCommentNo(rs.getInt("commentno"));
				comment.setUserid(rs.getString("userid"));
				comment.setContent(rs.getString("content"));
				comment.setWrittenDate(rs.getDate("writtendate"));

				commentList.add(comment);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return commentList;
	}

	@Override
	public void insertComment(Comment comment) {
		conn = DBConn.getConnection();

		String sql
		= "INSERT INTO commentTB ("
				+ "		commentno,"
				+ "		boardno,"
				+ "		userid,"
				+ "		content )"
				+ "	VALUES ("
				+ "		commentTb_seq.nextval,"
				+ "		?,"
				+ "		?,"
				+ "		? )";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, comment.getBoardNo());
			ps.setString(2, comment.getUserid());
			ps.setString(3, comment.getContent());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void deleteComment(Comment comment) {
		conn = DBConn.getConnection();

		String sql
		= "DELETE commentTB"
				+ "	WHERE commentno = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getCommentNo());
			
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int countComment(Comment comment) {
		conn = DBConn.getConnection();

		String sql = "SELECT COUNT(*) FROM commenttb WHERE commentno=?";
		
		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getCommentNo());
			rs = ps.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
	}

}
