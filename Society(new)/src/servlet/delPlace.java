package servlet;

import dao.AssoDao;
import dao.PlaceDao;
import dao.StuDao;
import entity.Association;
import entity.Place;
import entity.Student;
import exception.BaseException;
import exception.DbException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delPlace")
public class delPlace extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public delPlace() {
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
        int id = Integer.valueOf(request.getParameter("id"));
        PlaceDao placeDao = new PlaceDao();
        HttpSession session=request.getSession();
        try {
            Place p = placeDao.searchPlaceById(id);
            placeDao.delPlace(p);
            session.setAttribute("message", "删除成功！");
            response.sendRedirect("admin_pages/place.jsp");
        } catch (BaseException | SQLException e) {
            session.setAttribute("message", "删除失败，可能被占用！（详情参见控制台）");
            response.sendRedirect("admin_pages/place.jsp");
            e.printStackTrace();
        }
    }

    public void init() throws ServletException {
        // Put your code here
    }
}
