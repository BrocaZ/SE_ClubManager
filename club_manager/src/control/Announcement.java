package control;

import java.sql.Connection;
import java.util.*;

import util.DBUtil;

public class Announcement {
	private int annoucementId;
	private int associationId;
	private int annoContent;
	private int time;
	private int activityId;
	private int place;
	private int annoType;
	private int status;
	private int remarks;

	public int addAnno() {
		Connection conn=null;
		try {
			conn = DBUtil.getConnection();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	public int delAnno() {
		// TODO: implement
		return 0;
	}

	public int annoList() {
		// TODO: implement
		return 0;
	}

	public int searchAnno() {
		// TODO: implement
		return 0;
	}

}