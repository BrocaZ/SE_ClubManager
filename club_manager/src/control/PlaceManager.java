package control;

import java.sql.Connection;
import java.util.*;

import model.Announcement;
import model.Place;
import util.BaseException;
import util.DBUtil;

public class PlaceManager {

	public void modPinofo(Place p) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE `pla` SET `placeName`=?, `available`=?, `state`=?, `remarks`=? WHERE (`placeId`=?) ";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, p.getPlaceName());
			pst.setInt(2, p.getAvailable());
			pst.setString(3, p.getStatus());
			pst.setString(4, p.getRemarks());
			pst.setInt(5, p.getPlaceId());
			pst.execute();
		} catch (Exception e) {
			throw new BaseException("修改失败");
		}

	}

	public void modPstatus(Place p) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE `pla` SET `state`=?  WHERE `placeId`=? ";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, p.getStatus());
			pst.setInt(2, p.getPlaceId());
			pst.execute();
		} catch (Exception e) {
			throw new BaseException("修改失败");
		}
	}

	public int addPlace(Place p) throws BaseException {
		// 新增场地时,默认状态为available
		if (p.getPlaceName().equals(""))
			throw new BaseException("名称不能为空");
		if (p.getAvailable() == 0)
			throw new BaseException("可容纳人数不能为0");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select max(placeId) from pla";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			int id = 1;
			if (rs.next())
				id = rs.getInt(1) + 1;

			sql = "select * from pla where placeName=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, p.getPlaceName());
			rs = pst.executeQuery();
			if(rs.next())
				throw new BaseException("该场地已存在");

			sql = "INSERT INTO `se_clubmanager`.`pla` (`placeId`, `placeName`, `available`, `state`, `remarks`) "
					+ "VALUES (?, ?, ?, ?, ?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, p.getPlaceName());
			pst.setInt(3, p.getAvailable());
			pst.setString(4, p.getStatus());
			pst.setString(5, p.getRemarks());
			pst.execute();
		} catch (Exception e) {
			throw new BaseException("添加失败");
		}
		return 0;
	}

	public void delPlace(Place p) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from pla where placeId=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, p.getPlaceId());
			pst.execute();
		} catch (Exception e) {
			throw new BaseException("删除失败");
		}

	}

	public List<Place> placeList() throws BaseException {
		List<Place> result = new ArrayList<Place>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select `placeId`, `placeName`, `available`, `state`, `remarks` from pla";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Place p = new Place();
				p.setPlaceId(rs.getInt(1));
				p.setPlaceName(rs.getString(2));
				p.setAvailable(rs.getInt(3));
				p.setStatus(rs.getString(4));
				p.setRemarks(rs.getString(5));
				result.add(p);
			}
		} catch (Exception e) {
			throw new BaseException("加载失败");
		}
		return result;
	}
	
	public List<Place> avaplaceList() throws BaseException {
		List<Place> result = new ArrayList<Place>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select `placeId`, `placeName`, `available`, `state`, `remarks` from pla where `state` =?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,"available" );
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Place p = new Place();
				p.setPlaceId(rs.getInt(1));
				p.setPlaceName(rs.getString(2));
				p.setAvailable(rs.getInt(3));
				p.setStatus(rs.getString(4));
				p.setRemarks(rs.getString(5));
				result.add(p);
			}
		} catch (Exception e) {
			throw new BaseException("加载失败");
		}
		return result;
	}

	public static void main(String[] args) throws BaseException {
		PlaceManager pm = new PlaceManager();
		Place p = null;
		List<Place> result =null;

//		System.out.println("Test1");
//		p =new Place();
//		p.setPlaceId(1);
//		p.setPlaceName("文一312");
//		p.setAvailable(500);
//		p.setStatus("available");
//		pm.modPinofo(p);

//		System.out.println("Test2");
//		p =new Place();
//		p.setPlaceId(1);
//		p.setStatus("unavailable");
//		pm.modPstatus(p);

//		System.out.println("Test3");
//		p = new Place();
//		p.setPlaceName("理四222");
//		p.setAvailable(100);
//		p.setStatus("available");
//		pm.addPlace(p);

//		System.out.println("Test4");
//		p = new Place();
//		p.setPlaceId(10);
//		pm.delPlace(p);
		
//		System.out.println("Test5");
//		result = pm.placeList();
//		for(int i=0;i<result.size();i++) {
//			p=result.get(i);
//			System.out.println(p.getPlaceName());
//		}
		
//		System.out.println("Test6");
//		result = pm.avaplaceList();
//		for(int i=0;i<result.size();i++) {
//			p=result.get(i);
//			System.out.println(p.getPlaceName());
//		}
		
	}

}