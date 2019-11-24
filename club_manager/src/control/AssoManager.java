package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import util.DbException;
//import model;

import model.Association;
import model.Student;
import util.BusinessException;
import util.DBUtil;

public class AssoManager {

	// method that asso need
	//修改社团，logo虽然写在这，但是应该是没啥用的，修改logo用setAssoLogo
	public void modAsso(Association asso) throws DbException, BusinessException {
		java.sql.Connection conn = null;
		int assoid = asso.getAssociationId();
		if(asso.getAssociationName() == null || asso.getAssociationName().equals("")) {
			throw new BusinessException("名字不能为空");
		}
		if(asso.getChiefSno() == null || asso.getChiefSno().equals("")) {
			throw new BusinessException("社长学号不能为空");
		}
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE asso set placeId=?,associationName=?,logo=?,chiefSno=?,brief_introduction=?,state=?,remarks=? WHERE associationId = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(8, assoid);
			pst.setInt(1, asso.getPlacId());
			pst.setString(2, asso.getAssociationName());
			pst.setBytes(3, asso.getLogo());
			pst.setString(4, asso.getChiefSno());
			pst.setString(5, asso.getIntro());
			pst.setString(6, asso.getStatus());
			pst.setString(7, asso.getRemarks());
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public Association addAsso(Association asso) throws DbException, BusinessException {
		java.sql.Connection conn = null;
		int assoid = 0;
		if(asso.getAssociationName() == null || asso.getAssociationName().equals("")) {
			throw new BusinessException("名字不能为空");
		}
		if(asso.getChiefSno() == null || asso.getChiefSno().equals("")) {
			throw new BusinessException("社长学号不能为空");
		}
		try {
			conn = DBUtil.getConnection();
			String sql = "select MAX(AssociationId) from asso";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				assoid = rs.getInt(1);
			}
			assoid += 1;
			asso.setAssociationId(assoid);
			sql = "INSERT into asso(AssociationId,placeId,associationName,logo,chiefSno,brief_introduction,state,remarks) VALUES(?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, assoid);
			pst.setInt(2, asso.getPlacId());
			pst.setString(3, asso.getAssociationName());
			pst.setBytes(4, asso.getLogo());
			pst.setString(5, asso.getChiefSno());
			pst.setString(6, asso.getIntro());
			pst.setString(7, asso.getStatus());
			pst.setString(8, asso.getRemarks());

			pst.execute();
			
			//将社长加入社团人员表
			sql = "INSERT into asso_p(sno,associationId,role) VALUES (?,?,'社长')";
			pst = conn.prepareStatement(sql);
			pst.setString(1, asso.getChiefSno());
			pst.setInt(2, assoid);
			pst.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return asso;
	}
	//还得删其他的表，还没写
	//删除社团，如果有相关信息的话，是在这个方法里报错，还是直接在这个方法里删掉？
	//现在社团相关的活动这些还没删。。。忒麻烦了。删活动还得删活动人员表。。QAQ
	public void delAsso(int assoid) throws DbException {
		java.sql.Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			
			//删除社团人员表的相关信息
			String sql = "delete from asso_p WHERE associationId = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, assoid);
			pst.execute();
			
			//删除社团表中的信息
			sql = "DELETE from asso where associationId = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, assoid);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	//返回所有的社团
	public List<Association> assoList(){
		List<Association> res = new ArrayList<Association>();
		java.sql.Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select AssociationId,placeId,associationName,logo,chiefSno,brief_introduction,state,remarks from asso";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Association a = new Association();
				a.setAssociationId(rs.getInt(1));
				a.setPlacId(rs.getInt(2));
				a.setAssociationName(rs.getString(3));
				a.setLogo(rs.getBytes(4));
				a.setChiefSno(rs.getString(5));
				a.setIntro(rs.getString(6));
				a.setStatus(rs.getString(7));
				a.setRemarks(rs.getString(8));
				res.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
//			throw new util.DbException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return res;
	}
	
	//untest
	//在社团人员表查找学生所在社团获得id之后调用这个方法获取社团
	//先写着也不知道有用没用
	//这里logo虽然有返回，但是没啥用，读也不从这里读，但是写还是写着了，不知道咋读
	public Association searchAssoById(int assoid) throws DbException {
		Association a = new Association();
		java.sql.Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select AssociationId,placeId,associationName,logo,chiefSno,brief_introduction,state,remarks from asso where associationId = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, assoid);
			java.sql.ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				a.setAssociationId(rs.getInt(1));
				a.setPlacId(rs.getInt(2));
				a.setAssociationName(rs.getString(3));
				a.setLogo(rs.getBytes(4));
				a.setChiefSno(rs.getString(5));
				a.setIntro(rs.getString(6));
				a.setStatus(rs.getString(7));
				a.setRemarks(rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new util.DbException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return a;
	}
	//untest
	//模糊查找
	public List<Association> searchAssoByName(String name){
		List<Association> res = new ArrayList<Association>();
		java.sql.Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select AssociationId,placeId,associationName,logo,chiefSno,brief_introduction,state,remarks from asso where associationName LIKE ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "%"+name+"%");
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Association a = new Association();
				a.setAssociationId(rs.getInt(1));
				a.setPlacId(rs.getInt(2));
				a.setAssociationName(rs.getString(3));
				a.setLogo(rs.getBytes(4));
				a.setChiefSno(rs.getString(5));
				a.setIntro(rs.getString(6));
				a.setStatus(rs.getString(7));
				a.setRemarks(rs.getString(8));
				res.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
//			throw new util.DbException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return res;
	}
	// method need student and asso
	//判断表中有没有之后 再增加
	//社长调用的 线下面试之后由社长直接将社员加入
	public void joinAsso(String sno, int assoid) throws DbException, BusinessException {
		java.sql.Connection conn = null;
		try {
			//判断社员在不在这个社团
			conn = DBUtil.getConnection();
			String sql = "select * from asso_p where sno = ? and associationId = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				throw new BusinessException("该成员已在该社");
			}
			sql = "INSERT into asso_p(sno,associationId,role) VALUES (?,?,'社员')";
			pst = conn.prepareStatement(sql);
			pst.setString(1, sno);
			pst.setInt(2, assoid);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new util.DbException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	//也是社长直接操作，将社员退社
	public void exitAsso(String sno,int assoid) throws DbException {
		// TODO: implement
		java.sql.Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from asso_p where sno = ? and associationId = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, sno);
			pst.setInt(2, assoid);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new util.DbException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	//好像不用申请，所以就不审核了吧，社长直接退
//	public int checkExitAsso() {
//		// TODO: implement
//		return 0;
//	}
	//返回该社的成员，返回一个student的List是社长和社员都包含的，需要社长信息的话，用assoLeader方法
	//学生的信息只包含sno,name,sex,tel,affiliated_branch,major,class这些字段
	public List<Student> assoMemberList(int assoid){
		// TODO: implement
		List<Student> res = new ArrayList<Student>();
		java.sql.Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select sno,name,sex,tel,affiliated_branch,major,class from stu WHERE sno in (select sno from asso_p WHERE associationId = ?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, assoid);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Student stu = new Student();
				stu.setSno(rs.getString(1));
				stu.setName(rs.getString(2));
				stu.setSex(rs.getString(3));
				stu.setTel(rs.getString(4));
				stu.setBranch(rs.getString(4));
				stu.setMajor(rs.getString(5));
				stu.setStuclass(rs.getString(5));
				
				res.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
//			throw new util.DbException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return res;
	}
	
	//返回社长信息 只含sno,name,sex,tel,affiliated_branch,major,class这些字段
	public Student assoLeader(int assoid) {
		// TODO: implement
		Student stu = new Student();
		java.sql.Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select sno,name,sex,tel,affiliated_branch,major,class from stu WHERE sno in (select chiefsno from asso WHERE associationId = ?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, assoid);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				stu.setSno(rs.getString(1));
				stu.setName(rs.getString(2));
				stu.setSex(rs.getString(3));
				stu.setTel(rs.getString(4));
				stu.setBranch(rs.getString(4));
				stu.setMajor(rs.getString(5));
				stu.setStuclass(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
//			throw new util.DbException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return stu;
	} 
	
	void setAssoLogo(int assoid,String filepath) {
		File f = new File(filepath);
		Connection conn = null;
		try(FileInputStream fis = new FileInputStream(f)){
			conn = DBUtil.getConnection();
			String sql = "update asso set logo = ? where associationId = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setBinaryStream(1, fis,f.length());
			pst.setInt(2, assoid);
			pst.execute();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	void getAssoLogo(int assoid,String filepath) {
		Connection conn = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "select logo from asso where associationId = ?";
//			java.sql.Statement st = conn.createStatement();
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, assoid);
			java.sql.ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				java.sql.Blob b = rs.getBlob(1);
				File f = new File(filepath);
				try(FileOutputStream fos = new FileOutputStream(f)){
					fos.write(b.getBytes(1, (int) b.length()));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		AssoManager am = new AssoManager();
		Association asso = new Association();
//		asso.setAssociationId(10);
		asso.setAssociationName("test");
		asso.setChiefSno("31701005");
		asso.setPlacId(1);
		try {
//			asso = am.addAsso(asso);//因为会返回assoid 所以调用的时候要重新赋值
			
//			asso = am.searchAssoById(2);
//			System.out.println(asso.getAssociationName());
			
//			asso = am.addAsso(asso);
//			asso.setAssociationName("test3");
//			System.out.println(asso.getAssociationId());
//			am.modAsso(asso);
			am.delAsso(11);
			
//			am.setAssoLogo(7, "testlogo.jpg"); //设置社团logo
//			am.getAssoLogo(7, "outputtestlogo.jpg");//取出logo
			
//			List<Association> l = am.assoList(); //返回所有社团
//			List<Association> l = am.searchAssoByName("协会"); //模糊查找
//			for(Association a :l) {
//				System.out.println(a.getAssociationName());
//			}
			
//			Student stu = am.assoLeader(2);//返回对应id的社团的社长
//			System.out.println(stu.getName());
			
//			List<Student> lstu = am.assoMemberList(2);
//			for(Student s: lstu) {
//				System.out.println(s.getName());
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ok");
	}
}