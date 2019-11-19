package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dbutil.DBConn;
import web.dto.Member;
import web.dao.face.MemberDao;

public class MemberDaoImpl implements MemberDao{

	private Connection conn = null;// DB연결 객체
	private PreparedStatement ps = null; // SQL 수행 객체
	private ResultSet rs = null; // SQL수행 결과 객체

	
	@Override
	public int selectCntMemberByUserid(Member member) {
		conn = DBConn.getConnection(); // DB연결

		if(member.getUserid() == null || member.getUserpw() == null) {
			return -1;
		}
		
		// 수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT count(*) FROM member";
		sql += " WHERE 1=1";
		sql	+= " userid = ? ";
		sql	+= " AND userpw = ?";

		int cnt = -1;
		Member mem = new Member(); // 각 행을 처리할 DTO
		try {
			ps = conn.prepareStatement(sql); // 수행 객체 얻기
			
			ps.setString(1, member.getUserid() );
			ps.setString(2, member.getUserpw() );
			
			rs = ps.executeQuery(); // SQL 수행 결과 얻기

			// SQL 수행결과 처리
			while (rs.next()) {
				cnt = rs.getInt(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	public Member selectMemberByUserid(Member member) {
		conn = DBConn.getConnection(); // DB연결

		// 수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT * FROM member";
		sql += " WHERE userid = ? ";

		
		Member mem = new Member(); // 각 행을 처리할 DTO
		try {
			ps = conn.prepareStatement(sql); // 수행 객체 얻기
			
			ps.setString(1, member.getUserid() );
			
			
			rs = ps.executeQuery(); // SQL 수행 결과 얻기

			// SQL 수행결과 처리
			while (rs.next()) {
				member.setUserid(rs.getString("userid") );
				member.setUserpw(rs.getString("userpw") );
				member.setUsernick(rs.getString("usernick") );
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//    
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				// ---------------------
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		
		return member;
		
	}

	@Override
	public void insert(Member member) {
		conn = DBConn.getConnection(); // DB연결

		// 수행할 SQL 쿼리
		String sql = "";
		sql += "INSERT INTO  member(userid, userpw, usernick)";
		sql += " VALUES (?,?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			// SQL쿼리의 ? 채우기
			ps.setString(1, member.getUserid() ); 
			ps.setString(2, member.getUserpw() ); 
			ps.setString(3, member.getUsernick() ); 
			
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

}
