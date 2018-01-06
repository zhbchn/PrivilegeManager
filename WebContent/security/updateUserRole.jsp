<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新用户的角色</title>
</head>
<body>
    <table border="1" width="40%">
        <tr>
            <td>用户名</td>
            <td>${user.username }</td>
        </tr>
        <tr>
            <td>用户描述</td>
            <td>${user.description }</td>
        </tr>
        <tr>
            <td>用户原有角色</td>
            <td>
                <c:forEach var="role" items="${user.roles }">
                    ${role.name }<br/>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>须授予的角色</td>
            <td>
                <!-- 当下面表单提交时，会给服务器带去用户id和要授予的角色id  -->
                <form action="${pageContext.request.contextPath }/UserServlet?method=updateRole" method="post">
                    <input type="hidden" name="userid" value="${user.id }">
                    <c:forEach var="r" items="${list }">
                        <input type="checkbox" name="rid" value="${r.id }">${r.name }<br/>
                    </c:forEach>
                    <input type="submit" value="更新角色">
                </form>
            </td>
        </tr>

    </table>
</body>
</html>