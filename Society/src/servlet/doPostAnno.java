package servlet;

import dao.AnnoDao;
import entity.Announcement;
import entity.Place;
import entity.Student;
import exception.BaseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/doPostAnno")
public class doPostAnno extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public doPostAnno() {
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
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String url = request.getParameter("url");


        AnnoDao annoDao = new AnnoDao();
        Announcement anno=new Announcement();
        try {
            if (title.equals("") || title == null || content.equals("") || content == null ) {
                String message = "标题和内容不能为空";
                request.getSession().setAttribute("msg", message);
                response.sendRedirect("post-leader.jsp");
            } else {
                anno.settitle(title);
                anno.setAnnoContent(content);
//                anno.setHref(url);
                annoDao.addAnno(anno);
                response.sendRedirect("societyanno-leader.jsp");
            }
        } catch (BaseException e) {
            e.printStackTrace();
        }
    }

    public void init() throws ServletException {
        // Put your code here
    }
}
