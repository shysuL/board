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
import web.dto.Boardfile;

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
		sql += "SELECT * FROM("  ;
		sql += " SELECT rownum rnum, B.* FROM(" ;
		sql +=	" SELECT " ; 
		sql +=	" boardno, title, id, content, hit, writtendate" ;
		sql +=	" FROM board"; 
		sql +=	" ORDER BY boardno DESC" ;
		sql +=	" ) B"  ;
		sql +=	" ORDER BY rnum" ;
		sql +=	" ) BOARD" ;
		sql +=	" WHERE rnum BETWEEN ? AND ? ";


		// 최종 결과 변수
		List list = new ArrayList();
		
		try {
			ps = conn.prepareStatement(sql); // 수행 객체 얻기
			
			ps.setInt(1,  paging.getStartNo());
			ps.setInt(2,  paging.getEndNo());
			
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
	public void insert(Board board) {
		conn = DBConn.getConnection(); // DB연결

		// 수행할 SQL 쿼리
		String sql = "";
		sql += "INSERT INTO  board(boardno";
		sql += " , title";
		sql += " , id";
		sql += " , content";
		sql += " , hit)";
		sql += " VALUES (?,?,?,?,0)" ;
		
		try {
			ps = conn.prepareStatement(sql);
			
			// SQL쿼리의 ? 채우기
			
			ps.setInt(1, board.getBoardno() ); 
			ps.setString(2, board.getTitle() ); 
			ps.setString(3, board.getId() ); 
			ps.setString(4, board.getContent() ); 
//			System.out.println("ididid: " + board.getId());
			
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(ps != null) ps.close();
				// ---------------------
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public int selectBoardno() {
		conn = DBConn.getConnection(); // DB연결
		
		String sql = "";
		sql += "select board_seq.nextval from dual";
		
		int cnt = -1;
		try {
			ps= conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				cnt = rs.getInt("nextval");
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
		
		
		System.out.println("[BoardDao] selectBoardno : " + cnt);
		return cnt;
	}

	@Override
	public void insertFile(Boardfile boardFile) {
		
		System.out.println(boardFile);
		
		conn = DBConn.getConnection(); // DB연결
		
		String sql = "";
		sql += "INSERT INTO boardfile(fileno,boardno, originname, storedname, filesize)";
		sql += " VALUES(boardFile_seq.nextval,?,?,?,?)";

		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1,  boardFile.getBoardno());
			ps.setString(2, boardFile.getOriginname()); 
			ps.setString(3, boardFile.getStoredname());
			ps.setInt(4, boardFile.getFilesize());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if(ps != null) ps.close();
				// ---------------------
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public Board selectByBoardno(Board board) {
		conn = DBConn.getConnection(); // DB연결

		// 수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT * FROM board"  ;
		sql += " WHERE boardno = ?" ;
		


		
		
		try {
			ps = conn.prepareStatement(sql); // 수행 객체 얻기
			
			ps.setInt(1,  board.getBoardno());
			
			rs = ps.executeQuery(); // SQL 수행 결과 얻기

			// SQL 수행결과 처리
			while (rs.next()) {
				
				
				board.setBoardno(rs.getInt("boardno"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("ID"));
				board.setContent(rs.getString("content"));
				board.setHit(rs.getInt("hit"));
				board.setWrittendate(rs.getDate("WrittenDate"));
				
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
			
			
		return board;
	}

	@Override
	public void update(Board board) {
		conn = DBConn.getConnection(); // DB연결

		// 수행할 SQL 쿼리
		String sql = "";
		sql += "UPDATE board SET title = ? , content = ?";
		sql += " WHERE boardno = ?";

		
		try {
			ps = conn.prepareStatement(sql);
			
			// SQL쿼리의 ? 채우기
			ps.setString(1, board.getTitle() );
			ps.setString(2, board.getContent() );
			ps.setInt(3, board.getBoardno() ); 
			
			 
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(ps != null) ps.close();
				// ---------------------
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	
	@Override
	public Boardfile selectBoardfileByBoardno(Boardfile boardfile) {
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "SELECT * FROM boardfile";
		sql += " WHERE boardno = ?";
		sql += " ORDER BY boardno ";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,  boardfile.getBoardno());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				boardfile.setFileno(rs.getInt("fileno"));
				boardfile.setBoardno(rs.getInt("boardno"));
				boardfile.setOriginname(rs.getString("originname"));
				boardfile.setStoredname(rs.getString("storedname"));
				boardfile.setFilesize(rs.getInt("filesize")); 
				boardfile.setWritedate(rs.getDate("WriteDate"));
				
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
		
		return boardfile;
	}

	@Override
	public void selectByFileno(Boardfile boardfile) {
		// DB연결 객체
		conn = DBConn.getConnection();
		
		// 수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT * FROM boardfile";
		sql += " WHERE fileno = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,  boardfile.getBoardno());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				boardfile.setFileno(rs.getInt("fileno"));
				boardfile.setBoardno(rs.getInt("boardno"));
				boardfile.setOriginname(rs.getString("originname"));
				boardfile.setStoredname(rs.getString("storedname"));
				boardfile.setFilesize(rs.getInt("filesize")); 
				boardfile.setWritedate(rs.getDate("WriteDate"));
				
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
		
	}

	@Override
	public void delete(Board board) {
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "DELETE FROM board";
		sql += " WHERE boardno = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1,  board.getBoardno());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null)
				try {
					if(ps != null) ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

}
