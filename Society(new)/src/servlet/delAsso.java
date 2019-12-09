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

@WebServlet("/delAsso")
public class delAsso extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public delAsso() {
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
        int id = Integer.valueOf(request.getParameter("id"));
        AssoDao assoDao = new AssoDao();
        HttpSession session=request.getSession();
        try {
            assoDao.delAsso(id);
            session.setAttribute("message", "删除成功！");
            response.sendRedirect("admin_pages/assoCheck.jsp");
        } catch (SQLException | DbException e) {
            session.setAttribute("message", "删除失败！（详情参见控制台）");
            response.sendRedirect("admin_pages/assoCheck.jsp");
            e.printStackTrace();
        }
    }

    public void init() throws ServletException {
        // Put your code here
    }
}
