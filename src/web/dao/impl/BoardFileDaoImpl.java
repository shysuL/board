package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import web.dao.face.BoardFileDao;
import web.dbutil.DBConn;
import web.dto.Boardfile;

public class BoardFileDaoImpl implements BoardFileDao {

	private Connection conn = null;// DB연결 객체
	private PreparedStatement ps = null; // SQL 수행 객체
	private ResultSet rs = null; // SQL수행 결과 객체

	public void delete(Boardfile boardfile) {
		conn = DBConn.getConnection();

		String sql = "";
		sql += "DELETE FROM boardfile";
		sql += " WHERE boardno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1,  boardfile.getBoardno());

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


};



