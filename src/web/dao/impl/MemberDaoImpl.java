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

		// 수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT count(*) FROM member";
		sql += " WHERE userid = ? and userpw = ?";

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
		
		
		return member;
		
	}

}
