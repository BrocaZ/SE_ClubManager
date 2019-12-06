<%--
  Created by IntelliJ IDEA.
  User: ANARKHWQH
  Date: 2019/12/1
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="dao.StuDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.AssoDao" %>
<%@ page import="dao.StuDao" %>
<%@ page import="entity.Association" %>
<%@ page import="dao.AnnoDao" %>
<%@ page import="entity.Announcement" %>
<%@ page import="entity.Student" %>
<%@ page import="entity.Message" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>活动公告</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <link rel="stylesheet" type="text/css" media="screen" href="https://cdn.staticfile.org/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Bootstrap CSS-->
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome CSS-->
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- Custom Font Icons CSS-->
    <link rel="stylesheet" href="css/font.css">
    <!-- Google fonts - Muli-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Muli:300,400,700">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="css/style.pink.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="css/custom.css">
    <link rel="stylesheet" type="text/css" media="screen" href="https://cdn.staticfile.org/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="img/favicon.ico">
    <!-- Tweaks for older IEs-->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
<%--    <script type="text/javascript">--%>
<%--        window.onload=function () {--%>
<%--            var list2 = "<option>A</option>";--%>
<%--            <%--%>
<%--            request.setCharacterEncoding("utf-8");--%>
<%--            String BranchName=request.getParameter("BranchName");--%>
<%--            System.out.println(BranchName);--%>
<%--             StuDao stuDao=new StuDao();--%>
<%--             List<String> result = stuDao.majorInBranch("商学院");--%>
<%--            for (int i = 0; i < result.size(); i++) {--%>
<%--                String majorName = result.get(i);--%>
<%--           %>--%>
<%--            list2 = list2 + "<option>" + <%=majorName%> +"</option>";--%>
<%--            <%}%>--%>
<%--            // $("#list2").html('XXXX');--%>
<%--            document.getElementById('list2').innerHTML = list2;--%>
<%--        }--%>

<%--    </script>--%>
</head>

<body>
<header class="header">
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid d-flex align-items-center justify-content-between">
            <div class="navbar-header">
                <!-- Navbar Header--><a href="actAnno.jsp" class="navbar-brand">
                <div class="brand-text brand-big visible text-uppercase"><strong class="text-primary">ZUCC</strong><strong>SOCIETY</strong></div>
                <div class="brand-text brand-sm"><strong class="text-primary">Z</strong><strong>S</strong></div></a>
                <!-- Sidebar Toggle Btn-->
                <button class="sidebar-toggle"><i class="fa fa-long-arrow-left"></i></button>
            </div>
            <div class="right-menu list-inline no-margin-bottom">
                <div class="list-inline-item dropdown"><a id="navbarDropdownMenuLink1" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link messages-toggle"><i class="icon-email"></i></a>
                    <div aria-labelledby="navbarDropdownMenuLink1" class="dropdown-menu messages">
                        <%
                            StuDao stuDao = new StuDao();
                            String sno=stuDao.getCurID();
                            List<Message> result=stuDao.messageInStu(sno);
                            for (int i=0;i<result.size() && i<=4;i++){
                                Message m=result.get(i);
                                String sendsno=m.getSendsno();
                                String sendname=stuDao.findStu(sendsno).getName();
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                String date = formatter.format(m.getSenddate());

                        %>
                        <a href="#" class="dropdown-item message d-flex align-items-center">
                            <div class="profile"><img src="img/t1.jpg" alt="..." class="img-fluid"></div>
                            <div class="content" > <strong class="d-block"><%=sendname%>></strong><span class="d-block"><%=m.getContent()%></span><small class="date d-block"><%=date%></small></div></a>
                        <%
                            }
                        %>
                        <a href="message.jsp" class="dropdown-item text-center message">
                            <strong>See All Messages <i class="fa fa-angle-right"></i></strong></a>
                    </div>
                </div>
                <!-- Log out               -->
                <div class="list-inline-item logout"><a id="logout" href="index.jsp" class="nav-link"> <span class="d-none d-sm-inline">退出 </span><i class="icon-logout"></i></a></div>
            </div>
        </div>
    </nav>
