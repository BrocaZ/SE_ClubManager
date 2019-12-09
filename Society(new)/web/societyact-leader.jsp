<%--
  Created by IntelliJ IDEA.
  User: ANARKHWQH
  Date: 2019/11/30
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.StuDao" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.AssoDao" %>
<%@ page import="entity.Association" %>
<%@ page import="dao.ActDao" %>
<%@ page import="entity.Activity" %>
<%@ page import="entity.Message" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>活动列表</title>
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
    <!-- Favicon-->
    <link rel="shortcut icon" href="img/favicon.ico">
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
    <script type="text/javascript">
        function check(){
            var mymessage=confirm("确定要取消该活动吗？");
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
<%
    Object message = session.getAttribute("message");
    if(message!=null && !"".equals(message)){
%>
<script type="text/javascript">
    alert("<%=message%>");
</script>
<%  session.setAttribute("message",null);
}%>
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
            <li><a href="actAnno.jsp"> <i class="icon-home"></i>活动公告 </a></li>
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
                    <li class="active">
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
            <%
                }
            %>
            <!--社长-->
        </ul>
    </nav>
    <!-- Sidebar Navigation end-->
    <div class="page-content">
        <div class="page-header">
            <div class="container-fluid">
                <h2 class="h5 no-margin-bottom">活动列表</h2>
            </div>
        </div>
        <div class="messages-block block" style="margin-top: 70px; margin-left: 100px;margin-right: 100px;">
            <div id="apply" style="margin-left: 86.5%; background-color: white;">
                <a href="addact.jsp?actid=0" style="font-size: 18px; font-weight: 600;">申请活动</a >
            </div>
            <div class="messages" style="padding-left: 20px; padding-top: 15px;padding-right: 20px;padding-bottom: 15px;">
                <a class="message d-flex align-items-center " >
                    <div class="content" style="width: 64%"> <strong class="d-block">内部公告</strong><span class="d-block">社内例会、通知</span></div>
                    <object><a href="post-leader.jsp?id=0&flat=no" title="添加公告"><i class="icon ion-plus-round" style="font-size: 25px; color: gray; padding-left: 214px"></i></a ></object>
                </a >
                <%
                    ActDao actDao=new ActDao();
                    List<Activity> acts=actDao.listActInAsso();
                    Association asso=new Association();
                    for (int i = 0; i < acts.size(); i++) {
                        int id=acts.get(i).getActivityId();
                        int assoId=acts.get(i).getAssociationId();
                        asso=assoDao.searchAssoById(assoId);
                        String assoName=asso.getAssociationName();
                        String theme=acts.get(i).getActtheme();
                        String status="待审核";
                        if(acts.get(i).getStatus().equals("ok"))
                            status="审核通过";
                        else if(acts.get(i).getStatus().equals("del"))
                            status="审核未通过";
                        String time=acts.get(i).getRemarks();
                %>
                <a href="addact.jsp?actid=<%=id%>" class="message d-flex align-items-center " title="点击可修改活动信息">
                    <div class="content" style="width: 62%"> <strong class="d-block"><%=theme%></strong><span class="d-block"><%=assoName%></span></div>
                    <div style="width: 10%"><span class="d-block"><%=status%></span></div>
                    <div  style="width: 13%"><span class="d-block"><%=time%></span></div>
                    <form>
                        <object><a href="post-leader.jsp?id=<%=id%>&flat=yes" title="添加公告"><i class="icon ion-plus-round" style="font-size: 25px; color: gray;"></i></a></object>
                        <object><a href="actperson-leader.jsp?id=<%=id%>" title="查看报名活动的学生"><i class="icon ion-navicon-round" style="font-size: 28px;margin-left: 18px ;color: gray;"></i></a></object>
                    </form>
                    <form action="${pageContext.request.contextPath}/doDelAct?id=<%=id%>" method="post" onclick="return check()">
                        <button type="submit" style="background-color: rgba(0,0,0,0);border: none" title="点击删除"><i class="icon ion-close-round"  style="font-size: 23px; margin-left: 15px; color: gray;"></i></button>
                    </form>
                </a>
               <%}%>
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