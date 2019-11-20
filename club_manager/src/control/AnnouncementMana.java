package control;

import java.sql.Connection;
import java.util.*;

import util.DBUtil;

public class AnnouncementMana {

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