</header>
<div class="d-flex align-items-stretch">
    <!-- Sidebar Navigation-->
    <nav id="sidebar">
        <!-- Sidebar Header-->
        <div class="sidebar-header d-flex align-items-center">
            <%
                stuDao = new StuDao();
                String name = stuDao.findStu(stuDao.getCurID()).getName();
                if (name != null) {
            %>
            <div class="avatar">
                <img src="img/t7.jpg" alt="..." class="img-fluid rounded-circle">
            </div>
            <div class="title">
                <h1 class="h5"><a href="modifyInfo.jsp"><%=name %></a></h1>
            </div>
            <%} %>
        </div>
        <ul class="list-unstyled">
            <li>
                <a href="actAnno.jsp"> <i class="icon-home"></i>活动公告 </a>
            </li>
            <li><a href="#exampledropdownDropdown" data-toggle="collapse"> <i
                    class="icon-windows"></i>我的社团 </a>
                <ul id="exampledropdownDropdown" class="collapse list-unstyled ">
                    <%
                        AssoDao assoDao = new AssoDao();
                        stuDao = new StuDao();
                        List<Integer> list = assoDao.assoPersonList(stuDao.getCurID());
                        for (int i = 0; i < list.size(); i++) {
                            String assoName = assoDao.searchAssoById(list.get(i)).getAssociationName();
                    %>
                    <li><a href="society.jsp?assoName=<%=assoName%>"><%=assoName%>
                    </a></li>
                    <%}%>
                </ul>
            </li>
            <li>
                <a href="overview.jsp"> <i class="icon-grid"></i>社团总览</a>
            </li>
            <!--社长-->
            <%
                if (assoDao.isLeader(stuDao.getCurID())) {
            %>
            <li>
                <a href="#exampledropdownDropdown" data-toggle="collapse1"> <i class="icon-settings"></i>社团管理 </a>
                <ul class="collapse1 list-unstyled ">
                    <li>
                        <a href="societyact-leader.jsp">活动列表</a>
                    </li>
                    <li>
                        <a href="societyanno-leader.jsp">公告列表</a>
                    </li>
                    <li class="active">
                        <a href="addstu-leader.jsp?check=0">添加社员</a>
                    </li>
                    <li>
                        <a href="changeleader.jsp">修改社团信息</a>
                    </li>
                </ul>
            </li>
            <!--社长-->
            <%
                }
            %>
        </ul>
    </nav>
    <!-- Sidebar Navigation end-->
    <div class="page-content">
        <div class="page-header">
            <div class="container-fluid">
                <h2 class="h5 no-margin-bottom">添加社员</h2>
            </div>
        </div>
        <div class="apply" style="padding-top: 50px; padding-left:200px;padding-bottom: 100px;">
            <form  action="${pageContext.request.contextPath}/AddStu?check=<%=request.getParameter("check")%>" method="post" style="text-align: center">
                <%
                    String snno="";
                    String check=request.getParameter("check");
                    if(!check.equals("0"))
                        snno=check;
                %>
                <div class="apply1">
                    <label class="apply-control-label">学号</label>
                    <input type="text" class="apply-control" style="width: 200px" name="sno" value="<%=snno%>">
                </div>
                <%

                    if (!check.equals("0")){
                        Student stu=stuDao.findStu(check);

                %>
                <div class="apply1">
                    <label class="apply-control-label">姓名</label>
                    <label class="apply-control" style="width: 200px; font-size: 17px; text-align: left"><%=stu.getName()%></label>
                </div>
                <div class="apply1">
                    <label class="apply-control-label">分院</label>
                    <label class="apply-control" style="width: 200px; font-size: 17px; text-align: left"><%=stu.getBranch()%></label>
                </div>
                <div class="apply1">
                    <label class="apply-control-label">专业</label>
                    <label class="apply-control"style="width: 200px; font-size: 17px; text-align: left"><%=stu.getMajor()%></label>
                </div>
                <div class="apply1">
                    <label class="apply-control-label">班级</label>
                    <label class="apply-control"style="width: 200px; font-size: 17px; text-align: left"><%=stu.getStuclass()%></label>
                </div>
                <%
                    }
                %>
                <div class="apply-submit"style="margin-left: 29%;" >
                    <button type="submit" style="background-color: #ff6574;border-radius:5px; border: none; width: 100px;"><i class="icon ion-checkmark-round" style="font-size: 25px; color: #F5F5F5;"></i></button>
                </div>

