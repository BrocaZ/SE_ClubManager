package control;

import java.sql.Connection;
import java.util.*;

import model.Student;
import util.BaseException;
import util.DBUtil;

public class StudentManager {
	public void modTel(Student s) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql="update stu set tel=? where sno = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, s.getTel());
			pst.setString(2, s.getSno());
			pst.execute();
		} catch (Exception e) {
			throw new BaseException("ÐÞ¸ÄÊ§°Ü");
		}
	}

	public void setPwd(Student s) throws BaseException{
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql="update stu set password=? where sno = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, s.getPassword());
			pst.setString(2, s.getSno());
			pst.execute();
		} catch (Exception e) {
			throw new BaseException("ÐÞ¸ÄÊ§°Ü");
		}
	}

	public void setHeadImage(Student s) throws BaseException{
		Connection conn = null;
		try {
			
		} catch (Exception e) {
			throw new BaseException("ÐÞ¸ÄÊ§°Ü");
		}
	}

}