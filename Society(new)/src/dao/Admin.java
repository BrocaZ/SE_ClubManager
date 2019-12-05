package dao;

import entity.Activity;
import entity.Association;
import entity.Place;
import exception.BaseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Admin extends BaseDao {

    public void sendMessage(String sendsno,String recsno,String content){
        Connection conn=null;
        int mesid = 0;
        try {
            conn = this.getConnection();
            String sql = "SELECT MAX(mesid) from message";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                mesid = rs.getInt(1)+1;
            }

            sql = "INSERT into message(mesId,sendsno,recsno,content,senddate) VALUES(?,?,?,?,NOW())";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,mesid);
            pst.setString(2,sendsno);
            pst.setString(3,recsno);
            pst.setString(4,content);
            pst.execute();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //管理员按审批按钮,可以是管理员收到邮件，点击后显示信息，然后有一个审批按钮
    public void checkAddAct(Activity act,boolean accept) throws BaseException {
        // TODO: implement
        Connection conn=null;
        try
        {
            conn=this.getConnection();
            if(accept){
                String sql="UPDATE act set state = 'ok' where activityId = ?";
                PreparedStatement pst=conn.prepareStatement(sql);
//                pst.setString(1, "待举办");
                pst.setInt(1,act.getActivityId());
                pst.execute();
                Place p=new Place();
                p.setPlaceId(act.getPalceId());
                p.setStatus("unavailable");
                PlaceDao pm=new PlaceDao();
                pm.modPstatus(p);
            }else{
                String sql="DELETE from act where activityId = ?";
                PreparedStatement pst=conn.prepareStatement(sql);
//                pst.setString(1, "待举办");
                pst.setInt(1,act.getActivityId());
                pst.execute();
            }
            String str = "未通过";
            if(accept) str = "已通过";
            sendMessage("admin",act.getLeaderSno(),"添加"+act.getActtheme()+"的审核"+str);
        }
        catch(Exception e)
        {
            throw new BaseException("审批失败");
        }
    }
    public void checkModAct(Activity act,boolean accept) throws BaseException {
        // TODO: implement
        Connection conn=null;
        try
        {
            conn=this.getConnection();
            if(accept){
                int id = Integer.parseInt(act.getStatus());
                String sql="UPDATE act set placeId = ?,acttheme = ?,activityContent = ?,leaderSno = ?,start_time = ?,end_time = ?,attend_number = ? where activityId = ?";
                PreparedStatement pst=conn.prepareStatement(sql);
                pst.setInt(1,act.getPalceId());
                pst.setString(2,act.getActtheme());
                pst.setString(3,act.getActivityContent());
                pst.setString(4,act.getLeaderSno());
                pst.setTimestamp(5,new java.sql.Timestamp(act.getStartTime().getTime()));
                pst.setTimestamp(6,new java.sql.Timestamp(act.getStartTime().getTime()));
                pst.setInt(7,act.getAttendNumber());
                pst.setInt(8,id);
                pst.execute();

                sql="DELETE from act where activityId = ?";
                pst=conn.prepareStatement(sql);
                pst.setInt(1,act.getActivityId());
                pst.execute();
            }else{
                String sql="DELETE from act where activityId = ?";
                PreparedStatement pst=conn.prepareStatement(sql);
                pst.setInt(1,act.getActivityId());
                pst.execute();
            }
            String str = "未通过";
            if(accept) str = "已通过";
            sendMessage("admin",act.getLeaderSno(),"修改"+act.getActtheme()+"的审核"+str);
        }
        catch(Exception e)
        {
            throw new BaseException("审批失败");
        }
    }

    //管理员按审批按钮，可以是管理员收到邮件，点击后显示信息，然后有一个审批按钮
    public void checkDelAct(Activity act,boolean accept) throws BaseException {
        Connection conn=null;
        try
        {
            conn=this.getConnection();
            if(!accept){
                String sql="UPDATE act set state = 'ok' where activityId = ?";
                PreparedStatement pst=conn.prepareStatement(sql);
//                pst.setString(1, "待举办");
                pst.setInt(1,act.getActivityId());
                pst.execute();
            }else{
                String sql="DELETE from act where activityId = ?";
                PreparedStatement pst=conn.prepareStatement(sql);
//                pst.setString(1, "待举办");
                pst.setInt(1,act.getActivityId());
                pst.execute();

                Place p=new Place();
                p.setPlaceId(act.getPalceId());
                p.setStatus("available");
                PlaceDao pm=new PlaceDao();
                pm.modPstatus(p);
            }
            String str = "未通过";
            if(accept) str = "已通过";
            sendMessage("admin",act.getLeaderSno(),"删除"+act.getActtheme()+"的审核"+str);
        }
        catch(Exception e)
        {
            throw new BaseException("审批失败");
        }
    }

    //管理员按审批按钮，可以是管理员收到邮件，点击后显示信息，然后有一个审批按钮
//    public void checkAddAsso(Association asso) throws BaseException {
//        // TODO: implement
//        Connection conn=null;
//        try
//        {
//            conn=this.getConnection();
//            String sql="update asso set state=? where associationId=?";
//            PreparedStatement pst=conn.prepareStatement(sql);
//            pst.setString(1, "已成立");
//            pst.setInt(2,asso.getAssociationId());
//            pst.execute();
//            Place p=new Place();
//            p.setPlaceId(asso.getPlacId());
//            p.setStatus("不可用");
//            PlaceDao pm=new PlaceDao();
//            pm.modPstatus(p);
//        }
//        catch(Exception e)
//        {
//            throw new BaseException("审批失败");
//        }
//    }

   public void checkModAsso(Association asso,boolean accept) throws BaseException {
       Connection conn=null;
       try
       {
           conn=this.getConnection();
           if(accept){
               int id = Integer.parseInt(asso.getStatus());
               String sql="UPDATE asso set placeId = ?,associationName = ?,chiefSno = ?,brief_introduction = ? where associationId = ?";
               PreparedStatement pst=conn.prepareStatement(sql);
               pst.setInt(1,asso.getPlacId());
               pst.setString(2,asso.getAssociationName());
               pst.setString(3,asso.getChiefSno());
               pst.setString(4,asso.getIntro());
               pst.setInt(5,id);
               pst.execute();

               sql="DELETE from asso where associationId = ?";
               pst=conn.prepareStatement(sql);
               pst.setInt(1,asso.getAssociationId());
               pst.execute();
           }else{
               String sql="DELETE from asso where associationId = ?";
               PreparedStatement pst=conn.prepareStatement(sql);
               pst.setInt(1,asso.getAssociationId());
               pst.execute();
           }
           String str = "未通过";
           if(accept) str = "已通过";
           sendMessage("admin",asso.getChiefSno(),asso.getAssociationName()+"信息修改审核"+str);
       }
       catch(Exception e)
       {
           throw new BaseException("审批失败");
       }
   }
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
//		   if(state.compareTo("可用")==0)
//		   {
//		   sql="update asso set state=? where associationId=?";
//		   pst=conn.prepareStatement(sql);
//		   pst.setString(1, "已成立");
//		   pst.setInt(2,asso.getAssociationId());
//		   pst.execute();
//		   sql="update pla set state=? where placeId=?";
//		   pst=conn.prepareStatement(sql);
//		   pst.setString(1, "不可用");
//		   pst.setInt(2,asso.getPlacId());
//		   pst.execute();
//		   }
//		   else
//		   {
//			   sql="update asso set state=? where associationId=?";
//			   pst=conn.prepareStatement(sql);
//			   pst.setString(1, "审批不通过");
//			   pst.setInt(2,asso.getAssociationId());
//			   pst.execute();
//			   throw new BaseException("该场地已被占用");
//		   }
//	   }
//	   catch(Exception e)
//	   {
//		   e.printStackTrace();
//	   }
//   }

    //管理员按审批按钮，可以是管理员收到邮件，点击后显示信息，然后有一个审批按钮
//    public void checkDelAsso(Association asso) {
//        // TODO: implement
//        Connection conn=null;
//        try
//        {
//            conn=this.getConnection();
//            String sql="delete from  asso where associationId=?";
//            PreparedStatement pst=conn.prepareStatement(sql);
//            pst.setInt(1,asso.getAssociationId());
//            pst.execute();
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//    }

    public List<Activity> listActCheck() {
        List<Activity> result = new ArrayList<>();
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql = "select activityId,placeId,associationId,acttheme,activityContent,leaderSno,start_time,end_time,attend_number,state,remarks from act where state != 'ok'";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
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
                result.add(a);
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

    public List<Association> listAssoApprove() {
        List<Association> res = new ArrayList<Association>();
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql = "select AssociationId,placeId,associationName,logo,chiefSno,brief_introduction,state,remarks from asso where state != 'ok'";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
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
        return res;
    }

//    public static void main(String[] args) {
//        Admin ad = new Admin();
//        List<Activity> l = ad.listActCheck();
//        for ( Activity a : l){
//            System.out.println(a.getActivityId());
//        }
//    }
}
