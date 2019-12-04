<%@ page import="dao.StuDao" %>
<%@ page import="entity.Association" %>
<%@ page import="dao.AssoDao" %>
<%@ page import="dao.PlaceDao" %><%--
  Created by IntelliJ IDEA.
  User: zky
  Date: 2019/12/2
  Time: 4:16 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>社团审批</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Bootstrap CSS-->
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome CSS-->
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- Custom Font Icons CSS-->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/font.css">
    <!-- Google fonts - Muli-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Muli:300,400,700">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.pink.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/img/favicon.ico">
    <!-- Tweaks for older IEs-->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
    <style type="text/css">
        #research {
            width: 405px;
            height: 50px;
            display: flex;
            float: left;
            margin: auto;
            /*margin-left: 300px;*/
            background-color: #FFFFFF;
            margin-bottom: 20px;
            border-radius: 15px;
            /*border-color: #2d3035;*/
            font-family: 'Microsoft YaHei';
            font-size: 25px;
            /*margin-top: 30px;*/
        }

        #research input {
            width: 355px;
            height: 50px;
            float: left;
            font-size: 14px;
            line-height: 63px;
            border: solid 2px;
            background-color: #FFFFFF;
            text-align: left;
            color: #b0b0b0;
            text-indent: 6px;
            /*border-color: #2d3035;*/
            border-top-left-radius: 15px;
            border-bottom-left-radius: 15px;
            border-right: 0px;
        }

        #research button {
            border: solid 2px;
            width: 50px;
            height: 50px;
            float: left;
            line-height: 50px;
            background-color: #FFFFFF;
            text-align: center;
            color:#b0b0b0;
            /*border-color: #2d3035;*/
            background-image: url(${pageContext.request.contextPath }/img/sousuo.png);
            background-size: 25px 25px;
            background-position: 11px 13px;
            background-repeat: no-repeat;
            border-top-right-radius: 15px;
            border-bottom-right-radius: 15px;
            border-left: 0px;
        }
    </style>
</head>
<body>
<header class="header">
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid d-flex align-items-center justify-content-between">
            <div class="navbar-header">
                <!-- Navbar Header-->
                <a href="approve.jsp" class="navbar-brand">
                    <div class="brand-text brand-big visible text-uppercase"><strong class="text-primary">ZUCC</strong><strong>SOCIETY</strong></div>
                    <div class="brand-text brand-sm"><strong class="text-primary">Z</strong><strong>S</strong></div>
                </a>
                <!-- Sidebar Toggle Btn-->
                <button class="sidebar-toggle"><i class="fa fa-long-arrow-left"></i></button>
            </div>
            <div class="right-menu list-inline no-margin-bottom">
                <!--            <div class="list-inline-item"><a href="#" class="search-open nav-link"><i class="icon-magnifying-glass-browser"></i></a></div>-->
