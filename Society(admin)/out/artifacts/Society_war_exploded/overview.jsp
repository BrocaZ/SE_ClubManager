<%@ page import="dao.StuDao" %>
<%@ page import="dao.AssoDao" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: zky
  Date: 2019/11/26
  Time: 1:46 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>社团总览</title>
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
        #research {
            width: 415px;
            height: 50px;
            display: flex;
            float: left;
            margin: auto;
            /*margin-left: 300px;*/
            background-color: #FFFFFF;
            margin-bottom: 40px;
            border-radius: 15px;
            /*  border-color: #2d3035;*/
            font-family: 'Microsoft YaHei';
            font-size: 25px;
            margin-top: 30px;
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
            /* border-color: #2d3035;*/
            border-top-left-radius: 15px;
            border-bottom-left-radius: 15px;
            border-right: 0px;
        }

        #research button {
            border: solid 2px;
            width: 60px;
            height: 50px;
            float: left;
            line-height: 50px;
            background-color: #FFFFFF;
            text-align: center;
            color: #b0b0b0;
            /* border-color: #2d3035;*/
            background-image: url(img/sousuo.png);
            background-size: 25px 25px;
            background-position: 11px 13px;
            background-repeat: no-repeat;
            border-top-right-radius: 15px;
            border-bottom-right-radius: 15px;
            border-left: 0px;
        }
        /*.row{
            background-color:white ;
        }*/
        .s-img img
        {
            cursor: pointer;
            transition: all 0.6s;
        }
        .s-img img:hover
        {
            transform: scale(1.4);
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
<%--                        <a href=actAnno.jsp" class="dropdown-item text-center message">--%>
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
                <h1 class="h5"><%=name %></h1>
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
<%--                    <li>--%>
<%--                        <a href="#">文学社</a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a href="#">动漫社</a>--%>
<%--                    </li>--%>
                </ul>
            </li>
            <li class="active">
                <a href="overview.jsp"> <i class="icon-grid"></i>社团总览 </a>
            </li>
        </ul>
    </nav>
    <!-- Sidebar Navigation end-->
    <div class="page-content">
        <!-- Page Header-->
        <div class="page-header no-margin-bottom">
            <div class="container-fluid">
                <h2 class="h5 no-margin-bottom">社团总览</h2>
                <!--        <div class="list-inline-item"><a href="#" class="search-open nav-link"><i class="icon-magnifying-glass-browser"></i></a></div>-->
            </div>
        </div>
        <!-- Breadcrumb-->
        <div class="row">
            <form action="" id="research">
                <input type="text" name="keyword" autocomplete="off" placeholder="请输入社团名称" />
                <button></button>
            </form>
        </div>
        <div id="myblock">
            <div class="d-block" style="margin-left: 11%; margin-top: 20px; margin-bottom: 50px;">
                <!--      <div class="row" style="margin: auto">-->
                <div class="s-img">
                    <a href="introduction.jsp"><img src="img/leya.jpg" style="width: 150px; height: 150px;margin-top: 10px; border-radius:75px;margin-right: 40px;margin-top: 50px;" /></a>
                    <a href="#"><img src="img/ziyou.png" style="width: 150px; height: 150px;margin-top: 10px; border-radius:75px; margin-right: 40px;margin-top: 50px;" /></a>
                    <a href="#"><img src="img/tennis.png" style="width: 150px; height: 150px;margin-top: 10px; border-radius:75px; margin-right: 40px;margin-top: 50px;" /></a>
                    <a href="#"><img src="img/gggl.png" style="width: 150px; height: 150px;margin-top: 10px; border-radius:75px;margin-top: 40px;" /></a>
                </div>
                <div class="s-img">
                    <a href="#"><img src="img/gx.png" style="width: 150px; height: 150px;margin-top: 10px; border-radius:75px;margin-right: 40px; margin-top: 50px;" /></a>
                    <a href="#"><img src="img/ymbl.jpg" style="width: 150px; height: 150px;margin-top: 10px; border-radius:75px;margin-right: 40px;margin-top: 50px;" /></a>
                    <a href="#"><img src="img/yx.jpg" style="width: 150px; height: 150px;margin-top: 10px; border-radius:75px;margin-right: 40px;margin-top: 50px;" /></a>
                    <a href="#"><img src="img/fxyjh.png" style="width: 150px; height: 150px;margin-top: 10px; border-radius:75px;margin-top: 40px;" /></a>
                </div>
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
