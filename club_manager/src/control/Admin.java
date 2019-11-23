package control;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import model.Activity;
import model.Association;
import model.Place;
import util.BaseException;
import util.DBUtil;

public class Admin {
   public static void checkAddAct(Activity act) throws BaseException {//����Ա��������ť   �����ǹ���Ա�յ��ʼ����������ʾ��Ϣ��Ȼ����һ��������ť
      // TODO: implement
	   Connection conn=null;
	   try
	   {
		  
		   conn=DBUtil.getConnection();
		   String sql="select state from act where activityId=?";
		   PreparedStatement pst=conn.prepareStatement(sql);
		   pst.setInt(1, act.getActivityId());
		   ResultSet rs=pst.executeQuery();
		   String state=null;
		   while(rs.next())
		   {
			   state=rs.getString(1);
		   }
		   if(state.compareTo("������")==0)
		   {
		   sql="update act set state=? where activityId=?";
		   pst=conn.prepareStatement(sql);
		   pst.setString(1, "���ٰ�");
		   pst.setInt(2,act.getActivityId());
		   pst.execute();
		   Place p=new Place();
		   p.setPlaceId(act.getPalceId());
		   p.setStatus("������");
		   PlaceManager pm=new PlaceManager();
		   pm.modPstatus(p);
		   }
	   }
	   catch(Exception e)
	   {
		   throw new BaseException("����ʧ��");
	   }
   }
   
//   public void checkModAct(Activity act) {//��������������ӻ����һ��һ��
//      // TODO: implement
//	   Connection conn=null;
//	   try
//	   {
//		   conn=DBUtil.getConnection();
//		   String sql="select state from pla where placeId=?";
//		   PreparedStatement pst=conn.prepareStatement(sql);
//		   pst.setInt(1, act.getPalceId());
//		   String state = null;
//		   ResultSet rs=pst.executeQuery();
//		   while(rs.next())
//		   {
//			   state=rs.getString(1);
//		   }
//		   if(state.compareTo("����")==0)
//		   {
//		   sql="update act set state=? where activityId=?";
//		   pst=conn.prepareStatement(sql);
//		   pst.setString(1, "���ٰ�");
//		   pst.setInt(2,act.getActivityId());
//		   pst.execute();
//		   sql="update pla set state=? where placeId=?";
//		   pst=conn.prepareStatement(sql);
//		   pst.setString(1, "������");
//		   pst.setInt(2,act.getPalceId());
//		   pst.execute();
//		   }
//		   else
//		   {
//			   sql="update act set state=? where activityId=?";
//			   pst=conn.prepareStatement(sql);
//			   pst.setString(1, "������ͨ��");
//			   pst.setInt(2,act.getActivityId());
//			   pst.execute();
//			   throw new BaseException("�ó����ѱ�ռ��");
//		   }
//	   }
//	   catch(Exception e)
//	   {
//		   e.printStackTrace();
//	   }
//   }
   
   public static void checkDelAct(Activity act) throws BaseException {//����Ա��������ť   �����ǹ���Ա�յ��ʼ����������ʾ��Ϣ��Ȼ����һ��������ť
      // TODO: implement
	   Connection conn=null;
	   try
	   {
		  conn=DBUtil.getConnection();
		  String sql="delete from act where activityId=?";
		  PreparedStatement pst=conn.prepareStatement(sql);
		  pst.setInt(1,act.getActivityId());
		  pst.execute();
	   }
	   catch(Exception e)
	   {
		   throw new BaseException("����ʧ��");
	   }
   }
   
   public static void checkAddAsso(Association asso) throws BaseException {//����Ա��������ť   �����ǹ���Ա�յ��ʼ����������ʾ��Ϣ��Ȼ����һ��������ť
      // TODO: implement
	   Connection conn=null;
	   try
	   {
		   conn=DBUtil.getConnection();
		   String sql="update asso set state=? where associationId=?";
		   PreparedStatement pst=conn.prepareStatement(sql);
		   pst.setString(1, "�ѳ���");
		   pst.setInt(2,asso.getAssociationId());
		   pst.execute();
		   Place p=new Place();
		   p.setPlaceId(asso.getPlacId());
		   p.setStatus("������");
		   PlaceManager pm=new PlaceManager();
		   pm.modPstatus(p);
	   }
	   catch(Exception e)
	   {
		   throw new BaseException("����ʧ��");
	   }
   }
   
//   public void checkModAsso(Association asso) {
//      // TODO: implement
//	   Connection conn=null;
//	   try
//	   {
//		   conn=DBUtil.getConnection();
//		   String sql="select state from pla where placeId=?";
//		   PreparedStatement pst=conn.prepareStatement(sql);
//		   pst.setInt(1, asso.getPlacId());
//		   String state = null;
//		   ResultSet rs=pst.executeQuery();
//		   while(rs.next())
//		   {
//			   state=rs.getString(1);
//		   }
//		   if(state.compareTo("����")==0)
//		   {
//		   sql="update asso set state=? where associationId=?";
//		   pst=conn.prepareStatement(sql);
//		   pst.setString(1, "�ѳ���");
//		   pst.setInt(2,asso.getAssociationId());
//		   pst.execute();
//		   sql="update pla set state=? where placeId=?";
//		   pst=conn.prepareStatement(sql);
//		   pst.setString(1, "������");
//		   pst.setInt(2,asso.getPlacId());
//		   pst.execute();
//		   }
//		   else
//		   {
//			   sql="update asso set state=? where associationId=?";
//			   pst=conn.prepareStatement(sql);
//			   pst.setString(1, "������ͨ��");
//			   pst.setInt(2,asso.getAssociationId());
//			   pst.execute();
//			   throw new BaseException("�ó����ѱ�ռ��");
//		   }
//	   }
//	   catch(Exception e)
//	   {
//		   e.printStackTrace();
//	   }
//   }
   
   public static void checkDelAsso(Association asso) {//����Ա��������ť   �����ǹ���Ա�յ��ʼ����������ʾ��Ϣ��Ȼ����һ��������ť
      // TODO: implement
	   Connection conn=null;
	   try
	   {
		  conn=DBUtil.getConnection();
		  String sql="delete from  asso where associationId=?";
		  PreparedStatement pst=conn.prepareStatement(sql);
		  pst.setInt(1,asso.getAssociationId());
		  pst.execute();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
   }
   
   public static void modLeader(String leaderSno,Association asso) {//�޸��糤    ѡ���糤��ʱ�򣬿ɲ������������ʽ��ѡ����Ա
      // TODO: implement
	   Connection conn=null;
	   try
	   {
		  conn=DBUtil.getConnection();
		  String sql="update asso_p set state=? where sno=? and associationId=?";
		  PreparedStatement pst=conn.prepareStatement(sql);
		  pst.setString(1, "��ְ");
		  pst.setString(2,asso.getChiefSno());
		  pst.setInt(3,asso.getAssociationId());
		  pst.execute();
		  
		  sql="update asso_p set role=? where sno=? and associationId=?";
		  pst=conn.prepareStatement(sql);
		  pst.setString(1, "�糤");
		  pst.setString(2,leaderSno);
		  pst.setInt(3,asso.getAssociationId());
		  pst.execute();
		  
		  sql="update asso set chiefSno=? where associationId=?";
		  pst=conn.prepareStatement(sql);
		  pst.setString(1, leaderSno);
		  pst.setInt(2,asso.getAssociationId());
		  pst.execute();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
   }
   
}