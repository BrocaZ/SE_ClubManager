package servlet;

import dao.StuDao;
import entity.Student;
import exception.BaseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/doAddact")
public class doAddact extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public doAddact() {
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
        String acttheme=request.getParameter("acttheme");
        String actcontent=request.getParameter("actcontent");
        String actstarttime=request.getParameter("actstarttime");
        String actendtime=request.getParameter("actendtime");
        String actplace=request.getParameter("actplace");
        String actleader=request.getParameter("actleader");
        String state="hold";

        if(true){

        }

//        if(sno!=null && !sno.equals("")){
//            StuDao stuDao = new StuDao();
//            Student stu = new Student();
//            stuDao.setCurID(sno);
//            System.out.println(stuDao.getCurID());
//            try {
//                if(stuDao.findStu(sno)!=null){//存在这个用户，可以正常访问学生信息
////                    request.getSession().setAttribute("user", stu);
//                    response.sendRedirect("actAnno.jsp");
//
//                }else{//不存在这个用户，给出提示，转回登录页面
//                    String message = "用户名或密码错误";
//                    request.getSession().setAttribute("msg", message);
//                    response.sendRedirect("index.jsp");
//                }
//            } catch (BaseException e) {
//                e.printStackTrace();
//            }
//        }

    }

    public void init() throws ServletException {
        // Put your code here
    }
}
