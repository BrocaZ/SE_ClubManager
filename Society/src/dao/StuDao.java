package dao;

import entity.Student;
import exception.BaseException;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;

public class StuDao extends BaseDao {

    private static String curID;

    public String getCurID() {
        return curID;
    }

    public void setCurID(String curID) {
        this.curID = curID;
    }

    public Student findStu(String sno) throws BaseException {
        Connection conn = null;
        Student stu = null;
        try {
            conn = this.getConnection();
            String sql="select * from stu where sno = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,sno);
            java.sql.ResultSet res = pst.executeQuery();
            if(res.next()){
                //存在，可登录
                stu = new Student();
                System.out.println("findStu连接数据库成功");
                stu.setSno(res.getString(1));
                stu.setHead_image(res.getBytes(2));
                stu.setName(res.getString(3));
                stu.setSex(res.getString(4));
                stu.setTel(res.getString(5));
                stu.setBranch(res.getString(6));
                stu.setStuclass(res.getString(7));
                stu.setPassword(res.getString(8));
                stu.setStatus(res.getString(9));
                stu.setRemarks(res.getString(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stu;
    }

    public String getName(String sno){
        Connection conn = null;
        String name = "";
        try {
            conn = this.getConnection();
            String sql="select name from stu where sno = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,sno);
            java.sql.ResultSet res = pst.executeQuery();
            if(res.next()){
                name = res.getString(1);
                System.out.println("getName连接数据库成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    public void modTel(Student s) throws BaseException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql="update stu set tel=? where sno = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, s.getTel());
            pst.setString(2, s.getSno());
            pst.execute();
        } catch (Exception e) {
            throw new BaseException("修改失败");
        }
    }

    public void setPwd(Student s) throws BaseException{
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql="update stu set password=? where sno = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, s.getPassword());
            pst.setString(2, s.getSno());
            pst.execute();
        } catch (Exception e) {
            throw new BaseException("修改失败");
        }
    }

    public void setHeadImage(Student s,String path) throws BaseException{
        Connection conn = null;
        try {
            conn = this.getConnection();
            FileInputStream images =new FileInputStream(new File(path));
            String sql="update stu set head_image=? where sno = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setBinaryStream(1, images, images.available());
            pst.setString(2, s.getSno());
            pst.execute();
        } catch (Exception e) {
//			throw new BaseException(e.getMessage());
            throw new BaseException("修改失败");
        }
    }
}
