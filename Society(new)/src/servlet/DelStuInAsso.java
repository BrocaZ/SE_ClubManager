package servlet;

import dao.AssoDao;
import exception.DbException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

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
        AssoDao assoDao=new AssoDao();
        HttpSession session=request.getSession();
        String encoder = "utf-8";
        String s = URLEncoder.encode(assoname,encoder);
        try {
            assoDao.exitAsso(sno,Integer.parseInt(assoid));
            session.setAttribute("message", "删除成功！");
            response.sendRedirect("society.jsp?assoName="+s);
        } catch (DbException | SQLException e) {
            e.printStackTrace();
            session.setAttribute("message", "删除失败！（详情参见控制台）");
            response.sendRedirect("society.jsp?assoName="+s);
        }
    }

    public void init() throws ServletException {
        // Put your code here
    }
}
