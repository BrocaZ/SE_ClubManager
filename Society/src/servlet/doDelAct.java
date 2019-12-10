package servlet;
import dao.ActDao;
import exception.BaseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/doDelAct")
public class doDelAct extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public doDelAct() {
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
        ActDao actDao=new ActDao();
        Integer id= Integer.valueOf(request.getParameter("id"));
        HttpSession session=request.getSession();
        try {
            actDao.delAct(id);
            session.setAttribute("message", "取消活动申请已提交到管理员！");
            response.sendRedirect("societyact-leader.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("message", "操作失败！（详情见控制台）");
            response.sendRedirect("societyact-leader.jsp");
        }
    }
}
