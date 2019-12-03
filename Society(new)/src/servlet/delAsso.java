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
        System.out.println("delAsso连接数据库成功");
        int id = Integer.valueOf(request.getParameter("id"));
        AssoDao assoDao = new AssoDao();
        try {
            assoDao.delAsso(id);
            response.sendRedirect("admin_pages/assoCheck.jsp");
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void init() throws ServletException {
        // Put your code here
    }
}
