<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%
    request.setAttribute("title", "Project Tasks");
    request.setAttribute("contentPage", "../content/tasks.jsp"); 
%>

<jsp:include page="/views/layout/layout.jsp" />
