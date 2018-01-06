<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/zhb" prefix="zhb" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左侧导航栏</title>
</head>
<body>
    <br/><br/>
    <a href="${pageContext.request.contextPath }/PrivilegeServlet?method=getAll" target="right">权限管理</a>
    <br/><br/>
    <a href="${pageContext.request.contextPath }/ResourceServlet?method=getAll" target="right">资源管理</a>
    <br/><br/>
    <a href="${pageContext.request.contextPath }/RoleServlet?method=getAll" target="right">角色管理</a>
    <br/><br/>
   <%--  <zhb:permission value="用户管理"> <!-- 标签控制用户需要有添加分类的权限值，才可以看到超链接 --> --%>
        <a href="${pageContext.request.contextPath }/UserServlet?method=getAll" target="right">用户管理</a>
   <%--  </zhb:permission> --%>
   
    <br/><br/>
</body>
</html>