package control;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import model.Activity;
import model.Student;
import util.BaseException;
import util.DBUtil;

public class ActManager {
   public static void joinAct(int actId) throws BaseException {//学生报名参加活动       直接点击活动后面的报名
      // TODO: implement
	   Connection conn=null;
	   Student stu=new Student();
	   try
	   {
		  conn=DBUtil.getConnection();
		  String sql="insert into act_p(sno,activityId,state) values(?,?,?)";
		  PreparedStatement pst=conn.prepareStatement(sql);
		  pst.setString(1,stu.getCurStu().getSno());
		  pst.setInt(2, actId);
		  pst.setString(3, "报名");
		  pst.execute();
	   }
	   catch(Exception e)
	   {
		   throw new BaseException("报名失败");
	   }
	   
   }
   public void modAct(Activity act) throws BaseException {
       // TODO: implement   在修改活动的界面中，会在每一个属性后面显示已有信息，社长可选择改或者不改
       Connection conn=null;
       Student stu=new Student();
       try
       {
           conn=DBUtil.getConnection();
           String sql="select associationId from asso where chiefSno=?";
           PreparedStatement pst=conn.prepareStatement(sql);
           pst.setString(1, stu.getCurStu().getSno());
           ResultSet rs=pst.executeQuery();
           int associationId=0;
           while(rs.next())
           {
               associationId=rs.getInt(1);
           }
           sql="update act set placeId=?,associationId=?,activityTheme=?,activityContent=?,leaderSno=?,start_time=?,end_time=?,attend_number=?,state=?,remarks=? where activityId=?";
           pst=conn.prepareStatement(sql);
           pst.setInt(1, act.getPalceId());
           pst.setInt(2,associationId);
           pst.setString(3, act.getactivityTheme());
           pst.setString(4, act.getActivityContent());
           pst.setString(5, act.getLeaderSno());
           pst.setTimestamp(6, new java.sql.Timestamp(act.getStartTime().getTime()));
           pst.setTimestamp(7, new java.sql.Timestamp(act.getEndTime().getTime()));
           pst.setInt(8, act.getAttendNumber());
           pst.setString(9, "待审批");
           pst.setString(10, act.getRemarks());
           pst.setInt(11, act.getActivityId());
           pst.execute();
       }
       catch(Exception e)
       {
           throw new BaseException("修改失败");
       }
   }

   //社长点击增加活动,与修改活动信息界面一致
   public void addAct(Activity act) throws BaseException {
       // TODO: implement   在增加活动的界面，每个属性后面的内容为空，需社长第一次输入
       Connection conn=null;
       Student stu=new Student();
       try
       {
           conn=DBUtil.getConnection();
           String sql="select associationId from asso where chiefSno=?";
           PreparedStatement pst=conn.prepareStatement(sql);
           pst.setString(1, "31701004");
           ResultSet rs=pst.executeQuery();
           int associationId=0;
          
           while(rs.next())
           {
               associationId=rs.getInt(1);
           } 
           System.out.println(associationId);
           sql="select max(activityId) from act";
           Statement st=conn.createStatement();
           rs=st.executeQuery(sql);
           int activityId=0;
           while(rs.next())
           {
               activityId=rs.getInt(1);
           }
           activityId++;
           sql="insert into act values(?,?,?,?,?,?,?,?,?,?,?)";
           System.out.println(activityId);
           pst=conn.prepareStatement(sql);
           pst.setInt(1,activityId);
           pst.setInt(2, act.getPalceId());
           pst.setInt(3, associationId);
           pst.setString(4, act.getactivityTheme());
           pst.setString(5, act.getActivityContent());
           pst.setString(6,act.getLeaderSno());
           pst.setTimestamp(7, new java.sql.Timestamp(act.getStartTime().getTime()));
           pst.setTimestamp(8, new java.sql.Timestamp(act.getEndTime().getTime()));
           pst.setInt(9, act.getAttendNumber());
           pst.setDouble(10, activityId);
           pst.setString(11, act.getRemarks());
           pst.execute();
       }
       catch(Exception e)
       {
    	   throw new BaseException(e.getMessage());
//    	   throw new BaseException("增加失败");
       }
   }

	public static void delAct(Activity act) throws BaseException {//直接点击活动后面的删除按钮即可 
		// TODO: implement
		Connection conn=null;
		   try
		   {
			  conn=DBUtil.getConnection();
			  String sql="update act set state=? where activityId=?";
			  PreparedStatement pst=conn.prepareStatement(sql);
			  pst.setString(1, "待审批取消");
			  pst.setInt(2,act.getActivityId());
			  pst.execute();
		   }
		   catch(Exception e)
		   {
			   throw new BaseException("删除失败");
		   }
	}

	public static void main(String[] args) throws BaseException {
		ActManager am=new ActManager();
		Activity act = new Activity();
        act.setPalceId(1);
        act.setactivityTheme("a");
        act.setActivityContent("b");
        act.setLeaderSno("31701004");
        act.setStartTime(new Date());
        act.setEndTime(new Date());
        act.setAttendNumber(30);
        act.setRemarks("2019-12-1");
        am.addAct(act);
	}
}