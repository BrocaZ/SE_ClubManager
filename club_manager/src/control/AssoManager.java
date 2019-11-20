package control;

import java.sql.SQLException;
import java.util.*;
import util.DbException;
//import model;

import model.Association;
import util.DBUtil;

public class AssoManager {

	// method that asso need
	//un test
	public void modAsso(Association asso) throws DbException {
		java.sql.Connection conn = null;
		int assoid = asso.getAssociationId();
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
	
	public Association addAsso(Association asso) throws util.DbException {
		java.sql.Connection conn = null;
		Association res = null;
		int assoid = 0;
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
		return res;
	}

	public void delAsso(int assoid) throws util.DbException {
		java.sql.Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE asso set remarks = 'delete' WHERE associationId = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
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

	//返回所有的社团，remarks没标记为删除的社团
	public List<Association> assoList() throws util.DbException {
		List<Association> res = new ArrayList<Association>();
		java.sql.Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select AssociationId,placeId,associationName,logo,chiefSno,brief_introduction,state,remarks from asso where remarks != 'delete' or remarks is null";
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
		return res;
	}
	
	//untest
	//在社团人员表查找学生所在社团获得id之后调用这个方法获取社团
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
	//模糊查找 但是没筛软删除掉的社团
	public Association searchAssoByName(String name) throws DbException {
		Association a = new Association();
		java.sql.Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select AssociationId,placeId,associationName,logo,chiefSno,brief_introduction,state,remarks from asso where associationName LIKE ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "%"+name+"%");
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

	// method need student and asso
	public int joinAsso() {
		// TODO: implement
		return 0;
	}

	public int exitAsso() {
		// TODO: implement
		return 0;
	}

	public int assoMemberList() {
		// TODO: implement
		return 0;
	}

	public int checkExitAsso() {
		// TODO: implement
		return 0;
	}
	public static void main(String[] args) {
		AssoManager am = new AssoManager();
		Association asso = new Association();
		asso.setAssociationId(10);
		asso.setAssociationName("test");
		asso.setPlacId(1);
//		am.addAsso(asso);
		try {
			am.delAsso(7);
			List<Association> l = am.assoList();
			for(Association a :l) {
				System.out.println(a.getAssociationName());
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ok");
	}
}