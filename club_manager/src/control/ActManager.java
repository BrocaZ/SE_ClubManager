package control;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

import util.DBUtil;

public class ActManager {
   public int joinAct(int actId) {
      // TODO: implement
	   Connection conn=null;
	   try
	   {
		  conn=DBUtil.getConnection();
		  String sql="insert into activity_person(sno,activityId,state) values(?,?,?)";
		  PreparedStatement pst=conn.prepareStatement(sql);
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   
      return 0;
   }
   public int modAct() {
		// TODO: implement
		return 0;
	}

	public int addAct() {
		// TODO: implement
		return 0;
	}

	public int delAct() {
		// TODO: implement
		return 0;
	}

}