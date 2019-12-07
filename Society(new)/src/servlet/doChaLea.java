package servlet;

import dao.AssoDao;
import dao.PlaceDao;
import entity.Association;
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
import java.text.SimpleDateFormat;
import java.util.Date;

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
        String assoname = request.getParameter("assoname");
        String assopla=request.getParameter("assopla");
        PlaceDao placeDao=new PlaceDao();
        String assobrief=request.getParameter("assobrief");
        String assochief=request.getParameter("assochief");
        String assochisno=assochief.substring(0,8);
//        System.out.println(assochisno);
        AssoDao assoDao=new AssoDao();
        HttpSession session=request.getSession();
        try {
            int plaid=placeDao.getPlaceByName(assopla);
            int assoid=assoDao.getCurAssoId();
            Association association=assoDao.searchAssoById(assoid);
            if(association.getChiefSno().compareTo(assochisno)!=0) {
                assoDao.modLeader(assochisno,association);
            }
            if(association.getAssociationName().compareTo(assoname)!=0||association.getPlacId()!=plaid||association.getIntro().compareTo(assobrief)!=0) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                association.setAssociationName(assoname);
                association.setPlacId(plaid);
                association.setIntro(assobrief);
                association.setRemarks(df.format(new Date()));
                assoDao.modAsso(association);
            }
            session.setAttribute("message", "修改申请已提交到管理员！");
            response.sendRedirect("actAnno.jsp");

        } catch (BaseException | SQLException e) {
            e.printStackTrace();
            session.setAttribute("message", "操作失败！（详情见控制台）");
            response.sendRedirect("actAnno.jsp");
        }

//        String id=sno.substring(0,8);
//        try {
//
//            Association asso= assoDao.searchAssoById(assoid);
//            assoDao.modLeader(id,asso);
//            response.sendRedirect("actAnno.jsp");
//        } catch (DbException e) {
//            e.printStackTrace();
//            response.sendRedirect("changeleader.jsp");
//        }
    }

    public void init() throws ServletException {
        // Put your code here
    }
}
