<%--
  Created by IntelliJ IDEA.
  User: ANARKHWQH
  Date: 2019/11/30
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.AssoDao" %>
<%@ page import="dao.StuDao"%>
<%@ page import="dao.AnnoDao" %>
<%@ page import="dao.PlaceDao" %>
<%@ page import="entity.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>修改社团信息</title>
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
    <link rel="stylesheet" type="text/css" media="screen" href="https://cdn.staticfile.org/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="img/favicon.ico">
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
    </script>
</head>

<body>
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
                <h1 class="h5"><a href="modifyInfo.jsp"><%=name %></a>
                </h1>
            </div>
            <%} %>
        </div>
        <ul class="list-unstyled">
            <li ><a href="actAnno.jsp"> <i class="icon-home"></i>活动公告 </a></li>
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
                <ul  class="collapse1 list-unstyled ">
                    <li>
                        <a href="societyact-leader.jsp">活动列表</a>
                    </li>
                    <li>
                        <a href="societyanno-leader.jsp">公告列表</a>
                    </li>
                    <li>
                        <a href="addstu-leader.jsp?check=0">添加社员</a>
                    </li>
                    <li class="active">
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
                <h2 class="h5 no-margin-bottom">修改社团信息</h2>
            </div>
        </div>
        <div class="apply" style="padding-top: 100px; padding-bottom: 100px;">
            <%
                int assoid=assoDao.getCurAssoId();
                String path="img/"+assoid+".jpg";
            %>
            <form  action="${pageContext.request.contextPath}/doChaLea" method="post" name="f" onsubmit="return check()" style="text-align: center">
                <div class="apply1">
                    <label class="apply-control-label">社团logo</label>
                    <img src="<%=path%>" alt="..." style="width:100px;height:100px;">
                </div>
                <%
                    PlaceDao placeDao=new PlaceDao();
                   Association asso=assoDao.searchAssoById(assoid);
                   String assoname=asso.getAssociationName();
                   int plaid=asso.getPlacId();
                   String planame=placeDao.searchPlaceById(plaid).getPlaceName();
                   String brief=asso.getIntro();
                   String chief=asso.getChiefSno();
                   String chiefname=chief+stuDao.getName(chief);
                %>
                <div class="apply1">
                    <label class="apply-control-label" id="apply-control2">社团名称</label>
                    <input type="text" class="apply-control" value="<%=assoname%>" name="assoname">
                </div>
                <div class="apply1">
                    <label class="apply-control-label" id="apply-control3">社团场地</label>
                    <input type="text" class="apply-control" list="placelist1"  name="assopla" value="<%=planame%>">
                    <datalist id="placelist1">
                        <%
                            List<Place> places=placeDao.placeList();
                            for(int i=0;i<places.size();i++)
                            {
                                String planame1=places.get(i).getPlaceName();


                        %>
                        <option><%=planame1%>
                        </option>
                        <%}%>
                    </datalist>
                </div>
                <div class="apply1">
                    <label class="apply-control-label" id="apply-control4">社团简介</label>
                    <input type="text" class="apply-control" value="<%=brief%>" name="assobrief">
                </div>
                <div class="apply1">
                    <label class="apply-control-label">社长</label>
                    <input type="text" class="apply-control" list="placelist" name="assochief" value="<%=chiefname%>">
                    <datalist id="placelist">
                        <%
                            List<Student> res = assoDao.assoMemberList(assoid);
                            for (int i = 0; i < res.size(); i++) {
                                String stu = res.get(i).getSno()+" "+res.get(i).getName();
                        %>
                        <option><%=stu%></option>
                        <%}%>
                    </datalist>
                </div>
                <div class="apply-submit" >
                    <button type="submit" style="background-color: #ff6574;border-radius:5px; border: none; width: 100px;"><i class="icon ion-checkmark-round" style="font-size: 25px; color: #F5F5F5;"></i></button>
                </div>
            </form>
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
    <script src="js/charts-home.js"></script>
    <script src="js/front.js"></script>
</body>

</html>