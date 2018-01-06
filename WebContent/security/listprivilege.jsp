<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限列表</title>
</head>
<body style="text-align: center;">
    <br/><br/>
    <table width="60%" align="center">
        <tr>
            <td></td>
            <td></td>
            <td align="right">
                <a href="${pageContext.request.contextPath }/PrivilegeServlet?method=addUI">添加权限</a>
            </td>
        </tr>
    </table>
    <br/>
    <table width="60%" border="1" align="center">
        <tr>
            <td>权限名称</td>
            <td>权限描述</td>
            <td>操作</td>
        </tr>
        <c:forEach var="p" items="${list }">
            <tr>
                <td>${p.name }</td>
                <td>${p.description }</td>
                <td>
                    <a href="#">删除</a>
                    <a href="#">修改</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>