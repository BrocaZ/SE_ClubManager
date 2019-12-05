package dao;

import entity.Place;
import exception.BaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceDao extends BaseDao {

    public void modPinofo(Place p) throws BaseException {
        Connection conn = null;
        try {
            conn = this.getConnection();
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
            conn = this.getConnection();
            String sql = "UPDATE `pla` SET `state`=?  WHERE `placeId`=? ";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, p.getStatus());
            pst.setInt(2, p.getPlaceId());
            pst.execute();
        } catch (Exception e) {
            throw new BaseException("修改失败");
        }
    }

    public int addPlace(Place p) throws BaseException, SQLException {
        // 新增场地时,默认状态为available
        if (p.getPlaceName().equals(""))
            throw new BaseException("名称不能为空");
        if (p.getAvailable() == 0)
            throw new BaseException("可容纳人数不能为0");
        Connection conn = null;
        conn = this.getConnection();
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
        pst.setString(4, "available");
        pst.setString(5, p.getRemarks());
        pst.execute();
        return 0;
    }

    public void delPlace(Place p) throws SQLException {
        Connection conn = null;
        conn = this.getConnection();
        String sql = "delete from pla where placeId=?";
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, p.getPlaceId());
        pst.execute();
    }

    public List<Place> placeList() throws BaseException {
        List<Place> result = new ArrayList<Place>();
        Connection conn = null;
        try {
            conn = this.getConnection();
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
            conn = this.getConnection();
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

    public Place searchPlaceById(int id) throws BaseException{
        Place p = new Place();
        java.sql.Connection conn = null;
        try {
            conn = this.getConnection();
            String sql = "select placeId,placeName,available,state,remarks from pla where placeId = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            java.sql.ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                p.setPlaceId(rs.getInt(1));
                p.setPlaceName(rs.getString(2));
                p.setAvailable(rs.getInt(3));
                p.setStatus(rs.getString(4));
                p.setRemarks(rs.getString(5));
            }
        } catch (Exception e) {
            throw new BaseException("加载失败");
        }
        return p;
    }

    public int getPlaceByName(String name) throws BaseException {
        Place result = new Place();
        int placeid = -1;
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql = "select placeId from pla where placeName = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,name);
            java.sql.ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                placeid = rs.getInt(1);
            }
        } catch (Exception e) {
            throw new BaseException("加载失败");
        }
        return placeid;
    }
}
