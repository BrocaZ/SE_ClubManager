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
	//�޸����ţ�logo��Ȼд���⣬����Ӧ����ûɶ�õģ��޸�logo��setAssoLogo
	public void modAsso(Association asso) throws DbException, BusinessException {
		java.sql.Connection conn = null;
		int assoid = asso.getAssociationId();
		if(asso.getAssociationName() == null || asso.getAssociationName().equals("")) {
			throw new BusinessException("���ֲ���Ϊ��");
		}
		if(asso.getChiefSno() == null || asso.getChiefSno().equals("")) {
			throw new BusinessException("�糤ѧ�Ų���Ϊ��");
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
			throw new BusinessException("���ֲ���Ϊ��");
		}
		if(asso.getChiefSno() == null || asso.getChiefSno().equals("")) {
			throw new BusinessException("�糤ѧ�Ų���Ϊ��");
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
			
			//���糤����������Ա��
			sql = "INSERT into asso_p(sno,associationId,role) VALUES (?,?,'�糤')";
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
	//����ɾ�����ı���ûд
	//ɾ�����ţ�����������Ϣ�Ļ���������������ﱨ������ֱ�������������ɾ����
	//����������صĻ��Щ��ûɾ������߯�鷳�ˡ�ɾ�����ɾ���Ա����QAQ
	public void delAsso(int assoid) throws DbException {
		java.sql.Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			
			//ɾ��������Ա��������Ϣ
			String sql = "delete from asso_p WHERE associationId = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, assoid);
			pst.execute();
			
			//ɾ�����ű��е���Ϣ
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

	//�������е�����
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
	//��������Ա�����ѧ���������Ż��id֮��������������ȡ����
	//��д��Ҳ��֪������û��
	//����logo��Ȼ�з��أ�����ûɶ�ã���Ҳ���������������д����д���ˣ���֪��զ��
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
	//ģ������
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
	//�жϱ�����û��֮�� ������
	//�糤���õ� ��������֮�����糤ֱ�ӽ���Ա����
	public void joinAsso(String sno, int assoid) throws DbException, BusinessException {
		java.sql.Connection conn = null;
		try {
			//�ж���Ա�ڲ����������
			conn = DBUtil.getConnection();
			String sql = "select * from asso_p where sno = ? and associationId = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				throw new BusinessException("�ó�Ա���ڸ���");
			}
			sql = "INSERT into asso_p(sno,associationId,role) VALUES (?,?,'��Ա')";
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
	//Ҳ���糤ֱ�Ӳ���������Ա����
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
	
	//���������룬���ԾͲ�����˰ɣ��糤ֱ����
//	public int checkExitAsso() {
//		// TODO: implement
//		return 0;
//	}
	//���ظ���ĳ�Ա������һ��student��List���糤����Ա�������ģ���Ҫ�糤��Ϣ�Ļ�����assoLeader����
	//ѧ������Ϣֻ����sno,name,sex,tel,affiliated_branch,major,class��Щ�ֶ�
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
	
	//�����糤��Ϣ ֻ��sno,name,sex,tel,affiliated_branch,major,class��Щ�ֶ�
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
//			asso = am.addAsso(asso);//��Ϊ�᷵��assoid ���Ե��õ�ʱ��Ҫ���¸�ֵ
			
//			asso = am.searchAssoById(2);
//			System.out.println(asso.getAssociationName());
			
//			asso = am.addAsso(asso);
//			asso.setAssociationName("test3");
//			System.out.println(asso.getAssociationId());
//			am.modAsso(asso);
			am.delAsso(11);
			
//			am.setAssoLogo(7, "testlogo.jpg"); //��������logo
//			am.getAssoLogo(7, "outputtestlogo.jpg");//ȡ��logo
			
//			List<Association> l = am.assoList(); //������������
//			List<Association> l = am.searchAssoByName("Э��"); //ģ������
//			for(Association a :l) {
//				System.out.println(a.getAssociationName());
//			}
			
//			Student stu = am.assoLeader(2);//���ض�Ӧid�����ŵ��糤
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