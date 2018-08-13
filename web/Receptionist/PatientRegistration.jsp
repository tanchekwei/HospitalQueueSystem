<%-- 
    Document   : PatientRegistration
    Created on : Aug 10, 2018, 8:53:49 PM
    Author     : JT
--%>

<%@page import="ADT.*"%>
<%@page import="model.Patients"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <link rel="stylesheet" href="../css/hover-type-button.css">
        <link rel="stylesheet" href="../css/hover-button.css">
        <link rel="stylesheet" href="../css/hover-table.css">
        <link rel="stylesheet" href="../css/form-style.css">
        <link rel="stylesheet" href="../css/main.css">
    </head>
    <body>
        <!-- Initialize here -->
        <%
            pageContext.setAttribute("nextID", 0);
        %>
        <sql:setDataSource var = "snapshot" driver = "org.apache.derby.jdbc.ClientDriver"
                           url = "jdbc:derby://localhost:1527/hospitalDB"
                           user = "nbuser"  password = "nbuser"/>

        <sql:query dataSource = "${snapshot}" var = "result">
            SELECT * from Patients
        </sql:query>
        <c:forEach var="row" items="${result.rows}">
            <c:set var="nextID" value="${row.patientid}"/>
        </c:forEach>
        <%
            int nextID = (Integer) pageContext.getAttribute("nextID");
            if(nextID != 0) {
                nextID++;
            }
            pageContext.setAttribute("nextID", nextID);
        %>
        <form class="custom_form" method="post" action="../PatientRegistration">
            <table>
                <caption style="font-size: 20px; border-bottom: 2px solid black;"><b>Patient Registration Form</b></caption>
                <tbody>
                    <tr>
                        <td><label>ID</label></td>
                        <td><input type="text" name="id" value="${nextID}" required="required" readonly/></td>
                    </tr>
                    <tr>
                        <td><label>Name</label></td>
                        <td><input type="text" name="name" pattern="[A-Za-z ]{1,50}" maxlength="50" required="required" placeholder="Augusto"/></td>
                    </tr>
                    <tr>
                        <td><label>IC Number</label></td>
                        <td><input type="text" name="ic" pattern="[0-9]{12,12}" title="Please enter in this format. eg. 901231541234" required="required" placeholder="980123561234"/></td>
                    </tr>
                    <%
                        Boolean invalidIC = (Boolean) pageContext.getSession().getAttribute("invalidIC");
                        if(invalidIC != null && invalidIC == true) {
                            out.print("<tr><td></td><td><label style=\"color:red\">* IC number existed.</label></td></tr>");
                        }
                        pageContext.getSession().setAttribute("invalidIC", false);
                    %>
                    <tr>
                        <td><label>Gender</label></td>
                        <td><input type="radio" name="gender" value="M" checked="checked"/>Male<input type="radio" name="gender" value="F"/>Female</td>
                    </tr>
                    <tr>
                        <td><label>Contact Number</label></td>
                        <td><input type="text" name="telno" pattern="[0-9]{9,}" title="Please enter in this format. eg. 0123456789" required="required" placeholder="01123118900"/></td>
                    </tr>
                    <tr>
                        <td valign="top"><label>Address</label></td>
                        <td><input type="text" name="addr1" required="required"/><br>
                            <input type="text" name="addr2" required="required"/><br>
                            <input type="text" name="addr3" required="required"/><br>
                            <input type="text" name="addr4" required="required"/></td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="2"><input class="hover-type-button" type="submit" value="+ Register New Patient"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input class="hover-type-button" type="reset" value="↻ Reset"/></td>
                    </tr>
                </tfoot>
            </table>
        </form>
    </body>
</html>
