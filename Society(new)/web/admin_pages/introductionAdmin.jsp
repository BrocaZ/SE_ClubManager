<%@ page import="dao.StuDao" %>
<%@ page import="dao.AssoDao" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Association" %><%--
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
                <a href="approve.jsp" class="navbar-brand">
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
                <h1 class="h5"><a href="modPassword.jsp"><%=name %></a></h1>
                <!--            <p>Web Designer</p>-->
            </div>
            <%} %>
        </div>
        <ul class="list-unstyled">
            <li><a href="approve.jsp"> <i class="icon-home"></i>社团审批列表 </a></li>
            <li><a href="checkActList.jsp"> <i class="icon-list"></i>活动审批列表 </a></li>
            <li class="active"><a href="assoCheck.jsp"> <i class="icon-grid"></i>社团列表</a></li>
            <li><a href="place.jsp"> <i class="icon-windows"></i>场地列表</a></li>
            <li><a href="resetPassword.jsp"> <i class="icon-user"></i>密码重置</a></li>
        </ul>
    </nav>
    <!-- Sidebar Navigation end-->
    <div class="page-content">
        <%
            int assoId = Integer.valueOf(request.getParameter("id"));
            AssoDao assoDao = new AssoDao();
            Association asso = assoDao.searchAssoById(assoId);
            if(asso!=null){
                int assoid=asso.getAssociationId();
                String path="img/"+assoid+".jpg";
        %>
        <div class="page-header">
            <div class="container-fluid">
                <h2 class="h5 no-margin-bottom"><%=asso.getAssociationName()%></h2>
            </div>
        </div>
        <!-- Breadcrumb-->
        <div class="messages-block block">
        <div class="messages" style="margin-left: 100px; margin-top: 70px; width: 100%;">
            <img src="${pageContext.request.contextPath }/<%=path%>" style=" border-radius: 30px; float: left; width: 20%;">
            <div style="font-family:'agency fb';color:#db6574;float: right; margin-top: 8%;margin-right: 25%; " class="test"><b><%=asso.getAssociationName()%></b></div>

        </div>
        <div class="d-block" style="margin-top: 350px; margin-left: 100px;">
            <strong style="font-size:38px ; font-family: '微软雅黑';">简介</strong>
            <p style="font-family: '微软雅黑'; font-size: 20px; margin-right: 10%;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=asso.getIntro()%></p>
        </div>
        </div>
        <%}%>
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
<script src="${pageContext.request.contextPath }/js/front.js"></script>
</body>
</html>
