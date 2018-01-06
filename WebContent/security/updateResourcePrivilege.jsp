<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新资源的权限界面</title>
</head>
<body>
    <table border="1" width="40%">
        <tr>
            <td>资源URI</td>
            <td>${resource.uri }</td>
        </tr>
        <tr>
            <td>资源描述</td>
            <td>${resource.description }</td>
        </tr>
        <tr>
            <td>资源原有权限</td>
            <td>${resource.privilege.name }</td>
        </tr>
        <tr>
            <td>须授予的权限</td>
            <td>
                <!-- 当下面表单提交时，会给服务器带去资源id和要授予的权限id  -->
                <form action="${pageContext.request.contextPath }/ResourceServlet?method=updatePrivilege" method="post">
                    <input type="hidden" name="rid" value="${resource.id }">
                    <c:forEach var="p" items="${list }">
                        <input type="radio" name="pid" value="${p.id }">${p.name }<br/>
                    </c:forEach>
                    <input type="submit" value="更新权限">
                </form>
            </td>
        </tr>

    </table>
</body>
</html>