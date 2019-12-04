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
import java.io.IOException;

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
        System.out.println("CheckJoinAct连接数据库成功");
        int actid=Integer.parseInt(request.getParameter("actid"));
        String sno=request.getParameter("sno");
        String accept = request.getParameter("accept");

        AssoDao assoDao=new AssoDao();
        if(accept.equals("yes"))
            assoDao.checkJoinAct(actid, sno, true);
        else
            assoDao.checkJoinAct(actid, sno, false);

        response.sendRedirect("actperson-leader.jsp?id="+actid);
    }

    public void init() throws ServletException {
        // Put your code here
    }
}