<%--                <div class="apply1">--%>
<%--                    <label class="apply-control-label">分院</label>--%>
<%--                    <input type="text" class="apply-control" list="list1" name="BranchName">--%>
<%--                    <datalist id="list1">--%>
<%--                        <%--%>
<%--                            request.setCharacterEncoding("utf-8");--%>
<%--                            stuDao = new StuDao();--%>
<%--                            result = stuDao.allBranch();--%>
<%--                            for (int i = 0; i < result.size(); i++) {--%>
<%--                                BranchName = result.get(i);--%>
<%--                        %>--%>
<%--                        <option><%=BranchName%>--%>
<%--                        </option>--%>
<%--                        <%}%>--%>
<%--                    </datalist>--%>
<%--                </div>--%>
<%--                <div class="apply1">--%>
<%--                    <label class="apply-control-label">专业</label>--%>
<%--                    <input type="text" class="apply-control" list="list2" name="majorName" >--%>
<%--                    <datalist id="list2">--%>

<%--                    </datalist>--%>
<%--                </div>--%>
<%--                <div class="apply1">--%>
<%--                    <label class="apply-control-label">班级</label>--%>
<%--                    <input type="text" class="apply-control" list="list3" name="class">--%>
<%--                    <datalist id="list3">--%>
<%--                        <%--%>
<%--                            request.setCharacterEncoding("utf-8");--%>
<%--                            result = stuDao.classInMajor(request.getParameter("majorName"));--%>
<%--                            for (int i = 0; i < result.size(); i++) {--%>
<%--                                String classname = result.get(i);--%>
<%--                        %>--%>
<%--                        <option><%=classname%>--%>
<%--                        </option>--%>
<%--                        <%}%>--%>
<%--                    </datalist>--%>
<%--                </div>--%>
<%--                <div class="apply1">--%>
<%--                    <label class="apply-control-label">姓名</label>--%>
<%--                    <input type="text" class="apply-control" list="list4">--%>
<%--                    <datalist id="list4">--%>
<%--                        <%--%>
<%--                            request.setCharacterEncoding("utf-8");--%>
<%--                            result = stuDao.stuInClass(request.getParameter("majorName"), request.getParameter("class"));--%>
<%--                            for (int i = 0; i < result.size(); i++) {--%>
<%--                                String stuname = result.get(i);--%>
<%--                        %>--%>
<%--                        <option><%=stuname%>--%>
<%--                        </option>--%>
<%--                        <%}%>--%>
<%--                    </datalist>--%>
<%--                </div>--%>
            </form>
        </div>

    </div>
</div>
<!-- JavaScript files-->
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="vendor/popper.js/umd/popper.min.js">
</script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/4.2.1/js/bootstrap.min.js"></script>
<script src="vendor/jquery.cookie/jquery.cookie.js">
</script>
<script src="https://cdn.bootcss.com/Chart.js/2.7.3/Chart.min.js"></script>
<script src="vendor/jquery-validation/jquery.validate.min.js"></script>
<script src="js/charts-home.js"></script>
<script src="js/front.js"></script>
</body>

</html>