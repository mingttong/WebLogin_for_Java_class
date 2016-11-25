package cn.bjfu.im;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends BaseDAO {

	public int countByKeyword(String keyword) {
		int count = 0;

		String sql = "select count(*) as totalCount from studentInfo where usr like ?";
		Connection conn = getConn();

		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getInt("totalCount");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	public List<QueryUserVO> queryByKeyword(String keyword, int iPage) {
		Connection conn = getConn();
		List<QueryUserVO> arr = new ArrayList<QueryUserVO>();
		
		int startIndex = 10*(iPage - 1); // Ä¬ÈÏÃ¿Ò³10Ìõ
		String sql2 = "select usr,pwd from studentInfo where usr like ? limit ?,10";
		try {
			PreparedStatement ps = conn.prepareStatement(sql2);
			ps.setString(1,  "%" + keyword + "%");
			ps.setInt(2,  startIndex);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String usr = rs.getString("usr");
				String pwd = rs.getString("pwd");
				QueryUserVO vo = new QueryUserVO(usr, pwd);
				arr.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return arr;
	}

	public static void main(String[] args) {

	}

}
