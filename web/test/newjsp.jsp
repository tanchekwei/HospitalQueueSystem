<%-- 
    Document   : newjsp
    Created on : Aug 10, 2018, 6:28:20 PM
    Author     : Tan Chek Wei
--%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <sql:setDataSource var = "snapshot" driver = "org.apache.derby.jdbc.ClientDriver"
                           url = "jdbc:derby://localhost:1527/hospitalDB"
                           user = "nbuser"  password = "nbuser"/>

        <sql:query dataSource = "${snapshot}" var = "result">
            SELECT * from Doctors
        </sql:query>
        <table>
            <c:forEach var = "row" items = "${result.rows}">
                <tr>
                    <td> <c:out value = "${row.doctorID}"/></td>
                    <td> <c:out value = "${row.doctorName}"/></td>
                    <td> <c:out value = "${row.doctorIC}"/></td>
                    <td> <c:out value = "${row.doctorGender}"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
