<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户界面</title>
</head>
<body>
    <form action="${pageContext.request.contextPath }/UserServlet?method=add" method="post">
        <table>
            <tr>
                <td>用户名</td>
                <td>
                    <input type="text" name="username">
                </td>
            </tr>
            <tr>
                <td>用户密码</td>
                <td>
                    <input type="text" name="password">
                </td>
            </tr>
            <tr>
                <td>用户描述</td>
                <td>
                    <textarea rows="5" cols="50" name="description"></textarea>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input type="submit" value="添加用户">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>