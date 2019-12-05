package servlet;

import dao.AssoDao;
import dao.PlaceDao;
import dao.StuDao;
import entity.Association;
import entity.Place;
import entity.Student;
import exception.BaseException;
import exception.BusinessException;
import exception.DbException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/addPlace")
public class addPlace extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public addPlace() {
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

        String name = request.getParameter("name");
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(request.getParameter("capacity"));
        HttpSession session=request.getSession();
        if (!isNum.matches()) {
            session.setAttribute("message", "容量只能填写数字");
            response.sendRedirect("admin_pages/addPlace.jsp");
            return;
        }
        int capacity = Integer.valueOf(request.getParameter("capacity"));

        System.out.println(name);
        System.out.println(capacity);

        PlaceDao placeDao = new PlaceDao();
        Place p = new Place();
        p.setPlaceName(name);
        p.setAvailable(capacity);
        try {
            placeDao.addPlace(p);
            session.setAttribute("message", "添加成功！");
            response.sendRedirect("admin_pages/place.jsp");
        } catch (BaseException | SQLException e) {
            e.printStackTrace();
            session.setAttribute("message", "添加失败！（详情参见控制台）");
            response.sendRedirect("admin_pages/place.jsp");
        }
    }

    public void init() throws ServletException {
        // Put your code here
    }
}
