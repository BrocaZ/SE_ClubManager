<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/12/4
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="dao.*" %>
<%@ page import="entity.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>修改密码</title>
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
    <link rel="stylesheet" type="text/css" media="screen" href="https://cdn.staticfile.org/ionicons/2.0.1/css/ionicons.min.css">
<%--    <script>--%>
<%--        function check() {--%>
<%--            <%--%>
<%--                String oldpw=request.getParameter("oldpw");--%>
<%--               String pw=request.getParameter("stupw");--%>
<%--               String pw1=request.getParameter("stupw1");--%>
<%--               if(pw1.equals("")&&!pw.equals("")||pw.equals("")&&!pw1.equals("")) {--%>
<%--            %>--%>
<%--            alert("确认密码或新密码不能为空");--%>
<%--            return false;--%>
<%--            <%} else if(!pw1.equals("")&&!pw.equals("")&&pw1.compareTo(pw)!=0) {%>--%>
<%--            alert("两次输入的密码不一致！");--%>
<%--            return false;--%>
<%--            <%} else if(oldpw.equals("")&&(!pw1.equals("")||!pw.equals(""))){%>--%>
<%--            alert("旧密码不能为空！");--%>
<%--            return false;--%>
<%--            <%}%>--%>
<%--            return true;--%>
<%--        }--%>
<%--    </script>--%>
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
                <!-- Navbar Header--><a href="" class="navbar-brand">
                <div class="brand-text brand-big visible text-uppercase"><strong class="text-primary">ZUCC</strong><strong>SOCIETY</strong></div>
                <div class="brand-text brand-sm"><strong class="text-primary">Z</strong><strong>S</strong></div></a>
                <!-- Sidebar Toggle Btn-->
                <button class="sidebar-toggle"><i class="fa fa-long-arrow-left"></i></button>
            </div>
            <div class="right-menu list-inline no-margin-bottom">
                <div class="list-inline-item logout"><a id="logout" href="${pageContext.request.contextPath }/index.jsp" class="nav-link"> <span class="d-none d-sm-inline">退出 </span><i class="icon-logout"></i></a></div>
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
                if (name != null) {
            %>
            <div class="avatar">
                <img src="${pageContext.request.contextPath }/img/t7.jpg" alt="..." class="img-fluid rounded-circle">
            </div>
            <div class="title">
                <h1 class="h5"><a href="modPassword.jsp"><%=name %></a></h1>
            </div>
            <%} %>
        </div>
        <ul class="list-unstyled">
            <li class="active"><a href="approve.jsp"> <i class="icon-home"></i>社团审批列表 </a></li>
            <li><a href="checkActList.jsp"> <i class="icon-list"></i>活动审批列表 </a></li>
            <li><a href="assoCheck.jsp"> <i class="icon-grid"></i>社团列表</a></li>
            <li><a href="place.jsp"> <i class="icon-windows"></i>场地列表</a></li>
            <li><a href="resetPassword.jsp"> <i class="icon-user"></i>密码重置</a></li>
        </ul>
    </nav>
    <!-- Sidebar Navigation end-->
    <div class="page-content">
        <div class="page-header">
            <div class="container-fluid">
                <h2 class="h5 no-margin-bottom">修改个人信息</h2>
            </div>
        </div>
        <div class="apply" style="padding-left: 30%;">
            <div class="apply1">
                <label class="apply-control-label">头像</label>
                <img src="${pageContext.request.contextPath }/img/t7.jpg" alt="..." style="width:100px;height:100px;">
            </div>
            <form action="${pageContext.request.contextPath}/doModPassword" method="post">
                <div class="apply1">
                    <label class="apply-control-label" id="apply-control5">旧密码</label>
                    <input type="text" class="apply-control" name="oldpw">
                </div>
                <div class="apply1">
                    <label class="apply-control-label" id="apply-control3">新密码</label>
                    <input type="text" class="apply-control" name="stupw">
                </div>
                <div class="apply1">
                    <label class="apply-control-label" id="apply-control4">密码确认</label>
                    <input type="text" class="apply-control" name="stupw1">
                </div>
                <div class="apply-submit"style="margin-left: 24%;">
                    <button  type="submit" style="background-color: #ff6574;border-radius:5px; border: none; width: 100px;"><i class="icon ion-checkmark-round" style="font-size: 25px; color: #F5F5F5;"></i></button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- JavaScript files-->
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/vendor/popper.js/umd/popper.min.js"> </script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/4.2.1/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/vendor/jquery.cookie/jquery.cookie.js"> </script>
<script src="https://cdn.bootcss.com/Chart.js/2.7.3/Chart.min.js"></script>
<script src="${pageContext.request.contextPath }/vendor/jquery-validation/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath }/js/charts-home.js"></script>
<script src="${pageContext.request.contextPath }/js/front.js"></script>
</body>

</html>

