<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色列表</title>
</head>
<body>
    <br/><br/>
    <table width="80%" align="center">
        <tr>
            <td></td>
            <td></td>
            <td align="right">
                <a href="${pageContext.request.contextPath }/RoleServlet?method=addUI">添加角色</a>
            </td>
        </tr>
    </table>
    <br/>
    <table width="80%" border="1" align="center">
        <tr>
            <td>角色名称</td>
            <td>角色描述</td>
            <td>操作</td>
        </tr>
        <c:forEach var="role" items="${list }">
            <tr>
                <td>${role.name }</td>
                <td>${role.description }</td>
                <td>
                    <a href="${pageContext.request.contextPath }/RoleServlet?method=forUpdateRolePrivilegeUI&id=${role.id }">为角色授予权限</a>
                    <a href="#">删除</a>
                    <a href="#">修改</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>