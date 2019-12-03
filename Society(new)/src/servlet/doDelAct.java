package servlet;
import dao.ActDao;
import exception.BaseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/doDelAct")
public class doDelAct extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public doDelAct() {
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
        ActDao actDao=new ActDao();
        Integer id= Integer.valueOf(request.getParameter("id"));
        System.out.println(id);
        try {
            actDao.delAct(id);
            response.sendRedirect("societyact-leader.jsp");
        } catch (BaseException e) {
            e.printStackTrace();
            response.sendRedirect("societyact-leader.jsp");
        }
    }
}
