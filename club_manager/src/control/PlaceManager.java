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
			throw new BaseException("–ﬁ∏ƒ ß∞‹");
		}

	}

	public void modPstatus(Place p) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE `pla` SET `state`=?  WHERE (`placeId`=?) ";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, p.getStatus());
			pst.setInt(2, p.getPlaceId());
			pst.execute();
		} catch (Exception e) {
			throw new BaseException("–ﬁ∏ƒ ß∞‹");
		}
	}

	public int addPlace(Place p) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql="select max(placeId) from pla";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			int id=1;
			if(rs.next())
				id=rs.getInt(1)+1;
			
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
			throw new BaseException("ÃÌº” ß∞‹");
		}
		return 0;
	}

	public void delPlace(Place p) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql="delete from pla where placeId=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, p.getPlaceId());
			pst.executeQuery();
		} catch (Exception e) {
			throw new BaseException("…æ≥˝ ß∞‹");
		}
		
	}

	public List<Place> placeList() throws BaseException {
		List<Place> result=new ArrayList<Place>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql="select `placeId`, `placeName`, `available`, `state`, `remarks` from pla";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Place p=new Place();
				p.setPlaceId(rs.getInt(1));
				p.setPlaceName(rs.getString(2));
				p.setAvailable(rs.getInt(3));
				p.setStatus(rs.getString(4));
				p.setRemarks(rs.getString(5));
				result.add(p);
			}
		} catch (Exception e) {
			throw new BaseException("º”‘ÿ ß∞‹");
		}
		return result;
	}

}