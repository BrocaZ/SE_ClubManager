package servlet;

import dao.Admin;
import dao.AssoDao;
import dao.StuDao;
import entity.Association;
import entity.Student;
import exception.BaseException;
import exception.DbException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/checkAsso")
public class checkAsso extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public checkAsso() {
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
        System.out.println("checkAsso连接数据库成功");
        boolean accept = false;
        String choose = request.getParameter("res");
        int assoid = Integer.valueOf(request.getParameter("assoid"));
        if(choose.equals("yes")){
            accept = true;
        }
        Admin admin = new Admin();
        AssoDao assoDao = new AssoDao();
        try {
            Association asso = assoDao.searchAssoById(assoid);
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(asso.getStatus());
            if(isNum.matches()){
                //修改信息
                admin.checkModAsso(asso,accept);
            }
        } catch (BaseException e) {
            e.printStackTrace();
        }
        response.sendRedirect("admin_pages/approve.jsp");
    }

    public void init() throws ServletException {
        // Put your code here
    }
}
