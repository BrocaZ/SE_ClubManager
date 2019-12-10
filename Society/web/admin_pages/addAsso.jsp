<%@ page import="dao.StuDao" %>
<%@ page import="dao.PlaceDao" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Place" %><%--
  Created by IntelliJ IDEA.
  User: zky
  Date: 2019/11/30
  Time: 3:17 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>增加社团</title>
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
    <link rel="stylesheet" type="text/css" media="screen" href="https://cdn.staticfile.org/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/img/favicon.ico">
    <!-- Tweaks for older IEs-->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
    <script>
        function check() {
            for (var i = 0; i < document.f.elements.length - 1; i++) {
                if (document.f.elements[i].value == "") {
                    alert("当前表单不能有空项");
                    document.f.elements[i].focus();
                    return false;
                }
            }
            return true;
        }
        // function getObjectURL(file) {
        //     var url = null;
        //     if (window.createObjectURL != undefined) { // basic
        //         url = window.createObjectURL(file);
        //     } else if (window.URL != undefined) { // mozilla(firefox)
        //         url = window.URL.createObjectURL(file);
        //     } else if (window.webkitURL != undefined) { // webkit or chrome
        //         url = window.webkitURL.createObjectURL(file);
        //     }
        //     return url;
        // }
        // window.getObjectURL = getObjectURL;
    </script>
    <style type="text/css">
        #preview img
        {
            width: 100%;
            height: 100%;
        }
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
            <div class="avatar">
                <img src="${pageContext.request.contextPath }/img/t7.jpg" alt="..." class="img-fluid rounded-circle">
            </div>
            <div class="title">
                <h1 class="h5"><a href="modPassword.jsp"><%=name %></a></h1>
            </div>
            <%}%>
        </div>
        <!-- Sidebar Navidation Menus-->
        <!--          <span class="heading">Main</span>-->
        <ul class="list-unstyled">
            <li>
                <a href="approve.jsp"> <i class="icon-home"></i>社团审批列表 </a>
            </li>
            <li>
                <a href="checkActList.jsp"> <i class="icon-list"></i>活动审批列表 </a>
            </li>
            <li class="active">
                <a href="assoCheck.jsp"> <i class="icon-grid"></i>社团列表</a>
            </li>
            <li>
                <a href="place.jsp"> <i class="icon-windows"></i>场地列表</a>
            </li>
            <li>
                <a href="resetPassword.jsp"> <i class="icon-user"></i>密码重置</a>
            </li>
        </ul>
    </nav>
    <!-- Sidebar Navigation end-->
    <div class="page-content">
        <div class="page-header">
            <div class="container-fluid">
                <h2 class="h5 no-margin-bottom">增加社团</h2>
                <!--        <div class="list-inline-item"><a href="#" class="search-open nav-link"><i class="icon-magnifying-glass-browser"></i></a></div>-->
            </div>
        </div>
        <div class="apply">
            <form action="${pageContext.request.contextPath}/addAsso" method="post" name="f" onsubmit="return check()">
                <div class="apply1">
                    <label class="apply-control-label">社团名称</label>
                    <input type="text" class="apply-control" id="apply-control1" name="assoName">
                </div>
                <div class="apply1">
                    <label class="apply-control-label">场地</label>
                    <input type="text" class="apply-control" list="placelist" name="place">
                    <datalist id="placelist">
                        <%
                            PlaceDao placeDao = new PlaceDao();
                            List<Place> placeList = placeDao.avaplaceList();
                            for(int i=0;i<placeList.size();i++){
                        %>
                        <option><%=placeList.get(i).getPlaceName()%></option>
                        <%}%>
                    </datalist>
                </div>
                <div class="apply1">
                    <label class="apply-control-label">社长学号</label>
                    <input type="text" class="apply-control" name="sno">
                </div>
                <div class="apply1">
                    <label class="apply-control-label" id="apply-control2">社团简介</label>
                    <textarea name="content" cols="50" rows="10" style="vertical-align:top"></textarea>
                </div>
                <div class="apply1">
                    <label class="apply-control-label">社团logo</label>
                    <input type="file" id="input" name="logo">
<%--                    <input class="weui_uploader_input" name='logo' type="file" accept="image/*" multiple />--%>
<%--                    <div id="preview" style="width: 100%; height: 100%"></div>--%>
                </div>
                <div class="apply-submit" style="text-align: center">
                    <button type="submit" style="background-color: #ff6574;border-radius:5px; border: none; width: 100px;"><i class="icon ion-checkmark-round" style="font-size: 25px; color: #F5F5F5;"></i></button>
                </div>
            </form>
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
