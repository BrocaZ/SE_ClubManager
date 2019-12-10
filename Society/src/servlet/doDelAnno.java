package servlet;
import dao.ActDao;
import dao.AnnoDao;
import entity.Announcement;
import exception.BaseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/doDelAnno")
public class doDelAnno extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public doDelAnno() {
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
        AnnoDao annoDao=new AnnoDao();
        Integer id= Integer.valueOf(request.getParameter("annoid"));
//        System.out.println(id);
        Announcement anno=new Announcement();
        anno.setAnnoucementId(id);
        HttpSession session=request.getSession();
        try {
            annoDao.delAnno(anno);
            session.setAttribute("message", "删除成功！");
            response.sendRedirect("societyanno-leader.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("message", "发布失败！（详情见控制台）");
            response.sendRedirect("societyanno-leader.jsp");
        }
    }
}

