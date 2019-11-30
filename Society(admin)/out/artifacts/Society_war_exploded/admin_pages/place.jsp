<%@ page import="dao.StuDao" %>
<%@ page import="dao.PlaceDao" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Place" %><%--
  Created by IntelliJ IDEA.
  User: zky
  Date: 2019/11/29
  Time: 7:51 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>场地列表</title>
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
            background-image: url(${pageContext.request.contextPath }/img/sousuo.png);
            background-size: 25px 25px;
            background-position: 11px 13px;
            background-repeat: no-repeat;
            border-top-right-radius: 15px;
            border-bottom-right-radius: 15px;
            border-left: 0px;
        }
    </style>
    <script type="text/javascript">
        function check(){
            var mymessage=confirm("确定要删除吗？");
            if(mymessage==true){
                return true;
            }
            else if(mymessage==false){
                return false;
            }
        }
    </script>
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
                <!--            <p>Web Designer</p>-->
            </div>
            <%}%>
        </div>

        <ul class="list-unstyled">
            <li>
                <a href="approve.jsp"> <i class="icon-home"></i>审批列表 </a>
            </li>
            <li>
                <a href="assoCheck.jsp"> <i class="icon-grid"></i>社团列表 </a>
            </li>
            <li class="active">
                <a href="place.jsp"> <i class="icon-windows"></i>场地列表</a>
            </li>
        </ul>
    </nav>
    <!-- Sidebar Navigation end-->
    <div class="page-content">
        <!-- Page Header-->
        <div class="page-header no-margin-bottom">
            <div class="container-fluid">
                <h2 class="h5 no-margin-bottom">场地列表</h2>
            </div>
        </div>
        <!-- Breadcrumb-->
        <div class="row">
            <form action="" id="research">
                <input type="text" name="keyword" autocomplete="off" placeholder="请输入场地名称" />
                <button></button>
            </form>
        </div>
        <!--    <div class="row">-->
        <div class="messages-block block">
            <!-- <div class="title"><strong>New Messages</strong></div> -->
            <div class="messages">
                <%
                    PlaceDao placeDao = new PlaceDao();
                    List<Place> placeList = placeDao.placeList();
                    for(int i=0;i<placeList.size();i++){
                %>
                <a class="message d-flex align-items-center">
<%--                    <input id="inlineCheckbox1" type="checkbox" value="option1" style="width: 30px; height: 20px;">--%>
                    <div class="message d-flex align-items-center" style="width: 97%;">
                        <strong class="d-block" style="padding-left: 3%; padding-right: 5%; width: 45%;"><%=placeList.get(i).getPlaceName()%></strong>
                        <span class="d-block" style="width: 55%;">容量：<%=placeList.get(i).getAvailable()%></span>
                    </div>
                    <div href="${pageContext.request.contextPath }/servlet/delAsso?id=<%=placeList.get(i).getPlaceId()%>" onclick="return check()">
                        <i class="icon-close" style="padding-right: 5px;"></i>
                    </div>
                </a>
                <%}%>
            </div>
        </div>
        <div class="col-sm-9 ml-auto" style="float: right; width:15%;">
            <button class="btn btn-primary" style=" "><i class="icon-new-file" style="padding-right: 5px;"></i>增加场地</button>
<%--            <button class="btn btn-secondary" style="padding-right: 15px;"><i class="icon-close" style="padding-right: 5px;"></i>删减社团</button>--%>
        </div>
    </div>
</div>
<!-- JavaScript files-->
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/vendor/popper.js/umd/popper.min.js"></script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/4.2.1/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/vendor/jquery.cookie/jquery.cookie.js"></script>
<script src="https://cdn.bootcss.com/Chart.js/2.7.3/Chart.min.js"></script>
<script src="${pageContext.request.contextPath }/vendor/jquery-validation/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath }/js/front.js"></script>
</body>
</html>
