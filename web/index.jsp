<%@ page import="en.edu.whu.entity.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: wuwenan
  Date: 11/05/2017
  Time: 5:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
当前在线用户人数：${userNumber}<br/>
<%
    //展示用户列表
    ArrayList<User> userList = (ArrayList<User>) request.getServletContext().getAttribute("userList");
    for (int i = 0; i < userList.size(); i++) {
        User user = userList.get(i);
%>
IP:<%=user.getIpString()%>,FirstTime:<%=user.getFirstTimeString()%>,SessionId:<%=user.getSessionIdString()%> <br/>
<%
    }
%>
</body>
</html>