<%--                <div class="list-inline-item dropdown">--%>
<%--                    <a id="navbarDropdownMenuLink1" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link messages-toggle"><i class="icon-email"></i><span class="badge dashbg-1">1</span></a>--%>
<%--                    <div aria-labelledby="navbarDropdownMenuLink1" class="dropdown-menu messages">--%>
<%--                        <a href="#" class="dropdown-item message d-flex align-items-center">--%>
<%--                            <div class="profile"><img src="img/t1.jpg" alt="..." class="img-fluid">--%>
<%--                                <div class="status online"></div>--%>
<%--                            </div>--%>
<%--                            <div class="content"> <strong class="d-block">姓名</strong><span class="d-block">理四开例会</span><small class="date d-block">9:30am</small></div>--%>
<%--                        </a>--%>
<%--                        <a href="index.html" class="dropdown-item text-center message">--%>
<%--                            <strong>See All Messages <i class="fa fa-angle-right"></i></strong></a>--%>
<%--                    </div>--%>
<%--                </div>--%>
                <!-- Log out               -->
                <div class="list-inline-item logout">
                    <a id="logout" href="${pageContext.request.contextPath }/index.jsp" class="nav-link"> <span class="d-none d-sm-inline">退出 </span><i class="icon-logout"></i></a>
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
            <div class="avatar"><img src="${pageContext.request.contextPath }/img/t7.jpg" alt="..." class="img-fluid rounded-circle"></div>
            <div class="title">
                <h1 class="h5"><%=name%></h1>
                <!--            <p>社员</p>-->
            </div>
            <%}%>
        </div>
        <!-- Sidebar Navidation Menus-->
        <!--          <span class="heading">Main</span>-->
        <ul class="list-unstyled">
            <li class="active"><a href="approve.jsp"> <i class="icon-home"></i>社团审批列表 </a></li>
            <li><a href="checkActList.jsp"> <i class="icon-list"></i>活动审批列表 </a></li>
            <li><a href="assoCheck.jsp"> <i class="icon-grid"></i>社团列表</a></li>
            <li><a href="place.jsp"> <i class="icon-windows"></i>场地列表</a></li>
        </ul>
    </nav>
    <!-- Sidebar Navigation end-->
    <div class="page-content">
        <div class="page-header">
            <div class="container-fluid">
                <h2 class="h5 no-margin-bottom">审批信息</h2>
                <!--        <div class="list-inline-item"><a href="#" class="search-open nav-link"><i class="icon-magnifying-glass-browser"></i></a></div>-->
            </div>
        </div>
        <div class="messages-block block"style="height: 90%;">
            <div class="messages" >
                <%
                    int assoid = Integer.valueOf(request.getParameter("assoid"));
                    AssoDao assoDao = new AssoDao();
                    PlaceDao placeDao = new PlaceDao();
                    Association asso = assoDao.searchAssoById(assoid);
                    if(asso!=null){
                %>
                <div style="width: 100%; padding-left: 15%; padding-top: 2%;"><strong style="font-size: 25px;">社团名称:</strong><small style="font-size: 20px; padding-left: 20px;"><%=asso.getAssociationName()%></small></div>
                <div style="width: 100%; padding-left: 15%; padding-top: 2%;"><strong style="font-size: 25px;">场地:</strong><small style="font-size: 20px; padding-left: 20px;"><%=placeDao.searchPlaceById(asso.getPlacId()).getPlaceName()%></small></div>
                <div style="width: 100%; padding-left: 15%; padding-top: 2%;"><strong style="font-size: 25px;">社长学号:</strong><small style="font-size: 20px; padding-left: 20px;"><%=asso.getChiefSno()%></small></div>
                <div style="width: 100%; padding-left: 15%; padding-top: 2%; padding-right: 10%; padding-bottom: 20px;"><strong style="font-size: 25px;">社团简介:</strong><small style="font-size: 20px; padding-left: 20px;"><%=asso.getIntro()%></small></div>
                <%}%>
            </div>
        </div>
        <div class="col-sm-9 ml-auto" style="float: right; width:20%;">
            <form action="${pageContext.request.contextPath}/checkAsso?res=yes&assoid=<%=assoid%>" method="post"><button type="submit" class="btn btn-primary">&nbsp;&nbsp;通过&nbsp;&nbsp;</button></form>
            <form action="${pageContext.request.contextPath}/checkAsso?res=no&assoid=<%=assoid%>" method="post"><button type="submit" class="btn btn-secondary">不通过</button></form>
        </div>
    </div>
</div>
<!-- JavaScript files-->
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/vendor/popper.js/umd/popper.min.js">
</script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/4.2.1/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/vendor/jquery.cookie/jquery.cookie.js">
</script>
<script src="https://cdn.bootcss.com/Chart.js/2.7.3/Chart.min.js"></script>
<script src="${pageContext.request.contextPath }/vendor/jquery-validation/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath }/js/charts-home.js"></script>
<script src="${pageContext.request.contextPath }/js/front.js"></script>
</body>
</html>
