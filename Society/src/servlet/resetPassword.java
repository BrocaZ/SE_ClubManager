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

@WebServlet("/resetPassword")
public class resetPassword extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public resetPassword() {
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
        String sno = request.getParameter("sno");
        StuDao stuDao = new StuDao();
        Student stu = new Student();
        stu.setSno(sno);
        stu.setPassword("123456");
        HttpSession session=request.getSession();
        try {
            if(stuDao.findStu(sno)==null){
                session.setAttribute("message", "该学生不存在！");
                response.sendRedirect("admin_pages/resetPassword.jsp");
                return;
            }
            stuDao.setPwd(stu);
            session.setAttribute("message", "重置成功！");
            response.sendRedirect("admin_pages/resetPassword.jsp");
        } catch (SQLException | BaseException e) {
            session.setAttribute("message", "操作失败！（详情参见控制台）");
            response.sendRedirect("admin_pages/resetPassword.jsp");
            e.printStackTrace();
        }
    }

    public void init() throws ServletException {
        // Put your code here
    }
}
