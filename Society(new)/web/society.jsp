<%@ page import="dao.StuDao" %>
<%@ page import="dao.AssoDao" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Student" %>
<%@ page import="entity.Announcement" %>
<%@ page import="dao.AnnoDao" %>
<%--
  Created by IntelliJ IDEA.
  User: zky
  Date: 2019/11/25
  Time: 9:35 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%String assooname = request.getParameter("assoName");%>
    <title><%=assooname%></title>
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
                <!--                <div class="list-inline-item"><a href="#" class="search-open nav-link"><i class="icon-magnifying-glass-browser"></i></a></div>-->
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
                <!-- Log out               -->
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
            </div>
            <%} %>
        </div>
        <ul class="list-unstyled">
            <li>
                <a href="actAnno.jsp"> <i class="icon-home"></i>活动公告 </a>
            </li>
            <li class="active">
                <a href="#exampledropdownDropdown" aria-expanded="true" data-toggle="collapse"> <i class="icon-windows"></i>我的社团 </a>
                <ul id="exampledropdownDropdown" class="collapse list-unstyled ">
                    <%
                        AssoDao assoDao = new AssoDao();
                        List<Integer> list= assoDao.assoPersonList(stuDao.getCurID());
                        for(int i=0;i<list.size();i++){
                            String assoName = assoDao.searchAssoById(list.get(i)).getAssociationName();
                    %>
                    <li><a href="society.jsp?assoName=<%=assoName%>"><%=assoName%></a></li>
                    <%}%>
                </ul>
            </li>
            <li>
                <a href="overview.jsp"> <i class="icon-grid"></i>社团总览 </a>
            </li>
            <!--社长-->
            <%
                if (assoDao.isLeader(stuDao.getCurID())) {
            %>
            <li>
                <a href="#exampledropdownDropdown" data-toggle="collapse1"> <i class="icon-settings"></i>社团管理 </a>
                <ul  class="collapse1 list-unstyled ">
                    <li>
                        <a href="societyact-leader.jsp">活动列表</a>
                    </li>
                    <li>
                        <a href="societyanno-leader.jsp">公告列表</a>
                    </li>
                    <li>
                        <a href="addstu-leader.jsp">添加社员</a>
                    </li>
                    <li>
                        <a href="changeleader.jsp">更换社长</a>
                    </li>
                </ul>
            </li>
            <%}%>
            <!--社长-->
        </ul>

    </nav>
    <!-- Sidebar Navigation end-->
    <div class="page-content">
        <!-- Page Header-->
        <div class="page-header">
            <div class="container-fluid">
                <h2 class="h5 no-margin-bottom"><%=assooname%></h2>
            </div>
        </div>
        <!-- Breadcrumb-->
        <section>
            <div class="container-fluid">
                <div class="row">
                    <div class="mycol-lg-5">
                        <div class="line-chart block chart ">
                            <div class="title"><strong>社团人员</strong></div>
                            <!--<canvas id="lineChartCustom1"></canvas>-->
                            <%
                                int id = assoDao.searchAssoByName(assooname).get(0).getAssociationId();
                                List<Student> stulist = assoDao.assoMemberList(id);
                                Student leader = assoDao.assoLeader(id);
                                if(leader!=null){
                            %>
                            <div class="public-user-block">
                                <div class="row d-flex align-items-center">
                                    <div class=" d-flex align-items-center">
                                        <div class="order">社长</div>
                                        <div class="avatar">
                                            <img src="img/t3.jpg" alt="..." class="img-fluid">
                                        </div>
                                        <div class="name">
                                            <strong class="d-block"><%=leader.getName()%></strong>
                                            <span class="d-block"><%=leader.getSno()%></span>
                                        </div>
                                    </div>
                                    <div class="">
                                        <div class="details d-flex">
                                            <div class="item"><i class="icon-contract"></i><strong><%=leader.getTel()%></strong></div>
                                            <div class="item"><i class="icon-cloud"></i><strong><%=leader.getMajor()%><%=leader.getStuclass()%></strong></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%}
                                for(int i=0;i<stulist.size();i++){
                            %>
                            <div class="public-user-block">
                                <div class="row d-flex align-items-center">
                                    <div class=" d-flex align-items-center self1">
                                        <div class="order">社员</div>
                                        <div class="avatar"> <img src="img/t2.jpg" alt="..." class="img-fluid"></div>
                                        <div class="name"><strong class="d-block"><%=stulist.get(i).getName()%></strong><span class="d-block"><%=stulist.get(i).getSno()%></span></div>
                                    </div>
                                    <div class="">
                                        <div class="details d-flex">
                                            <div class="item"><i class="icon-contract"></i><strong><%=stulist.get(i).getTel()%></strong></div>
                                            <div class="item"><i class="icon-cloud"></i><strong><%=stulist.get(i).getMajor()%><%=stulist.get(i).getStuclass()%></strong></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%}%>
                        </div>
                    </div>
                    <div class="mycol-lg-4">
                        <div class="lin-chart  chart">
                            <div class="title"><strong>内部公告</strong></div>
                            <div class="messages-block">
                                <div class="messages">
                                    <%
                                        AnnoDao annoDao = new AnnoDao();
                                        List<Announcement> annolist = annoDao.clubannoList(assoDao.searchAssoById(id));
                                        for(int i=0;i<annolist.size();i++){
                                    %>
                                    <a href="#" class="message d-flex align-items-center">
                                        <div class="content">
                                            <strong class="d-block"><%=annolist.get(i).gettitle()%></strong>
                                            <span class="d-block"><%=annolist.get(i).getAnnoContent()%></span>
                                            <small class="date d-block"><%=annolist.get(i).getCreatetime()%></small>
                                        </div>
                                    </a>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
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
<script src="js/charts-custom.js"></script>
<script src="js/front.js"></script>
</body>
</html>
