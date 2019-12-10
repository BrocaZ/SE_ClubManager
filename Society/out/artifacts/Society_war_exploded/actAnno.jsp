<%@ page import="dao.StuDao" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.AssoDao" %>
<%@ page import="dao.AnnoDao" %>
<%@ page import="exception.BaseException" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="entity.*" %><%--
  Created by IntelliJ IDEA.
  User: zky
  Date: 2019/11/24
  Time: 1:40 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>活动公告</title>
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
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
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
            color: #b0b0b0;
            /*border-color: #2d3035;*/
            background-image: url(img/sousuo.png);
            background-size: 25px 25px;
            background-position: 11px 13px;
            background-repeat: no-repeat;
            border-top-right-radius: 15px;
            border-bottom-right-radius: 15px;
            border-left: 0px;
        }

        .message :hover {
            color: white;
        }

        .mycol-lg-6 {
            flex: 0 0 65%;
            max-width: 65%;
        }

        .mycol-lg-7 {
            flex: 0 0 34%;
            max-width: 34%;
        }
    </style>
</head>
<body>
<%
    Object message = session.getAttribute("message");
    if (message != null && !"".equals(message)) {
%>
<script type="text/javascript">
    alert("<%=message%>");
</script>
<% session.setAttribute("message", null);
}%>
<header class="header">
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid d-flex align-items-center justify-content-between">
            <div class="navbar-header">
                <!-- Navbar Header--><a href="actAnno.jsp" class="navbar-brand">
                <div class="brand-text brand-big visible text-uppercase"><strong
                        class="text-primary">ZUCC</strong><strong>SOCIETY</strong></div>
                <div class="brand-text brand-sm"><strong class="text-primary">Z</strong><strong>S</strong></div>
            </a>
                <!-- Sidebar Toggle Btn-->
                <button class="sidebar-toggle"><i class="fa fa-long-arrow-left"></i></button>
            </div>
            <div class="right-menu list-inline no-margin-bottom">
                <div class="list-inline-item dropdown"><a id="navbarDropdownMenuLink1" href="#" data-toggle="dropdown"
                                                          aria-haspopup="true" aria-expanded="false"
                                                          class="nav-link messages-toggle"><i
                        class="icon-email"></i></a>
                    <div aria-labelledby="navbarDropdownMenuLink1" class="dropdown-menu messages">
                        <%
                            StuDao stuDao = new StuDao();
                            String sno = stuDao.getCurID();
                            List<Message> result = stuDao.messageInStu(sno);
                            for (int i = 0; i < result.size() && i <= 4; i++) {
                                Message m = result.get(i);
                                String sendsno = m.getSendsno();
                                String sendname = stuDao.findStu(sendsno).getName();
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                String date = formatter.format(m.getSenddate());

                        %>
                        <a href="#" class="dropdown-item message d-flex align-items-center">
                            <div class="profile"><img src="img/t1.jpg" alt="..." class="img-fluid"></div>
                            <div class="content"><strong class="d-block"><%=sendname%>></strong><span
                                    class="d-block"><%=m.getContent()%></span><small class="date d-block"><%=date%>
                            </small></div>
                        </a>
                        <%
                            }
                        %>
                        <a href="message.jsp" class="dropdown-item text-center message">
                            <strong>See All Messages <i class="fa fa-angle-right"></i></strong></a>
                    </div>
                </div>
                <!-- Log out               -->
                <div class="list-inline-item logout"><a id="logout" href="index.jsp" class="nav-link"> <span
                        class="d-none d-sm-inline">退出 </span><i class="icon-logout"></i></a></div>
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
            <li class="active"><a href="actAnno.jsp"> <i class="icon-home"></i>活动公告 </a></li>
            <li><a href="#exampledropdownDropdown" aria-expanded="false" data-toggle="collapse"> <i
                    class="icon-windows"></i>我的社团 </a>
                <ul id="exampledropdownDropdown" class="collapse list-unstyled ">
                    <%
                        AssoDao assoDao = new AssoDao();
                        List<Integer> list = assoDao.assoPersonList(stuDao.getCurID());
                        for (int i = 0; i < list.size(); i++) {
                            int id = list.get(i);
                    %>
                    <li><a href="society.jsp?assoid=<%=id%>"><%=assoDao.searchAssoById(id).getAssociationName()%>
                    </a></li>
                    <%}%>
                </ul>
            </li>
            <li><a href="overview.jsp"> <i class="icon-grid"></i>社团总览</a></li>
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
    <div class="page-content">
        <div class="page-header">
            <div class="container-fluid">
                <h2 class="h5 no-margin-bottom">活动公告</h2>
            </div>
        </div>
        <div class="row">
            <form action="" id="research" style="text-align: center">
                <input type="text" name="keyword" autocomplete="off" placeholder="请输入社团名称"/>

                <button></button>
            </form>
        </div>

        <div class="row" style="margin-left: 20px;margin-right: 20px">
            <div class="mycol-lg-6">
                <div class="lin-chart  chart"> <div class="title"><strong>公告</strong></div>
                <div class="messages-block">

                    <div class="messages">
                        <%
                            String keyword = request.getParameter("keyword");
                            AnnoDao annoDao = new AnnoDao();
                            List<Announcement> annolist = null;
                            try {
                                annolist = annoDao.publicannoList(keyword);
                            } catch (BaseException e) {
                                e.printStackTrace();
                            }
                            for (int i = 0; i < annolist.size(); i++) {
                                int annoid = annolist.get(i).getAnnoucementId();
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                String creattime = formatter.format(annolist.get(i).getCreatetime());
                                int assoid = annolist.get(i).getAssociationId();
                                String path = "img/" + assoid + ".jpg";
                        %>
                        <a href="joinAct.jsp?annoid=<%=annoid%>&in=yes&special=no"
                           class="message d-flex align-items-center">
                            <div class="profile">
                                <img src="<%=path%>" alt="未设置" class="img-fluid">
                            </div>
                            <div class="content" style="width: 85%">
                                <strong class="d-block"><%=annolist.get(i).gettitle()%>
                                </strong>
                                <span class="d-block"><%=annolist.get(i).getAnnoContent()%></span>

                            </div>
                            <div>
                                <span class="date d-block"><%=creattime%></span>
                            </div>
                        </a>
                        <%}%>
                    </div></div>
                </div>
            </div>
            <div class="mycol-lg-7">
                <div class="lin-chart  chart">
                    <div class="title"><strong>我参与的活动</strong></div>
                    <div class="messages-block">
                        <div class="messages">
                            <%
                                List<Activity> re=stuDao.actOfStu(stuDao.getCurID());
                                for(int i=0;i<re.size();i++){
                                    Activity act=re.get(i);
                            %>
                            <a href="" class="message d-flex align-items-center">
                                <div class="content" style="width: 75%; margin-right: 3%">
                                    <strong class="d-block"><%=act.getActtheme()%></strong>
                                </div>
                                <div class="content" >
                                    <span class="d-block"><%=act.getStatus()%></span>
                                </div>
                            </a>
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- JavaScript files-->
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="vendor/popper.js/umd/popper.min.js"></script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/4.2.1/js/bootstrap.min.js"></script>
<script src="vendor/jquery.cookie/jquery.cookie.js"></script>
<script src="https://cdn.bootcss.com/Chart.js/2.7.3/Chart.min.js"></script>
<script src="vendor/jquery-validation/jquery.validate.min.js"></script>
<script src="js/charts-home.js"></script>
<script src="js/front.js"></script>
</body>
</html>
