package servlet;

import dao.AnnoDao;
import dao.AssoDao;
import entity.Announcement;
import entity.Place;
import entity.Student;
import exception.BaseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
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
        request.setCharacterEncoding("utf-8");
        String title = request.getParameter("title");
        String brief = request.getParameter("brief");
        String content = request.getParameter("content");
        String actid = request.getParameter("id");
        String type = request.getParameter("type");
//        System.out.println(actid);
//        System.out.println(title);
//        System.out.println(brief);
//        System.out.println(content);
//        System.out.println(type);
        int assoid=0;
        AssoDao assoDao=new AssoDao();
        HttpSession session=request.getSession();
        try {
            assoid=assoDao.getCurAssoId();
            AnnoDao annoDao = new AnnoDao();
            Announcement anno=new Announcement();
            anno.settitle(title);
            anno.setAnnoContent(content);
            anno.setAnnobrief(brief);
            anno.setAssociationId(assoid);

            anno.setActivityId(Integer.parseInt(actid));

            if(type.equals("1"))
                anno.setAnnoType("public");
            else
                anno.setAnnoType("secret");
            annoDao.addAnno(anno);
            session.setAttribute("message", "发布成功！");
            response.sendRedirect("societyanno-leader.jsp");
        } catch (BaseException | SQLException e) {
            e.printStackTrace();
            session.setAttribute("message", "发布失败！（详情见控制台）");
            response.sendRedirect("societyanno-leader.jsp");
        }
    }

    public void init() throws ServletException {
        // Put your code here
    }
}
