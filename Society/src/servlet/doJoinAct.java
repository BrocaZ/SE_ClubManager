package servlet;
import dao.ActDao;
import dao.AnnoDao;
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

@WebServlet("/doJoinAct")
public class doJoinAct extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public doJoinAct() {
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
        int id= Integer.valueOf(request.getParameter("id"));
        ActDao actDao=new ActDao();
        HttpSession session=request.getSession();
        try {
            Activity act = actDao.getActById(id);
            if(act.getStatus().equals("over")){
                session.setAttribute("message", "活动已结束！");
                response.sendRedirect("actAnno.jsp");
                return;
            }
            actDao.joinAct(id);
            session.setAttribute("message", "报名成功！");
            response.sendRedirect("actAnno.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("message", "报名失败！（详情参见控制台）");
            response.sendRedirect("actAnno.jsp");
        }
    }
}
