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
            sql = "INSERT INTO `anno` (`annoId`, `assoId`, `activityId`, `title`, `annoContent`, `createtime`, `annomentType`, `annobrief`, `state`, `remarks`) "
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setInt(2, anno.getAssociationId());
            pst.setInt(3, anno.getActivityId());
            pst.setString(4, anno.gettitle());
            pst.setString(5, anno.getAnnoContent());
            pst.setTimestamp(6, new java.sql.Timestamp(new Date().getTime()));
            pst.setString(7, anno.getAnnoType());
            pst.setString(8, anno.getAnnobrief());
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
                    + "anno.createtime,anno.annobrief FROM anno WHERE anno.annomentType = 'secret' and anno.assoId = ?";
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
                a.setAnnobrief(rs.getString(7));
                result.add(a);
            }
        } catch (Exception e) {
            throw new BaseException("加载失败");
        }

        return result;
    }

    public List<Announcement> publicannoList(String keyword) throws BaseException {
        List<Announcement> result=new ArrayList<Announcement>();
        Connection conn = null;
        if(keyword==null) {
            try {
                conn = this.getConnection();
                String sql = "SELECT anno.annoId,anno.assoId,anno.activityId,anno.title,anno.annoContent,"
                        + "anno.createtime,anno.annobrief,asso.associationName " +
                        "FROM anno ,asso WHERE anno.assoId = asso.associationId AND anno.annomentType = 'public'";
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                java.sql.ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Announcement a = new Announcement();
                    a.setAnnoucementId(rs.getInt(1));
                    a.setAssociationId(rs.getInt(2));
                    a.setActivityId(rs.getInt(3));
                    a.settitle(rs.getString(4));
                    a.setAnnoContent(rs.getString(5));
                    a.setCreatetime(rs.getTimestamp(6));
                    a.setAnnobrief(rs.getString(7));
                    a.setAssociationName(rs.getString(8));
                    result.add(a);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                conn = this.getConnection();
                String sql = "SELECT anno.annoId,anno.assoId,anno.activityId,anno.title,anno.annoContent,"
                        + "anno.createtime,anno.annobrief,asso.associationName " +
                        "FROM anno ,asso WHERE anno.assoId = asso.associationId AND anno.annomentType = 'public' AND asso.associationName like ?";
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, "%" + keyword + "%");
                java.sql.ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Announcement a = new Announcement();
                    a.setAnnoucementId(rs.getInt(1));
                    a.setAssociationId(rs.getInt(2));
                    a.setActivityId(rs.getInt(3));
                    a.settitle(rs.getString(4));
                    a.setAnnoContent(rs.getString(5));
                    a.setCreatetime(rs.getTimestamp(6));
                    a.setAnnobrief(rs.getString(7));
                    a.setAssociationName(rs.getString(8));
                    result.add(a);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public List<Announcement> annoList(int assoid) throws BaseException {
        List<Announcement> result=new ArrayList<Announcement>();
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql = "SELECT anno.annoId,anno.assoId,anno.activityId,anno.title,anno.annoContent,"
                    + "anno.createtime,anno.annobrief,asso.associationName " +
                    "FROM anno ,asso WHERE anno.assoId = asso.associationId and assoId=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,assoid);
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Announcement a=new Announcement();
                a.setAnnoucementId(rs.getInt(1));
                a.setAssociationId(rs.getInt(2));
                a.setActivityId(rs.getInt(3));
                a.settitle(rs.getString(4));
                a.setAnnoContent(rs.getString(5));
                a.setCreatetime(rs.getTimestamp(6));
                a.setAnnobrief(rs.getString(7));
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
                    + "anno.createtime,anno.annobrief,asso.associationName " +
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
                a.setAnnobrief(rs.getString(7));
                a.setAssociationName(rs.getString(8));
                result.add(a);
            }
        } catch (Exception e) {
//			throw new BaseException(e.getMessage());
            throw new BaseException("加载失败");
        }

        return result;
    }

    public Announcement searchAnnoById(int id) throws BaseException {
        Announcement result=new Announcement();
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql = "SELECT annoId,assoId,activityId,title,annoContent,createtime,annobrief,annomentType,state,remarks FROM anno WHERE annoId = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            java.sql.ResultSet rs = pst.executeQuery();
            if(rs.next()){
                result.setAnnoucementId(rs.getInt(1));
                result.setAssociationId(rs.getInt(2));
                result.setActivityId(rs.getInt(3));
                result.settitle(rs.getString(4));
                result.setAnnoContent(rs.getString(5));
                result.setCreatetime(rs.getTime(6));
                result.setAnnobrief(rs.getString(7));
                result.setAnnoType(rs.getString(8));
                result.setStatus(rs.getString(9));
                result.setRemarks(rs.getString(10));
            }
        } catch (Exception e) {
//			throw new BaseException(e.getMessage());
            throw new BaseException("加载失败");
        }
        return result;
    }
}
