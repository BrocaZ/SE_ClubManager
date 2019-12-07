package servlet;
import dao.ActDao;
import dao.AnnoDao;
import dao.StuDao;
import entity.Announcement;
import entity.Student;
import exception.BaseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/doModPassword")
public class doModPassword extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public doModPassword() {
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
        String oldpw=request.getParameter("oldpw");
        String pw=request.getParameter("stupw");
        String pw1=request.getParameter("stupw1");
        StuDao stuDao=new StuDao();
        String sno=stuDao.getCurID();
        Student stu1=new Student();
        boolean flag=true;
        stu1.setSno(sno);
        stu1.setPassword(pw);
        HttpSession session=request.getSession();
        try {
            Student stu=stuDao.findStu(sno);
            if(oldpw.compareTo(stu.getPassword())==0) {
                if (pw.compareTo(stu.getPassword()) != 0) {
                    if (pw.compareTo(pw1) == 0) {
                        stu1.setPassword(pw);
                        stuDao.setPwd(stu1);
                    } else {
                        flag = false;
                    }
                } else {
                    session.setAttribute("message", "原密码未做改动！");
                    response.sendRedirect("admin_pages/modPassword.jsp");
                    return ;
                }
            }
            else {
                session.setAttribute("message", "旧密码错误！");
                response.sendRedirect("admin_pages/modPassword.jsp");
                return ;
            }
            if(flag==true) {
                session.setAttribute("message", "修改成功！");
                response.sendRedirect("admin_pages/approve.jsp");
            }
            else
            {
                session.setAttribute("message", "两次新密码输入不一致！");
                response.sendRedirect("admin_pages/modPassword.jsp");
            }
        }
        catch (BaseException e) {
            e.printStackTrace();
            session.setAttribute("message", "操作失败！（详情见控制台）");
            response.sendRedirect("admin_pages/modPassword.jsp");
        }

    }
}

