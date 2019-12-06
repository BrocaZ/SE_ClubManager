package servlet;

import dao.AssoDao;
import dao.StuDao;
import entity.Association;
import entity.Student;
import exception.BaseException;
import exception.DbException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/CheckJoinAct")
public class CheckJoinAct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CheckJoinAct() {
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
        int actid=Integer.parseInt(request.getParameter("actid"));
        String sno=request.getParameter("sno");
        String accept = request.getParameter("accept");

        AssoDao assoDao=new AssoDao();
        HttpSession session=request.getSession();
        try {
            if(accept.equals("yes")){
                assoDao.checkJoinAct(actid, sno, true);
                session.setAttribute("message", "已通过该报名！");
            }
            else if(accept.equals("no")){
                assoDao.checkJoinAct(actid, sno, false);
                session.setAttribute("message", "已拒绝该报名！");
            }
            response.sendRedirect("actperson-leader.jsp?id="+actid);
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("message", "操作失败！（详情见控制台）");
            response.sendRedirect("actperson-leader.jsp?id="+actid);
        }
    }

    public void init() throws ServletException {
        // Put your code here
    }
}
