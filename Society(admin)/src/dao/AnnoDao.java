package dao;

import entity.Announcement;
import entity.Association;
import exception.BaseException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnnoDao extends BaseDao {

    public int addAnno(Announcement anno) throws BaseException {
        if(anno.gettitle().equals(""))
            throw new BaseException("标题不能为空");
        if(anno.getAnnoContent().equals(""))
            throw new BaseException("内容不能为空");
        //公告类型做成 勾选框   A.公开(public)  B.仅社团内成员可见(secret) 默认选项设置为私密
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql = "select max(annoId) from anno";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            int id = 1;
            if (rs.next())
                id = rs.getInt(1) + 1;
            sql = "INSERT INTO `anno` (`annoId`, `assoId`, `activityId`, `title`, `annoContent`, `createtime`, `annomentType`, `href`, `state`, `remarks`) "
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setInt(2, anno.getAssociationId());
            pst.setInt(3, anno.getActivityId());
            pst.setString(4, anno.gettitle());
            pst.setString(5, anno.getAnnoContent());
            pst.setTimestamp(6, new java.sql.Timestamp(new Date().getTime()));
            pst.setString(7, anno.getAnnoType());
            pst.setString(8, anno.getHref());
            pst.setString(9, anno.getStatus());
            pst.setString(10, anno.getRemarks());
            pst.execute();
        } catch (Exception e) {
//			throw new BaseException(e.getMessage());
            throw new BaseException("创建失败");
        }
        return 0;
    }

    public void delAnno(Announcement anno) throws BaseException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql="delete from anno where annoId=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, anno.getAnnoucementId());
            pst.execute();
        } catch (Exception e) {
            throw new BaseException(e.getMessage());
//			throw new BaseException("删除失败");
        }
    }

    //社团内部的活动公告，如例会通知等
    public List<Announcement> clubannoList(Association asso) throws BaseException {
        List<Announcement> result=new ArrayList<Announcement>();
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql = "SELECT anno.annoId,anno.assoId,anno.activityId,anno.title,anno.annoContent,"
                    + "anno.createtime,anno.href FROM anno WHERE anno.annomentType = 'secret' and anno.assoId = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, asso.getAssociationId());
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Announcement a=new Announcement();
                a.setAnnoucementId(rs.getInt(1));
                a.setAssociationId(rs.getInt(2));
                a.setActivityId(rs.getInt(3));
                a.settitle(rs.getString(4));
                a.setAnnoContent(rs.getString(5));
                a.setCreatetime(rs.getTimestamp(6));
                a.setHref(rs.getString(7));
                result.add(a);
            }
        } catch (Exception e) {
            throw new BaseException("加载失败");
        }

        return result;
    }

    public List<Announcement> publicannoList() throws BaseException {
        List<Announcement> result=new ArrayList<Announcement>();
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql = "SELECT anno.annoId,anno.assoId,anno.activityId,anno.title,anno.annoContent,"
                    + "anno.createtime,anno.href,asso.associationName " +
                    "FROM anno ,asso WHERE anno.assoId = asso.associationId AND anno.annomentType = 'public' ";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Announcement a=new Announcement();
                a.setAnnoucementId(rs.getInt(1));
                a.setAssociationId(rs.getInt(2));
                a.setActivityId(rs.getInt(3));
                a.settitle(rs.getString(4));
                a.setAnnoContent(rs.getString(5));
                a.setCreatetime(rs.getTimestamp(6));
                a.setHref(rs.getString(7));
                a.setAssociationName(rs.getString(8));
                result.add(a);
            }
        } catch (Exception e) {
            throw new BaseException("加载失败");
        }

        return result;
    }

    public List<Announcement> searchAnno(String text) throws BaseException {
        List<Announcement> result=new ArrayList<Announcement>();
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql = "SELECT anno.annoId,anno.assoId,anno.activityId,anno.title,anno.annoContent,"
                    + "anno.createtime,anno.href,asso.associationName " +
                    "FROM anno ,asso WHERE anno.assoId = asso.associationId AND anno.annomentType = 'public' "
                    + "and anno.title like ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%"+text+"%");
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Announcement a=new Announcement();
                a.setAnnoucementId(rs.getInt(1));
                a.setAssociationId(rs.getInt(2));
                a.setActivityId(rs.getInt(3));
                a.settitle(rs.getString(4));
                a.setAnnoContent(rs.getString(5));
                a.setCreatetime(rs.getTime(6));
                a.setHref(rs.getString(7));
                a.setAssociationName(rs.getString(8));
                result.add(a);
            }
        } catch (Exception e) {
//			throw new BaseException(e.getMessage());
            throw new BaseException("加载失败");
        }

        return result;
    }
}
