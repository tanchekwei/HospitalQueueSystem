<%-- 
    Document   : DisplayDoctors
    Created on : Aug 10, 2018, 7:25:41 PM
    Author     : Tan Chek Wei
--%>
<%
    String path = request.getContextPath();
%>
<%@page import="model.Timeclock"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="ADT.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sql:setDataSource var = "snapshot" driver = "org.apache.derby.jdbc.ClientDriver"
                   url = "jdbc:derby://localhost:1527/hospitalDB"
                   user = "nbuser"  password = "nbuser"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../css/hover-type-button.css">
        <link rel="stylesheet" href="../css/hover-button.css">
        <link rel="stylesheet" href="../css/hover-table.css">
        <link rel="stylesheet" href="../css/main.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">JY Hospital</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="<%=path%>/DisplayDoctors">Home</a></li>
                    <li><a href="#">Add Doctor</a></li>
                    <li><a href="#">Update Doctor</a></li>
                </ul>
            </div>
        </nav>




        <table id="hover-table" width="100%">
            <thead>
                <tr>
                    <th width="5%">Doctor ID</th>
                    <th width="20%">Doctor name</th>
                    <th width="15%">Doctor type</th>
                    <th width="15%">Doctor Contact</th>
                    <th width="15%">Time</th>
                    <th width="15%">Type</th>
                </tr>
            </thead>
            <%
                int idNow = 0;
                String elapsedMinutesStr = "", elapsedHoursStr = "";
                long elapsedHours = 0, elapsedMinutes = 0;
                int counter = 1;
                ArrayList<Timeclock> timeClock = (ArrayList) session.getAttribute("timeClock");
                for (int i = timeClock.getNumberOfEntries() - 1; i >= 0; i--) {
                    SimpleDateFormat df = new SimpleDateFormat("h:mm a");
                    Calendar cal = timeClock.getEntry(i).getTimeclocktime();
                    int hours = 0;
                    int minutes = 0;
                    if (i - 1 > 0) {

                        Calendar calNext = timeClock.getEntry(i - 1).getTimeclocktime();
                        long different = cal.getTimeInMillis() - calNext.getTimeInMillis();
                        long secondsInMilli = 1000;
                        long minutesInMilli = secondsInMilli * 60;
                        long hoursInMilli = minutesInMilli * 60;

                        elapsedHours = different / hoursInMilli;
                        different = different % hoursInMilli;

                        elapsedMinutes = different / minutesInMilli;
                        different = different % minutesInMilli;
                        elapsedMinutesStr = String.format("%02d", elapsedMinutes);
                        elapsedHoursStr = String.format("%01d", elapsedHours) + ":";
                    }
                    idNow = timeClock.getEntry(i).getStaffid().getStaffid();
                    pageContext.setAttribute("idNow", idNow);
                    System.out.println(idNow);
            %>

            <sql:query dataSource = "${snapshot}" var = "result">
                SELECT * from Staffs WHERE staffID = ${idNow}
            </sql:query>
            <tr>
                <td><%=timeClock.getEntry(i).getStaffid().getStaffid()%></td>

                <c:forEach var = "row" items = "${result.rows}">
                    <c:set var="formattedText" value="${formattedText} ${fn:toUpperCase(fn:substring(row.staffType,0,1))}${fn:toLowerCase(fn:substring(row.staffType,1,fn:length(row.staffType)))}" />                        

                    <td> <c:out value = "${row.staffName}"/></td>
                    <td> <c:out value = "${formattedText}"/></td>
                    <td> <c:out value = "${row.staffContact}"/></td>
                </c:forEach>

                <td><%=df.format(cal.getTime())%></td>
                <td><%=timeClock.getEntry(i).getTimeclocktype()%></td>

            </tr>


            <c:remove var = "formattedText"/>

            <%
                    counter++;
                }
            %>

        </table>









    </body>
</html>
