<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/zhb" prefix="zhb" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限管理系统</title>
</head>
<body>

    欢迎您：${user.username }	<a href="${pageContext.request.contextPath }/UserServlet?method=logout">注销</a>
    <br/><br/>
    <a href="${pageContext.request.contextPath }/login.jsp">登录</a>
    <br/><br/>

<!-- <br/><br/>
    <a href="/day20/manager/Servlet1">添加分类</a>
    <a href="/day20/manager/Servlet2">删除分类</a>
    <a href="/day20/manager/Servlet3">修改分类</a>
    <a href="/day20/manager/Servlet4">查找分类</a> -->
    
    <a href="./manager.jsp">管理界面</a>
    <zhb:permission value="添加分类"> <!-- 标签控制用户需要有添加分类的权限值，才可以看到超链接 -->
        <a href="/day20/manager/Servlet1">添加分类</a>
    </zhb:permission>

    <zhb:permission value="删除分类">
        <a href="/day20/manager/Servlet2">删除分类</a>
    </zhb:permission>

    <zhb:permission value="修改分类">
        <a href="/day20/manager/Servlet3">修改分类</a>
    </zhb:permission>

    <zhb:permission value="查找分类">
        <a href="/day20/manager/Servlet4">查找分类</a>
    </zhb:permission>

    <zhb:permission value="删除商品">
        <a href="/day20/manager/Servlet5">删除商品</a>
    </zhb:permission>
</body>
</html>