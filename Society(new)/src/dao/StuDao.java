package dao;

import entity.Activity;
import entity.Message;
import entity.Student;
import exception.BaseException;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StuDao extends BaseDao {

    private static String curID;

    public String getCurID() {
        return curID;
    }

    public void setCurID(String curID) {
        this.curID = curID;
    }
    public void initAct(){
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql="UPDATE pla set state = 'available' WHERE placeid in (select placeid from act where state = 'ok' and end_time < NOW())";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();

            sql = "UPDATE act set state = 'over' where state = 'ok' and end_time < NOW()";
            pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Message> messageInStu(String sno){
        Connection conn = null;
        List<Message> res = new ArrayList<Message>();
        try {
            conn = this.getConnection();
            String sql="select mesId,sendsno,recsno,content,senddate from message where recsno = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,sno);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                Message m = new Message();
                m.setMesid(rs.getInt(1));
                m.setSendsno(rs.getString(2));
                m.setRecsno(rs.getString(3));
                m.setContent(rs.getString(4));
                m.setSenddate(new java.util.Date(rs.getTimestamp(5).getTime()));
                res.add(m);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return res;
    }

    public void delMes(int mesid) throws SQLException {
        Connection conn = null;
    try{
        conn = this.getConnection();
        String sql="DELETE FROM message where mesid = ?";
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,mesid);
        pst.execute();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    }
    public Student findStu(String sno) throws BaseException {
        Connection conn = null;
        Student stu = null;
        try {
            conn = this.getConnection();
            String sql="select * from stu where sno = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,sno);
            ResultSet res = pst.executeQuery();
            if(res.next()){
                //存在，可登录
                stu = new Student();
                stu.setSno(res.getString(1));
                stu.setHead_image(res.getBytes(2));
                stu.setName(res.getString(3));
                stu.setSex(res.getString(4));
                stu.setTel(res.getString(5));
                stu.setBranch(res.getString(6));
                stu.setMajor(res.getString(7));
                stu.setStuclass(res.getString(8));
                stu.setPassword(res.getString(9));
                stu.setStatus(res.getString(10));
                stu.setRemarks(res.getString(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
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
            ResultSet res = pst.executeQuery();
            if(res.next()){
                name = res.getString(1);
                System.out.println("getName连接数据库成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
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
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public void setPwd(Student s) throws SQLException, BaseException {
        Connection conn = null;
        try{
        conn = this.getConnection();
        String sql="update stu set password=? where sno = ?";
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, StuDao.encryptAndDencrypt(s.getPassword(),'6'));
        pst.setString(2, s.getSno());
        pst.execute();
    } catch (Exception e) {
        throw new BaseException("修改失败");
    }finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    }

    //密码加密
    public static String encryptAndDencrypt(String value, char secret) {
        byte[] bt = value.getBytes(); // 将需要加密的内容转换为字节数组
        for (int i = 0; i < bt.length; i++) {
            bt[i] = (byte) (bt[i] ^ (int) secret); // 通过异或运算进行加密
        }
        String newresult = new String(bt, 0, bt.length); // 将加密后的字符串保存到 newresult 变量中
        return newresult;
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
        }finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    }

    public List<Activity> actOfStu(String sno){
        Connection conn = null;
        List<Activity> res = new ArrayList<Activity>();
        try {
            conn = this.getConnection();
            String sql="select acttheme, p.state from act_p p,act a WHERE a.activityId = p.activityId and sno = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,sno);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Activity a = new Activity();
                a.setActtheme(rs.getString(1));
                a.setStatus(rs.getString(2));
                res.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return res;
    }

            /*
     * 用在UI里的下拉框
     * */
    public List<String> allBranch(){
        Connection conn = null;
        List<String> res = new ArrayList<String>();
        try {
            conn = this.getConnection();
            String sql="select DISTINCT affiliated_branch from stu";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                res.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return res;
    }
    public List<String> majorInBranch(String branch){
        Connection conn = null;
        List<String> res = new ArrayList<String>();
        try {
            conn = this.getConnection();
            String sql="SELECT DISTINCT major from stu where affiliated_branch = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,branch);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                res.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return res;
    }
    public List<String> classInMajor(String major){
        Connection conn = null;
        List<String> res = new ArrayList<String>();
        try {
            conn = this.getConnection();
            String sql="SELECT DISTINCT class from stu where major = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,major);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                res.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return res;
    }
    public List<String> stuInClass(String major,String stuclass){
        Connection conn = null;
        List<String> res = new ArrayList<String>();
        try {
            conn = this.getConnection();
            String sql="SELECT DISTINCT sno,name from stu where major = ? and class = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,major);
            pst.setString(2,stuclass);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                res.add(rs.getString(1)+" "+rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return res;
    }


    //用于第一次加密数据库中的所有密码
//    public void hahaha(){
//        Connection conn = null;
//        try {
//            conn = this.getConnection();
//            String sql="update stu set password=?";
//            String key = encryptAndDencrypt("123456", '6');
//            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
//            pst.setString(1, key);
//            pst.execute();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public static void main(String[] args) {
//        StuDao sd = new StuDao();
//        sd.hahaha();
//    }

}
