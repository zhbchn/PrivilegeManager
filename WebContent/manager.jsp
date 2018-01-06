<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理页面(采用分帧技术)</title>
</head>
<frameset rows="22%,*">
    <frame name="head" src="${pageContext.request.contextPath }/security/head.jsp">
    <frameset cols="15%,*">
        <frame name="left" src="${pageContext.request.contextPath }/security/left.jsp">
        <frame name="right" src="#">
    </frameset>
</frameset>
</html>