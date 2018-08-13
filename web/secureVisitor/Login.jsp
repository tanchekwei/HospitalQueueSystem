<%-- 
    Document   : Login
    Created on : Dec 2, 2017, 1:56:36 PM
    Author     : Tan Chek Wei
--%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%String path = (String) request.getContextPath();%>
    <head>
        <link rel="stylesheet" href="../css/hover-type-button.css">
        <link rel="stylesheet" href="../css/hover-button.css">
        <link rel="stylesheet" href="../css/hover-table.css">
        <link rel="stylesheet" href="../css/main.css">
        <style>
            #mydiv {
                margin: auto;
                position: relative;
                text-align: center;
                top: 50%;
                width: 20%;  
            }
        </style>
    </head>
    <body>

        <div id="mydiv">
            <%
                if (session.getAttribute("isValid") != null) {
            %>
            <span style="color: red;">Invalid credential</span>

            <%
                }
            %>
            <h2>Login page</h2>
            <form class="login-form" method="post" action="<%=path%>/Login">
                <table id="hover-table">
                    <tr>
                        <td>Username:</td>
                        <td><input name="username" type="text" placeholder="email / phone number" required=""/></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input name="password" type="password" placeholder="password" required=""/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><button type="submit" style="display: block; margin: auto;" class="hover-type-button">login</button></td>
                    </tr>
                </table>
            </form>
        </div>
</html>
