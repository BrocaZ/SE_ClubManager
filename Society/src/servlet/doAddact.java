package servlet;

import dao.ActDao;
import dao.PlaceDao;
import dao.StuDao;
import entity.Activity;
import exception.BaseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
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
        int actid=Integer.parseInt(request.getParameter("actid"));
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
        String now = formatter.format(new Date());
        //res1需要<0，res2需要<0
        int res1=now.compareTo(actstarttime);
        int res2=actstarttime.compareTo(actendtime);

        HttpSession session=request.getSession();
        try {
            StuDao stuDao = new StuDao();
            if(stuDao.findStu(actleader)==null){
                session.setAttribute("message", "该学生不存在，请重新填写！");
                response.sendRedirect("addact.jsp?actid="+actid);
                return;
            }
            if(res1<0&&res2<0){
                starttime = formatter.parse(actstarttime);
                endtime = formatter.parse(actendtime);
                Date date = new Date();
                String dateString = formatter.format(date);
                PlaceDao pla=new PlaceDao();
                int plaid=0;
                plaid = pla.getPlaceByName(actplace);
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
                if(actid==0){
                    actDao.addAct(act);
                }else{
                    act.setActivityId(actid);
                    actDao.modAct(act);
                }
                session.setAttribute("message", "申请已提交到管理员！");
                response.sendRedirect("societyact-leader.jsp");
            }
            else {
                session.setAttribute("message", "时间填写有误，请重新填写！");
                response.sendRedirect("addact.jsp?actid"+actid);
            }
        } catch (BaseException | ParseException | SQLException e) {
            e.printStackTrace();
            session.setAttribute("message", "操作失败！（详情见控制台）");
            response.sendRedirect("societyact-leader.jsp");
        }
    }


    public void init() throws ServletException {
        // Put your code here
    }
}
