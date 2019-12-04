package servlet;

import dao.AssoDao;
import dao.PlaceDao;
import dao.StuDao;
import entity.Association;
import entity.Student;
import exception.BaseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddStu")
public class AddStu extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddStu() {
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

        String sno = request.getParameter("sno");
        String check=request.getParameter("check");
        StuDao stuDao=new StuDao();
        Student stu=new Student();
        
        try {
            if(check.equals("0")){
                System.out.println(check.equals("0"));
                response.sendRedirect("addstu-leader.jsp?check="+sno);
            }else{
                AssoDao assoDao=new AssoDao();
                int assoid=assoDao.getCurAssoId();
                if(assoDao.isInAsso(sno,assoid)){
                    //如果该学生已经加入该社团,跳出提示弹窗
                }else{
                    assoDao.joinAsso(sno,assoid);
                    response.sendRedirect("addstu-leader.jsp?check=0");
                }
            }

        } catch (BaseException e) {
            e.printStackTrace();
        }

    }

    public void init() throws ServletException {
        // Put your code here
    }
}
