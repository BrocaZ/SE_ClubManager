package servlet;

import dao.ActDao;
import dao.Admin;
import dao.AssoDao;
import dao.StuDao;
import entity.Activity;
import entity.Association;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/checkAct")
public class checkAct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public checkAct() {
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
        System.out.println("checkAct连接数据库成功");
        boolean accept = false;
        String choose = request.getParameter("res");
        int actid = Integer.valueOf(request.getParameter("actid"));
        Admin admin = new Admin();
        ActDao actDao = new ActDao();
        Activity act = actDao.getActById(actid);
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(act.getStatus());
        HttpSession session=request.getSession();
        if(choose.equals("yes")){
            accept = true;
        }
        try {
            if(isNum.matches()){
                //修改信息
                admin.checkModAct(act,accept);
            }
            else if(act.getStatus().equals("del")){
                //申请删除
                admin.checkDelAct(act,accept);
            }
            else if(act.getStatus().equals("add")){
                //申请添加
                admin.checkAddAct(act,accept);
            }
            else{
                //其他
                session.setAttribute("accept", "该申请数据存在问题（与数据存储有关）！");
            }

            if(accept){
                session.setAttribute("accept", "申请已通过！");
            }
            else {
                session.setAttribute("accept", "申请已驳回！");
            }
        } catch (BaseException e) {
            e.printStackTrace();
        }
        response.sendRedirect("admin_pages/checkActList.jsp");
    }

    public void init() throws ServletException {
        // Put your code here
    }
}
