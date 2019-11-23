package control;

import java.io.File;
import java.io.FileInputStream;
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

	public void setHeadImage(Student s,String path) throws BaseException{
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			FileInputStream images =new FileInputStream(new File(path));
			String sql="update stu set head_image=? where sno = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setBinaryStream(1, images, images.available());
			pst.setString(2, s.getSno());
			pst.execute();
		} catch (Exception e) {
//			throw new BaseException(e.getMessage());
			throw new BaseException("ÐÞ¸ÄÊ§°Ü");
		}
	}
	
	public static void main(String[] args) throws BaseException {
		StudentManager sm=new StudentManager();
		Student s=null;
		
//		System.out.println("Test1");
//		s=new Student();
//		s.setSno("31701004");
//		s.setTel("13588332884");
//		sm.modTel(s);
//		
//		System.out.println("Test2");
//		s=new Student();
//		s.setSno("31701004");
//		s.setPassword("987654321");
//		sm.setPwd(s);
		
		System.out.println("Test3");
		String path="C:\\Users\\ANARKHWQH\\Desktop\\init_head_image.jpeg";
		s=new Student();
		s.setSno("31701004");
		sm.setHeadImage(s, path);
		
	}
}