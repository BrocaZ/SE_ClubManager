package servlet;
import dao.ActDao;
import dao.AnnoDao;
import dao.StuDao;
import entity.Student;
import exception.BaseException;
import javafx.scene.effect.InnerShadow;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/doJoinAct")
public class doJoinAct extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public doJoinAct() {
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
        StuDao stuDao = new StuDao();
        String sno=stuDao.getCurID();
        AnnoDao annoDao = new AnnoDao();
        Integer id= Integer.valueOf(request.getParameter("id"));
        ActDao actDao=new ActDao();
        try {
            actDao.joinAct(id);
            response.sendRedirect("joinAct.jsp?id="+id);
        } catch (BaseException e) {
            e.printStackTrace();
            response.sendRedirect("joinAct.jsp?id="+id);
        }
    }
}
