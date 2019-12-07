package dao;

import entity.Association;
import entity.Student;
import exception.BaseException;
import exception.BusinessException;
import exception.DbException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssoDao extends BaseDao {

    //罗列一个学生的所有社团
    public List<Integer> assoPersonList(String sno) throws DbException {
        List<Integer> assoId = new ArrayList<Integer>();
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql = "select * from asso_p where sno = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,sno);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                assoId.add(rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return assoId;
    }

    // method that asso need
    //修改社团，logo虽然写在这，但是应该是没啥用的，修改logo用setAssoLogo
    public void modAsso(Association asso) throws DbException, BusinessException {
        Connection conn = null;
        int assoid = 0;
        try {
            conn = this.getConnection();
            String sql = "select MAX(AssociationId) from asso";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                assoid = rs.getInt(1);
            }
            assoid += 1;
//            asso.setAssociationId(assoid);
            sql = "INSERT into asso(AssociationId,placeId,associationName,logo,chiefSno,brief_introduction,state,remarks) VALUES(?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, assoid);
            pst.setInt(2, asso.getPlacId());
            pst.setString(3, asso.getAssociationName());
            pst.setBytes(4, asso.getLogo());
            pst.setString(5, asso.getChiefSno());
            pst.setString(6, asso.getIntro());
            pst.setString(7, asso.getAssociationId()+"");
            pst.setString(8, asso.getRemarks());

            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException(e);
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

    public Association addAsso(Association asso) throws BusinessException, SQLException {
        Connection conn = null;
        int assoid = 0;
        conn = this.getConnection();
        String sql = "select MAX(AssociationId) from asso";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            assoid = rs.getInt(1);
        }
        assoid += 1;
        asso.setAssociationId(assoid);

        sql = "select * from stu where sno=?";
        pst = conn.prepareStatement(sql);
        pst.setString(1,asso.getChiefSno());
        rs = pst.executeQuery();
        if(!rs.next()){
            //不存在该学生
            throw new BusinessException("社长学号不存在");
        }

        sql = "INSERT into asso(AssociationId,placeId,associationName,logo,chiefSno,brief_introduction,state,remarks) VALUES(?,?,?,?,?,?,?,?)";
        pst = conn.prepareStatement(sql);
        pst.setInt(1, assoid);
        pst.setInt(2, asso.getPlacId());
        pst.setString(3, asso.getAssociationName());
        pst.setBytes(4, asso.getLogo());
        pst.setString(5, asso.getChiefSno());
        pst.setString(6, asso.getIntro());
        pst.setString(7, "ok");
        pst.setString(8, asso.getRemarks());

        pst.execute();

        //将社长加入社团人员表
        sql = "INSERT into asso_p(sno,associationId,role) VALUES (?,?,'社长')";
        pst = conn.prepareStatement(sql);
        pst.setString(1, asso.getChiefSno());
        pst.setInt(2, assoid);
        pst.execute();

        return asso;
    }
    /*
    * 没测试过的delasso*/
    public void delAsso(int assoid) throws SQLException {
        Connection conn = null;
        conn = this.getConnection();

        //删除社团人员表的相关信息
        String sql = "delete from act_p where activityid in(select activityid from act where act.associationId = ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, assoid);
        pst.execute();
        sql = "delete from anno where assoId = ?";
        pst = conn.prepareStatement(sql);
        pst.setInt(1, assoid);
        pst.execute();
        sql = "delete from act where associationId = ?";
        pst = conn.prepareStatement(sql);
        pst.setInt(1, assoid);
        pst.execute();
        sql = "delete from asso_p where associationId = ?";
        pst = conn.prepareStatement(sql);
        pst.setInt(1, assoid);
        pst.execute();
        sql = "delete from asso where state = ?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, assoid+"");
        pst.execute();
        sql = "delete from asso where associationId = ?";
        pst = conn.prepareStatement(sql);
        pst.setInt(1, assoid);
        pst.execute();

//            //删除社团表中的信息
//            sql = "DELETE from asso where associationId = ?";
//            pst = conn.prepareStatement(sql);
//            pst.setInt(1, assoid);
//            pst.execute();

    }

    //返回所有成立的社团Id，按社员数量排列，用于社团浏览处
    public List<Integer> assoListDESC(){
        List<Integer> res = new ArrayList<Integer>();
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql = "select COUNT(sno) c,associationId from asso_p GROUP BY associationId ORDER BY c DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int a = rs.getInt(2);
                res.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
//			throw new util.DbException(e);
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

    //返回所有成立的社团
    public List<Association> assoList(){
        List<Association> res = new ArrayList<Association>();
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql = "select AssociationId,placeId,associationName,logo,chiefSno,brief_introduction,state,remarks from asso where state='ok'";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Association a = new Association();
                a.setAssociationId(rs.getInt(1));
                a.setPlacId(rs.getInt(2));
                a.setAssociationName(rs.getString(3));
                a.setLogo(rs.getBytes(4));
                a.setChiefSno(rs.getString(5));
                a.setIntro(rs.getString(6));
                a.setStatus(rs.getString(7));
                a.setRemarks(rs.getString(8));
                res.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
//			throw new util.DbException(e);
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

    //模糊查找已成立社团
    public List<Association> assoLikeList(String keyword){
        List<Association> res = new ArrayList<Association>();
        Connection conn = null;
        try {
            conn = this.getConnection();
            if(keyword==null){
                String sql = "select AssociationId,placeId,associationName,logo,chiefSno,brief_introduction,state,remarks from asso where state='ok'";
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Association a = new Association();
                    a.setAssociationId(rs.getInt(1));
                    a.setPlacId(rs.getInt(2));
                    a.setAssociationName(rs.getString(3));
                    a.setLogo(rs.getBytes(4));
                    a.setChiefSno(rs.getString(5));
                    a.setIntro(rs.getString(6));
                    a.setStatus(rs.getString(7));
                    a.setRemarks(rs.getString(8));
                    res.add(a);
                }
            }
            else{
                String sql = "select AssociationId,placeId,associationName,logo,chiefSno,brief_introduction,state,remarks from asso where state='ok' and associationName like ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1,"%"+keyword+"%");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Association a = new Association();
                    a.setAssociationId(rs.getInt(1));
                    a.setPlacId(rs.getInt(2));
                    a.setAssociationName(rs.getString(3));
                    a.setLogo(rs.getBytes(4));
                    a.setChiefSno(rs.getString(5));
                    a.setIntro(rs.getString(6));
                    a.setStatus(rs.getString(7));
                    a.setRemarks(rs.getString(8));
                    res.add(a);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
//			throw new util.DbException(e);
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

    //untest
    //在社团人员表查找学生所在社团获得id之后调用这个方法获取社团
    //先写着也不知道有用没用
    //这里logo虽然有返回，但是没啥用，读也不从这里读，但是写还是写着了，不知道咋读
    public Association searchAssoById(int assoid) throws DbException {
        Association a = new Association();
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql = "select AssociationId,placeId,associationName,logo,chiefSno,brief_introduction,state,remarks from asso where associationId = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, assoid);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                a.setAssociationId(rs.getInt(1));
                a.setPlacId(rs.getInt(2));
                a.setAssociationName(rs.getString(3));
                a.setLogo(rs.getBytes(4));
                a.setChiefSno(rs.getString(5));
                a.setIntro(rs.getString(6));
                a.setStatus(rs.getString(7));
                a.setRemarks(rs.getString(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException(e);
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
        return a;
    }
    //untest
    //模糊查找
    public List<Association> searchAssoByName(String name){
        List<Association> res = new ArrayList<Association>();
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql = "select AssociationId,placeId,associationName,logo,chiefSno,brief_introduction,state,remarks from asso where associationName LIKE ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%"+name+"%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Association a = new Association();
                a.setAssociationId(rs.getInt(1));
                a.setPlacId(rs.getInt(2));
                a.setAssociationName(rs.getString(3));
                a.setLogo(rs.getBytes(4));
                a.setChiefSno(rs.getString(5));
                a.setIntro(rs.getString(6));
                a.setStatus(rs.getString(7));
                a.setRemarks(rs.getString(8));
                res.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
//			throw new util.DbException(e);
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
    // method need student and asso
    //判断表中有没有之后 再增加
    //社长调用的 线下面试之后由社长直接将社员加入
    public boolean isInAsso(String sno, int assoid) throws DbException, BusinessException {
        boolean res=false;
        Connection conn = null;
        try {
            //判断社员在不在这个社团
            conn = this.getConnection();
            String sql = "select * from asso_p where sno = ? and associationId = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, sno);
            pst.setInt(2, assoid);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                res=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException(e);
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
    public void joinAsso(String sno, int assoid) throws DbException, BusinessException {
        Connection conn = null;
        try {
            //判断社员在不在这个社团
            conn = this.getConnection();
            String sql = "INSERT into asso_p(sno,associationId,role) VALUES (?,?,'社员')";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, sno);
            pst.setInt(2, assoid);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException(e);
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
    //也是社长直接操作，将社员退社
    public void exitAsso(String sno,int assoid) throws DbException, SQLException {
        // TODO: implement
        Connection conn = null;
        conn = this.getConnection();
        String sql = "delete from asso_p where sno = ? and associationId = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, sno);
        pst.setInt(2, assoid);
        pst.execute();
        sendMessage(new StuDao().getCurID(),sno,"您已退出"+searchAssoById(assoid).getAssociationName());
    }

    //好像不用申请，所以就不审核了吧，社长直接退
//	public int checkExitAsso() {
//		// TODO: implement
//		return 0;
//	}
    /**
     * 返回改社团成员，不含社长，
     * 学生的信息只包含sno,name,sex,tel,affiliated_branch,major,class这些字段
     */
    public List<Student> assoMemberList(int assoid){
        List<Student> res = new ArrayList<Student>();
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql = "select sno,name,sex,tel,affiliated_branch,major,class from stu WHERE sno in (select sno from asso_p WHERE associationId = ? and role != '社长')";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, assoid);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Student stu = new Student();
                stu.setSno(rs.getString(1));
                stu.setName(rs.getString(2));
                stu.setSex(rs.getString(3));
                stu.setTel(rs.getString(4));
                stu.setBranch(rs.getString(5));
                stu.setMajor(rs.getString(6));
                stu.setStuclass(rs.getString(7));
                res.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
//			throw new util.DbException(e);
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

    //返回社长信息 只含sno,name,sex,tel,affiliated_branch,major,class这些字段
    public Student assoLeader(int assoid) {
        // TODO: implement
        Student stu = new Student();
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql = "select sno,name,sex,tel,affiliated_branch,major,class from stu WHERE sno in (select chiefsno from asso WHERE associationId = ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, assoid);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                stu.setSno(rs.getString(1));
                stu.setName(rs.getString(2));
                stu.setSex(rs.getString(3));
                stu.setTel(rs.getString(4));
                stu.setBranch(rs.getString(4));
                stu.setMajor(rs.getString(5));
                stu.setStuclass(rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
//			throw new util.DbException(e);
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
        return stu;
    }
    public boolean isLeader(String sno){
        boolean res = false;
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql = "select * from asso WHERE chiefSno = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,sno);
            ResultSet rs = pst.executeQuery();
            res = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
//			throw new util.DbException(e);
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
    public void setAssoLogo(int assoid,String filepath) {
        File f = new File(filepath);
        Connection conn = null;
        try(FileInputStream fis = new FileInputStream(f)){
            conn = this.getConnection();
            String sql = "update asso set logo = ? where associationId = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setBinaryStream(1, fis,f.length());
            pst.setInt(2, assoid);
            pst.execute();
        }catch(Exception e){
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
    }

    public void getAssoLogo(int assoid,String filepath) {
        Connection conn = null;
        try{
            conn = this.getConnection();
            String sql = "select logo from asso where associationId = ?";
//			java.sql.Statement st = conn.createStatement();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, assoid);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                java.sql.Blob b = rs.getBlob(1);
                File f = new File(filepath);
                try(FileOutputStream fos = new FileOutputStream(f)){
                    fos.write(b.getBytes(1, (int) b.length()));
                }
            }
        }catch(Exception e) {
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
    }

    public int getCurAssoId() throws BaseException {
        Connection conn=null;
        int associationId = 0;
        try {
            conn = this.getConnection();
            String sql = "select associationId from asso where chiefSno=? and state = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            StuDao stu=new StuDao();
            pst.setString(1, stu.getCurID());
            pst.setString(2, "ok");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                associationId = rs.getInt(1);
            }
        }catch(Exception e)
        {
            throw new BaseException("增加失败");
        }
        return associationId;
    }
    public void sendMessage(String sendsno,String recsno,String content){
        Connection conn=null;
        int mesid = 0;
        try {
            conn = this.getConnection();
            String sql = "SELECT MAX(mesid) from message";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                mesid = rs.getInt(1)+1;
            }

            sql = "INSERT into message(mesId,sendsno,recsno,content,senddate) VALUES(?,?,?,?,NOW())";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,mesid);
            pst.setString(2,sendsno);
            pst.setString(3,recsno);
            pst.setString(4,content);
            pst.execute();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void checkJoinAct(int actid, String sno, boolean accept) throws SQLException {
        Connection conn=null;
        conn = this.getConnection();
        if(accept){
            String sql = "update act_p set state = '审核通过' WHERE activityid = ? and sno = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,actid);
            pst.setString(2,sno);
            pst.execute();
        }else{
            String sql = "delete from act_p WHERE activityid = ? and sno = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,actid);
            pst.setString(2,sno);
            pst.execute();
        }
        String content = new ActDao().getActById(actid).getActtheme();
        String str = "未通过";
        if(accept){
            str = "通过";
        }
        sendMessage(new StuDao().getCurID(),sno,content+" 审核"+str);
    }
    public void modLeader(String leaderSno,Association asso) throws SQLException {
        Connection conn=null;
        conn=this.getConnection();
        String sql="delete from asso_p where sno=? and associationId=?";
        PreparedStatement pst=conn.prepareStatement(sql);
        pst.setString(1,asso.getChiefSno());
        pst.setInt(2,asso.getAssociationId());
        pst.execute();

        sql="update asso_p set role=? where sno=? and associationId=?";
        pst=conn.prepareStatement(sql);
        pst.setString(1, "社长");
        pst.setString(2,leaderSno);
        pst.setInt(3,asso.getAssociationId());
        pst.execute();

        sql="update asso set chiefSno=? where associationId=?";
        pst=conn.prepareStatement(sql);
        pst.setString(1, leaderSno);
        pst.setInt(2,asso.getAssociationId());
        pst.execute();
    }

//    public static void main(String[] args) {
//        AssoDao ad = new AssoDao();
//        new StuDao().setCurID("31701006");
//        try {
//            ad.exitAsso("31903281",2);
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
//        ad.checkJoinAct(2,"31701005",true);
//        ad.sendMessage("admin","31701005","hello");
//        List<Integer> l = ad.assoList();
//        for (int i : l){
//            System.out.println(i);
//        }
//        ad.checkJoinAct(3,"31701005",false);
//        List<Student> l = ad.assoMemberList(2);
//        for(Student s : l){
//            System.out.println(s.getName());
//        }
//        Association a = new Association();
//        a.setAssociationName("test");
//        a.setChiefSno("31601005");
//        a.setPlacId(1);
//        try {
//            a = ad.addAsso(a);
//            a.setPlacId(2);
//            ad.modAsso(a);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
