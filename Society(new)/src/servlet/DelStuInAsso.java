package servlet;

import dao.AssoDao;
import exception.DbException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/DelStuInAsso")
public class DelStuInAsso extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DelStuInAsso() {
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
        String assoid = request.getParameter("assoid");
        String sno = request.getParameter("sno");
        String assoname = request.getParameter("assoname");
        System.out.println(assoname);
        AssoDao assoDao=new AssoDao();
        try {
            assoDao.exitAsso(sno,Integer.parseInt(assoid));
        } catch (DbException e) {
            e.printStackTrace();
        }
        String encoder = "utf-8";
        String s = URLEncoder.encode(assoname,encoder);
        response.sendRedirect("society.jsp?assoName="+s);
    }

    public void init() throws ServletException {
        // Put your code here
    }
}
