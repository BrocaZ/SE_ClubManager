package dao;
import entity.Activity;
import entity.Student;
import exception.BaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ActDao extends BaseDao {

    //学生报名参加活动，直接点击活动后面的报名
    public void joinAct(int actId) throws BaseException {
        // TODO: implement
        Connection conn=null;
        Student stu=new Student();
        StuDao stuDao=new StuDao();
        try
        {
            conn=this.getConnection();
            String sql="insert into act_p(sno,activityId,state) values(?,?,?)";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,stuDao.getCurID());
            pst.setInt(2, actId);
            pst.setString(3, "报名");
            pst.execute();
        }
        catch(Exception e)
        {
            throw new BaseException("报名失败");
        }

    }

    //修改社团信息，社长在社团活动后面点击修改信息，地点采用下拉框选择名称的形式，活动内容，负责人学号，可参加人数等等采用输入的形式，开始时间，结束时间采用时间选择的形式
    public void modAct(Activity act) throws BaseException {
        // TODO: implement   在修改活动的界面中，会在每一个属性后面显示已有信息，社长可选择改或者不改
        Connection conn=null;
        Student stu=new Student();
        try
        {
            //获取社团id
            conn=this.getConnection();
            String sql="select associationId from asso where chiefSno=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, stu.getCurStu().getSno());
            ResultSet rs=pst.executeQuery();
            int associationId=0;
            while(rs.next())
            {
                associationId=rs.getInt(1);
            }
            //获取当前actid
            sql="select max(activityId) from act";
            Statement st=conn.createStatement();
            rs=st.executeQuery(sql);
            int activityId=0;
            while(rs.next())
            {
                activityId=rs.getInt(1)+1;
            }
            //获取插入新的
            sql="insert into act(activityId,placeId,associationId,activityContent,leaderSno,start_time,end_time,attend_number,acttheme,state,remarks) values(?,?,?,?,?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,activityId);
            pst.setInt(2, act.getPalceId());
            pst.setInt(3, associationId);
            pst.setString(4, act.getActivityContent());
            pst.setString(5,act.getLeaderSno());
            pst.setTimestamp(6, new java.sql.Timestamp(act.getStartTime().getTime()));
            pst.setTimestamp(7, new java.sql.Timestamp(act.getEndTime().getTime()));
            pst.setInt(8, act.getAttendNumber());
            pst.setString(9, act.getActtheme());
            pst.setString(10,act.getActivityId()+"");
            pst.setString(11, act.getRemarks());
            pst.execute();
            //更新原记录的state
            sql="update act set state = ? where activityId = ?";
            pst=conn.prepareStatement(sql);
            pst.setString(1,act.getActivityId()+"");
            pst.setInt(2,act.getActivityId());
            pst.execute();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new BaseException("修改失败");
        }
    }

    //社长点击增加活动,与修改活动信息界面一致
    public void addAct(Activity act) throws BaseException {
        // TODO: implement   在增加活动的界面，每个属性后面的内容为空，需社长第一次输入
        Connection conn=null;
        try
        {
            conn=this.getConnection();
            String sql="select associationId from asso where chiefSno=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            StuDao stu=new StuDao();
            pst.setString(1, stu.getCurID());
            ResultSet rs=pst.executeQuery();
            int associationId=0;
            while(rs.next())
            {
                associationId=rs.getInt(1);
            }
            sql="select max(activityId) from act";
            Statement st=conn.createStatement();
            rs=st.executeQuery(sql);
            int activityId=0;
            while(rs.next())
            {
                activityId=rs.getInt(1);
            }
            activityId++;
            sql="insert into act(activityId,placeId,associationId,activityContent,leaderSno,start_time,end_time,attend_number,acttheme,state,remarks) values(?,?,?,?,?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,activityId);
            pst.setInt(2, act.getPalceId());
            pst.setInt(3, associationId);
            pst.setString(4, act.getActivityContent());
            pst.setString(5,act.getLeaderSno());
            pst.setTimestamp(6, new java.sql.Timestamp(act.getStartTime().getTime()));
            pst.setTimestamp(7, new java.sql.Timestamp(act.getEndTime().getTime()));
            pst.setInt(8, act.getAttendNumber());
            pst.setString(9, act.getActtheme());
            pst.setString(10,act.getActivityId()+"");
            pst.setString(11, act.getRemarks());
            pst.execute();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new BaseException("增加失败");
        }
    }

    //直接点击活动后面的删除按钮即可
    public void delAct(int actid) throws BaseException {
        // TODO: implement
        Connection conn=null;
        try
        {
            conn=this.getConnection();
            String sql="update act set state=? where activityId=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, "del");
            pst.setInt(2,actid);
            pst.execute();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new BaseException("删除失败");
        }
    }
    public  Activity getActById(int actid){
        Connection conn=null;
        Activity a = new Activity();
        try
        {
            conn=this.getConnection();
            String sql = "select activityId,placeId,associationId,acttheme,activityContent,leaderSno,start_time,end_time,attend_number,state,remarks from act where activityId = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,actid);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                a.setActivityId(rs.getInt(1));
                a.setPalceId(rs.getInt(2));
                a.setAssociationId(rs.getInt(3));
                a.setActtheme(rs.getString(4));
                a.setActivityContent(rs.getString(5));
                a.setLeaderSno(rs.getString(6));
                a.setStartTime(new java.util.Date(rs.getTimestamp(7).getTime()));
                a.setEndTime(new java.util.Date(rs.getTimestamp(8).getTime()));
                a.setAttendNumber(rs.getInt(9));
                a.setStatus(rs.getString(10));
                a.setRemarks(rs.getString(11));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return  a;
    }

    public List<Activity> listActInAsso() {
        List<Activity> result = new ArrayList<>();
        Connection conn = null;
        StuDao stuDao=new StuDao();
        try {
            //获取社团id
            Student stu = new Student();
            conn=this.getConnection();
            String sql="select associationId from asso where chiefSno=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, stuDao.getCurID());
            ResultSet rs=pst.executeQuery();
            int associationId=0;
            while(rs.next())
            {
                associationId=rs.getInt(1);
            }
            sql = "select activityId,placeId,associationId,acttheme,activityContent,leaderSno,start_time,end_time,attend_number,state,remarks from act where associationId = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,associationId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Activity a = new Activity();
                a.setActivityId(rs.getInt(1));
                a.setPalceId(rs.getInt(2));
                a.setAssociationId(rs.getInt(3));
                a.setActtheme(rs.getString(4));
                a.setActivityContent(rs.getString(5));
                a.setLeaderSno(rs.getString(6));
                a.setStartTime(new java.util.Date(rs.getTimestamp(7).getTime()));
                a.setEndTime(new java.util.Date(rs.getTimestamp(8).getTime()));
                a.setAttendNumber(rs.getInt(9));
                a.setStatus(rs.getString(10));
                a.setRemarks(rs.getString(11));
                String str = a.getStatus();
                if(str.equals("ok") || str.equals(""+a.getActivityId()) || str.equals("del")){
                    result.add(a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
//			throw new util.DbException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
//    public static void main(String[] args) {
//        Student stu = new Student();
//        stu.setSno("31701005");
//        Student.curStu = stu;
//        ActDao ad = new ActDao();

//        Activity act = new Activity();
//        act.setActivityId(8);
//        act.setPalceId(1);
//        act.setActtheme("test1");
//        act.setActivityContent("ddasdfsasadf");
//        act.setStartTime(new java.util.Date(System.currentTimeMillis()));
//        act.setEndTime(new java.util.Date(System.currentTimeMillis()));
//        try {
//            ad.addAct(act);
//            ad.modAct(act);
//            ad.delAct(act);
//        } catch (BaseException e) {
//            e.printStackTrace();
//        }
//        List<Activity> l = ad.listActInAsso();
//        for (Activity a : l){
//            System.out.println(a.getActtheme());
//        }
//
//        System.out.println(ad.getActById(1).getActivityContent());
//    }

}
