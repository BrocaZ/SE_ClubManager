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

    //管理员按审批按钮,可以是管理员收到邮件，点击后显示信息，然后有一个审批按钮
    public void checkAddAct(Activity act) throws BaseException {
        // TODO: implement
        Connection conn=null;
        try
        {

            conn=this.getConnection();
            String sql="select state from act where activityId=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1, act.getActivityId());
            ResultSet rs=pst.executeQuery();
            String state=null;
            while(rs.next())
            {
                state=rs.getString(1);
            }
            if(state.compareTo("待审批")==0)
            {
                sql="update act set state=? where activityId=?";
                pst=conn.prepareStatement(sql);
                pst.setString(1, "待举办");
                pst.setInt(2,act.getActivityId());
                pst.execute();
                Place p=new Place();
                p.setPlaceId(act.getPalceId());
                p.setStatus("不可用");
                PlaceDao pm=new PlaceDao();
                pm.modPstatus(p);
            }
        }
        catch(Exception e)
        {
            throw new BaseException("审批失败");
        }
    }

//   public void checkModAct(Activity act) {//与上面的审批增加活动函数一摸一样
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
//		   if(state.compareTo("可用")==0)
//		   {
//		   sql="update act set state=? where activityId=?";
//		   pst=conn.prepareStatement(sql);
//		   pst.setString(1, "待举办");
//		   pst.setInt(2,act.getActivityId());
//		   pst.execute();
//		   sql="update pla set state=? where placeId=?";
//		   pst=conn.prepareStatement(sql);
//		   pst.setString(1, "不可用");
//		   pst.setInt(2,act.getPalceId());
//		   pst.execute();
//		   }
//		   else
//		   {
//			   sql="update act set state=? where activityId=?";
//			   pst=conn.prepareStatement(sql);
//			   pst.setString(1, "审批不通过");
//			   pst.setInt(2,act.getActivityId());
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
    public void checkDelAct(Activity act) throws BaseException {
        // TODO: implement
        Connection conn=null;
        try
        {
            conn=this.getConnection();
            String sql="delete from act where activityId=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,act.getActivityId());
            pst.execute();
        }
        catch(Exception e)
        {
            throw new BaseException("审批失败");
        }
    }

    //管理员按审批按钮，可以是管理员收到邮件，点击后显示信息，然后有一个审批按钮
    public void checkAddAsso(Association asso) throws BaseException {
        // TODO: implement
        Connection conn=null;
        try
        {
            conn=this.getConnection();
            String sql="update asso set state=? where associationId=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, "已成立");
            pst.setInt(2,asso.getAssociationId());
            pst.execute();
            Place p=new Place();
            p.setPlaceId(asso.getPlacId());
            p.setStatus("不可用");
            PlaceDao pm=new PlaceDao();
            pm.modPstatus(p);
        }
        catch(Exception e)
        {
            throw new BaseException("审批失败");
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
    public void checkDelAsso(Association asso) {
        // TODO: implement
        Connection conn=null;
        try
        {
            conn=this.getConnection();
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

    //修改社长，选择社长的时候，可采用下拉框的形式，选择社员
    public void modLeader(String leaderSno,Association asso) {
        // TODO: implement
        Connection conn=null;
        try
        {
            conn=this.getConnection();
            String sql="update asso_p set state=? where sno=? and associationId=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, "离职");
            pst.setString(2,asso.getChiefSno());
            pst.setInt(3,asso.getAssociationId());
            pst.execute();

            sql="update asso_p set role=? where sno=? and associationId=?";
            pst=conn.prepareStatement(sql);
            pst.setString(1, "社长");
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


    public List<Activity> listActApprove() {
        List<Activity> result = new ArrayList<>();

        return result;
    }

}
