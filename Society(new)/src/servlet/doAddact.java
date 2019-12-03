package servlet;

import dao.ActDao;
import dao.PlaceDao;
import entity.Activity;
import exception.BaseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/doAddact")
public class doAddact extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public doAddact() {
        super();
    }

    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String acttheme = request.getParameter("acttheme");
        String actcontent = request.getParameter("content");
        String actstarttime = request.getParameter("actstarttime");
        String actendtime = request.getParameter("actendtime");
        String actplace = request.getParameter("actplace");
        String actleader = request.getParameter("actleader");
        int AttendNumber = 0;
        actstarttime=actstarttime.replace("T", " ");
        actendtime=actendtime.replace("T", " ");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date starttime = null;
        Date endtime=null;
        try {
            starttime = formatter.parse(actstarttime);
            endtime = formatter.parse(actendtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        String dateString = formatter.format(date);
        PlaceDao pla=new PlaceDao();
        int plaid=0;
        try {
            plaid = pla.getPlaceByName(actplace);
        } catch (BaseException e) {
            e.printStackTrace();
        }
        Activity act = new Activity();
        act.setPalceId(plaid);
        act.setActtheme(acttheme);
        act.setActivityContent(actcontent);
        act.setLeaderSno(actleader);
        act.setStartTime(starttime);
        act.setEndTime(endtime);
        act.setAttendNumber(AttendNumber);
        act.setRemarks(dateString);

        ActDao actDao = new ActDao();
        try {
            actDao.addAct(act);
            response.sendRedirect("societyact-leader.jsp");
        } catch (BaseException e) {
            e.printStackTrace();
        }
    }


    public void init() throws ServletException {
        // Put your code here
    }
}
