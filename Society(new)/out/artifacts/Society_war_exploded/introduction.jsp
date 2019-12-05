<%@ page import="dao.StuDao" %>
<%@ page import="dao.AssoDao" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Association" %>
<%@ page import="exception.DbException" %><%--
  Created by IntelliJ IDEA.
  User: zky
  Date: 2019/11/26
  Time: 2:45 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>社团简介</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
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
    <!-- Favicon-->
    <link rel="shortcut icon" href="img/favicon.ico">
    <!-- Tweaks for older IEs-->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
    <style type="text/css">
        .test {
            font-size: 50px;
        }

        @media screen and (max-height:200px) {
            .test {
                font-size: 10px;
            }
        }
    </style>
</head>
<body>
<header class="header">
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid d-flex align-items-center justify-content-between">
            <div class="navbar-header">
                <!-- Navbar Header-->
                <a href="actAnno.jsp" class="navbar-brand">
                    <div class="brand-text brand-big visible text-uppercase"><strong class="text-primary">ZUCC</strong><strong>SOCIETY</strong></div>
                    <div class="brand-text brand-sm"><strong class="text-primary">Z</strong><strong>S</strong></div>
                </a>
                <!-- Sidebar Toggle Btn-->
                <button class="sidebar-toggle"><i class="fa fa-long-arrow-left"></i></button>
            </div>
            <div class="right-menu list-inline no-margin-bottom">
<%--                <div class="list-inline-item dropdown">--%>
<%--                    <a id="navbarDropdownMenuLink1" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link messages-toggle"><i class="icon-email"></i><span class="badge dashbg-1">1</span></a>--%>
<%--                    <div aria-labelledby="navbarDropdownMenuLink1" class="dropdown-menu messages">--%>
<%--                        <a href="#" class="dropdown-item message d-flex align-items-center">--%>
<%--                            <div class="profile"><img src="img/t1.jpg" alt="..." class="img-fluid">--%>
<%--                                <div class="status online"></div>--%>
<%--                            </div>--%>
<%--                            <div class="content"> <strong class="d-block">姓名</strong><span class="d-block">理四开例会</span><small class="date d-block">9:30am</small></div>--%>
<%--                        </a>--%>
<%--                        <a href="actAnno.jsp" class="dropdown-item text-center message">--%>
<%--                            <strong>See All Messages <i class="fa fa-angle-right"></i></strong></a>--%>
<%--                    </div>--%>
<%--                </div>--%>
                <div class="list-inline-item logout">
                    <a id="logout" href="index.jsp" class="nav-link"> <span class="d-none d-sm-inline">退出 </span><i class="icon-logout"></i></a>
                </div>
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
                StuDao stuDao = new StuDao();
                String name = stuDao.findStu(stuDao.getCurID()).getName();
                if(name!=null){
            %>
            <div class="avatar"><img src="img/t7.jpg" alt="..." class="img-fluid rounded-circle"></div>
            <div class="title">
                <h1 class="h5"><a href="modifyInfo.jsp"><%=name %></a></h1>
                <!--            <p>Web Designer</p>-->
            </div>
            <%} %>
        </div>
        <!-- Sidebar Navidation Menus-->
        <!--        <span class="heading">Main</span>-->
        <ul class="list-unstyled">
            <li>
                <a href="actAnno.jsp"> <i class="icon-home"></i>活动公告 </a>
            </li>
            <li>
                <a href="#exampledropdownDropdown" aria-expanded="false" data-toggle="collapse"> <i class="icon-windows"></i>我的社团 </a>
                <ul id="exampledropdownDropdown" class="collapse list-unstyled ">
                    <%
                        AssoDao assoDao = new AssoDao();
                        List<Integer> list= assoDao.assoPersonList(stuDao.getCurID());
                        for(int i=0;i<list.size();i++){
                            String assoName = assoDao.searchAssoById(list.get(i)).getAssociationName();
                    %>
                    <li>
                        <a href="society.jsp?assoName=<%=assoName%>"><%=assoName%></a>
                    </li>
                    <%}%>
                </ul>
            </li>
            <li class="active">
                <a href="overview.jsp"> <i class="icon-grid"></i>社团总览 </a>
            </li>
            <!--社长-->
            <%
                if (assoDao.isLeader(stuDao.getCurID())) {
            %>
            <li>
                <a> <i class="icon-settings"></i>社团管理 </a>
                <ul class="collapse1 list-unstyled ">
                    <li>
                        <a href="societyact-leader.jsp">活动列表</a>
                    </li>
                    <li>
                        <a href="societyanno-leader.jsp">公告列表</a>
                    </li>
                    <li>
                        <a href="addstu-leader.jsp?check=0">添加社员</a>
                    </li>
                    <li>
                        <a href="changeleader.jsp">修改社团信息</a>
                    </li>
                </ul>
            </li>
            <%}%>
            <!--社长-->
        </ul>

    </nav>
    <!-- Sidebar Navigation end-->
    <%
        request.setCharacterEncoding("utf-8");
        String id=request.getParameter("assoid");
        String assoname=null;
        String brief=null;
        if(id==null)
        {
            assoname=request.getParameter("keyword1");
            Association association = null;
            association=assoDao.searchAssoByName(assoname).get(0);
            brief=association.getIntro();

        }
        else {
            Association association = null;
            association = assoDao.searchAssoById((Integer.parseInt(id)));

            assoname = association.getAssociationName();
            brief = association.getIntro();
        }
    %>
    <div class="page-content">
        <div class="page-header">
            <div class="container-fluid">
                <h2 class="h5 no-margin-bottom"><%=assoname%>></h2>
            </div>
        </div>
        <!-- Breadcrumb-->

        <div  class="messages-block block"style="">
        <div class="d-block" style="margin-left: 100px; margin-top: 70px; width: 100%;">
            <img src="img/logo2.jpg" style=" border-radius: 30px; float: left; width: 20%;">
            <div style="font-family:'agency fb';color:#db6574;float: right; margin-top: 8%;margin-right: 25%; " class="test"><b><%=assoname%></b></div>
        </div>
        <span></span>
        <div class="d-block" style="margin-top: 250px; margin-left: 100px;">
            <strong style="font-size:38px ; font-family: '微软雅黑';">简介</strong>
            <p style="font-family: '微软雅黑'; font-size: 20px; margin-right: 10%;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=brief%></p>
        </div>
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
<script src="js/front.js"></script>
</body>
</html>
