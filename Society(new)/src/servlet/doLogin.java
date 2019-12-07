package servlet;

import dao.StuDao;
import entity.Student;
import exception.BaseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/doLogin")
public class doLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public doLogin() {
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
        String sno = request.getParameter("loginUsername");
        String password = StuDao.encryptAndDencrypt(request.getParameter("loginPassword"),'6');
        if(sno!=null && !sno.equals("")){
            StuDao stuDao = new StuDao();
            Student stu = new Student();
            stuDao.setCurID(sno);
            System.out.println(stuDao.getCurID());
            try {
                if((stuDao.findStu(sno)!=null)&&(stuDao.findStu(sno).getPassword().equals(password))){
                    //存在这个用户，可以正常访问学生信息
//                  request.getSession().setAttribute("user", stu);
                    if(stuDao.findStu(sno).getStatus()!=null&&stuDao.findStu(sno).getStatus().equals("管理员")){
                        response.sendRedirect("admin_pages/approve.jsp");
                    }
                    else{
                        response.sendRedirect("actAnno.jsp");
                    }
                }else{//不存在这个用户，给出提示，转回登录页面
                    request.setAttribute("message", "用户名或密码错误");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
//                    response.sendRedirect("index.jsp");
                }
            } catch (BaseException e) {
                e.printStackTrace();
            }
        }
    }

    public void init() throws ServletException {
        // Put your code here
    }
}
