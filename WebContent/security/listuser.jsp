<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>
    <br/><br/>
    <table width="80%" align="center">
        <tr>
            <td></td>
            <td></td>
            <td align="right">
                <a href="${pageContext.request.contextPath }/UserServlet?method=addUI">添加用户</a>
            </td>
        </tr>
    </table>
    <br/>
    <table width="80%" border="1" align="center">
        <tr>
            <td>用户名称</td>
            <td>用户密码</td> <!-- 后台管理员可以看到用户的密码，这个倒无所谓 -->
            <td>用户描述</td>
            <td>操作</td>
        </tr>
        <c:forEach var="user" items="${list }">
            <tr>
                <td>${user.username }</td>
                <td>${user.password }</td>
                <td>${user.description }</td>
                <td>
                    <a href="${pageContext.request.contextPath }/UserServlet?method=forUpdateUserRoleUI&id=${user.id }">为用户授予角色</a>
                    <a href="#">删除</a>
                    <a href="#">修改</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>