package servlet;

import dao.AssoDao;
import dao.StuDao;
import exception.DbException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

@WebServlet("/DelMes")
public class DelMes extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DelMes() {
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
        String mesid = request.getParameter("mesid");
        StuDao stuDao=new StuDao();
        HttpSession session=request.getSession();
        try {
            stuDao.delMes(Integer.parseInt(mesid));
            session.setAttribute("message", "删除成功！");
            response.sendRedirect("message.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("message", "删除失败！（详情见控制台）");
            response.sendRedirect("message.jsp");
        }

    }

    public void init() throws ServletException {
        // Put your code here
    }
}
