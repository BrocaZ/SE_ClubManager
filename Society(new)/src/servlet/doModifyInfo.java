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
import java.io.IOException;
@WebServlet("/doModifyInfo")
public class doModifyInfo extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public doModifyInfo() {
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
        String tel=request.getParameter("stutel");
        String pw=request.getParameter("stupw");
        String pw1=request.getParameter("stupw1");
        StuDao stuDao=new StuDao();
        String sno=stuDao.getCurID();
        Student stu1=new Student();
        boolean flag=true;
        stu1.setSno(sno);
        stu1.setTel(tel);
        stu1.setPassword(pw);
        try {
            Student stu=stuDao.findStu(sno);
            if(tel.compareTo(stu.getTel())!=0)
            {
                stuDao.modTel(stu1);
            }


            if(pw.compareTo(stu.getPassword())!=0)
            {
                if(pw.compareTo(pw1)==0)
                {
                    stu1.setPassword(pw);
                    stuDao.setPwd(stu1);
                }
                else
                {
                   flag=false;
                }
            }
            if(flag==true)
            {
                response.sendRedirect("actAnno.jsp");
            }
            else
            {
                response.sendRedirect("modifyInfo.jsp");
            }
        }
        catch (BaseException e) {
            e.printStackTrace();
        }

    }
}

