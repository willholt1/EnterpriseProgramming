<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.GsonBuilder" %>

<%
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	String jsonResult = gson.toJson(request.getAttribute("films"));
	out.println(jsonResult);
%>