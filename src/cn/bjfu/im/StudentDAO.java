package cn.bjfu.im;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDAO extends BaseDAO {

	/*
	 * 向student表添加学生信息
	 * 
	 * @method add
	 * 
	 * @param {StudentVO} 要添加的学生信息VO类
	 * 
	 * @return {boolean} 是否添加成功
	 */
	public boolean add(StudentVO vo) {
		boolean f = false;
		String usr = vo.getUsr(), pwd = vo.getPwd();

		if (! checkExist(usr)) {
			Connection conn = getConn();
			String sql = "insert into studentInfo (usr, pwd) values (?, ?)";

			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, usr);
				ps.setString(2, pwd);

				int count = ps.executeUpdate(); // 执行sql

				if (count > 0) {
					f = true;
					System.out.println("insert ok!");
				} else {
					System.out.println("insert failed...");
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(conn);
			}
		}

		return f;
	}
	
	public boolean checkExist(String usr) {
		boolean f = false;
		
		if (findByUsr(usr) != null) {
			f = true;
		}
		
		return f;
	}

	/*
	 * 根据学号查找
	 * 
	 * @method findByUsr
	 * 
	 * @param {String} 用户名
	 * 
	 * @return {StudentVO} 查找到的学生信息VO类
	 */
	public StudentVO findByUsr(String usr) {
		StudentVO vo = null;

		String usrInDb = "", pwdInDb = "";

		Connection conn = getConn();
		String sql = "select usr, pwd from studentInfo where usr=?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, usr);

			ResultSet rs = ps.executeQuery();

			/*
			 * 只有唯一的一个结果。
			 */
			if (rs.next()) {
				usrInDb = rs.getString(1);
				pwdInDb = rs.getString("pwd");
				vo = new StudentVO(usrInDb, pwdInDb);

			} else {
				System.out.println("not find...");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return vo;
	}

}
