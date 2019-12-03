package servlet;

import dao.AssoDao;
import dao.PlaceDao;
import dao.StuDao;
import entity.Association;
import entity.Student;
import exception.BaseException;
import exception.BusinessException;
import exception.DbException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addAsso")
public class addAsso extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public addAsso() {
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

        String assoName = request.getParameter("assoName");
        String place = request.getParameter("place");
        String leaderSno = request.getParameter("sno");
        String content = request.getParameter("content");
        String logo = request.getParameter("logo");

        System.out.println(assoName);
        System.out.println(place);
        System.out.println(leaderSno);
        System.out.println(content);
        System.out.println(logo);

        //添加logo，未解决
        try {
            AssoDao assoDao = new AssoDao();
            Association asso = new Association();
            PlaceDao placeDao = new PlaceDao();
            int placeId = placeDao.getPlaceByName(place);
            asso.setAssociationName(assoName);
            asso.setPlacId(placeId);
            asso.setChiefSno(leaderSno);
            asso.setIntro(content);
            assoDao.addAsso(asso);
        } catch (BaseException e) {
            e.printStackTrace();
        }
        response.sendRedirect("admin_pages/assoCheck.jsp");
    }

    public void init() throws ServletException {
        // Put your code here
    }
}
