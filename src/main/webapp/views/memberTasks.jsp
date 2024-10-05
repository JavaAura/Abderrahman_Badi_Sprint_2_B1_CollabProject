<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%
    request.setAttribute("title", "Member Tasks");
    request.setAttribute("contentPage", "/views/content/memberTasks.jsp"); 
%>

<jsp:include page="/views/layout/layout.jsp" />
