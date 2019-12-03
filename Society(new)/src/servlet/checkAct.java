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

@WebServlet("/checkAct")
public class checkAct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public checkAct() {
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
        System.out.println("checkAct连接数据库成功");
        String choose = request.getParameter("res");

        response.sendRedirect("admin_pages/checkActList.jsp");
    }

    public void init() throws ServletException {
        // Put your code here
    }
}
