<%@ page contentType="text/html; charset=utf-8" %><%@ page language="java" import="java.sql.*,java.io.*,java.lang.*,java.util.*" %><%
String path = request.getContextPath();
String rand=(String)session.getAttribute("rand")==null?"":(String)session.getAttribute("rand");
out.print(rand);
%>