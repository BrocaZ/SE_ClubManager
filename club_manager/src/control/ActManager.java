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
   public static void joinAct(int actId) throws BaseException {//ѧ�������μӻ       ֱ�ӵ�������ı���
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
		  pst.setString(3, "����");
		  pst.execute();
	   }
	   catch(Exception e)
	   {
		   throw new BaseException("����ʧ��");
	   }
	   
   }
   public void modAct(Activity act) throws BaseException {
       // TODO: implement   ���޸Ļ�Ľ����У�����ÿһ�����Ժ�����ʾ������Ϣ���糤��ѡ��Ļ��߲���
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
           pst.setString(9, "������");
           pst.setString(10, act.getRemarks());
           pst.setInt(11, act.getActivityId());
           pst.execute();
       }
       catch(Exception e)
       {
           throw new BaseException("�޸�ʧ��");
       }
   }

   //�糤������ӻ,���޸Ļ��Ϣ����һ��
   public void addAct(Activity act) throws BaseException {
       // TODO: implement   �����ӻ�Ľ��棬ÿ�����Ժ��������Ϊ�գ����糤��һ������
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
//    	   throw new BaseException("����ʧ��");
       }
   }

	public static void delAct(Activity act) throws BaseException {//ֱ�ӵ��������ɾ����ť���� 
		// TODO: implement
		Connection conn=null;
		   try
		   {
			  conn=DBUtil.getConnection();
			  String sql="update act set state=? where activityId=?";
			  PreparedStatement pst=conn.prepareStatement(sql);
			  pst.setString(1, "������ȡ��");
			  pst.setInt(2,act.getActivityId());
			  pst.execute();
		   }
		   catch(Exception e)
		   {
			   throw new BaseException("ɾ��ʧ��");
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