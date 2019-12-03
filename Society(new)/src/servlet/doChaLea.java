package servlet;

import dao.AssoDao;
import entity.Association;
import exception.DbException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/doChaLea")
public class doChaLea extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public doChaLea() {
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
        String sno = request.getParameter("stuname");
        int assoid= Integer.valueOf( request.getParameter("assoid"));
        AssoDao assoDao=new AssoDao();
        String id=sno.substring(0,8);
        try {

            Association asso= assoDao.searchAssoById(assoid);
            assoDao.modLeader(id,asso);
            response.sendRedirect("actAnno.jsp");
        } catch (DbException e) {
            e.printStackTrace();
            response.sendRedirect("changeleader.jsp");
        }
    }

    public void init() throws ServletException {
        // Put your code here
    }
}
