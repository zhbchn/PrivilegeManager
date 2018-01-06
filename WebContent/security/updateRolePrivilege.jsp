<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新角色的权限</title>
</head>
<body>
    <table border="1" width="40%">
        <tr>
            <td>角色名称</td>
            <td>${role.name }</td>
        </tr>
        <tr>
            <td>角色描述</td>
            <td>${role.description }</td>
        </tr>
        <tr>
            <td>角色原有权限</td>
            <td>
                <c:forEach var="p" items="${role.privileges }">
                    ${p.name }<br/>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>须授予的权限</td>
            <td>
                <!-- 当下面表单提交时，会给服务器带去角色id和要授予的权限id  -->
                <form action="${pageContext.request.contextPath }/RoleServlet?method=updatePrivilege" method="post">
                    <input type="hidden" name="roleid" value="${role.id }">
                    <c:forEach var="p" items="${list }">
                        <input type="checkbox" name="pid" value="${p.id }">${p.name }<br/>
                    </c:forEach>
                    <input type="submit" value="更新权限">
                </form>
            </td>
        </tr>

    </table>
</body>
</html>