<%-- 
    Document   : DoctorMenu
    Created on : Aug 12, 2018, 9:23:30 AM
    Author     : Tan Chek Wei
--%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Timeclock"%>
<%@page import="ADT.ArrayList"%>
<%
    String path = request.getContextPath();
%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
        
        <link rel="stylesheet" href="../css/hover-type-button.css">
        <link rel="stylesheet" href="../css/hover-button.css">
        <link rel="stylesheet" href="../css/hover-table.css">
        <link rel="stylesheet" href="../css/main.css">
    </head>
    <body>
        <h1>Welcome, Dr <%= session.getAttribute("name")%> </h1>






        <form action="<%=path%>/TimeClock" method="post">
            <input type="hidden" name="id" value="${id}">
            <c:choose>
                <c:when test = "${status == 'clock in'}">
                    <p>Current status: Clocked in on ${time}</p>
                    <input class="hover-type-button" type="submit" name="operation" value="clock out">
                </c:when>
                <c:otherwise>
                    <p>Current status: Clocked out on ${time}</p>
                    <input class="hover-type-button" type="submit" name="operation" value="clock in">
                </c:otherwise>
            </c:choose>
        </form>

        <table id="hover-table" width="100%" >
            <thead>
                <tr>
                    <th width="10%">No</th>
                    <th width="30%">Time</th>
                    <th width="30%">Type</th>
                    <th width="30%">Duration</th>
                </tr>
            </thead>
            <%
                long sum = 0;
                String elapsedMinutesStr = "", elapsedHoursStr = "";
                long elapsedHours = 0, elapsedMinutes = 0;
                int counter = 1;
                ArrayList<Timeclock> timeClock = (ArrayList) session.getAttribute("timeClock");
                if (timeClock != null) {
                    for (int i = timeClock.getNumberOfEntries() - 1; i >= 0; i--) {
                        SimpleDateFormat df = new SimpleDateFormat("h:mm a");
                        Calendar cal = timeClock.getEntry(i).getTimeclocktime();
                        if (i - 1 >= 0) {

                            Calendar calNext = timeClock.getEntry(i - 1).getTimeclocktime();
                            long different = cal.getTimeInMillis() - calNext.getTimeInMillis();
                            sum += different;
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
            %>
            <tr>
                <td><%=counter%></td>
                <td><%=df.format(cal.getTime())%></td>
                <td><%=timeClock.getEntry(i).getTimeclocktype()%></td>
                <td><%=elapsedHoursStr%><%=elapsedMinutesStr%></td>




            <%
                    counter++;
                }
                    long secondsInMilli = 1000;
                    long minutesInMilli = secondsInMilli * 60;
                    long hoursInMilli = minutesInMilli * 60;

                    elapsedHours = sum / hoursInMilli;
                    sum = sum % hoursInMilli;

                    elapsedMinutes = sum / minutesInMilli;
                    sum = sum % minutesInMilli;

                    elapsedMinutesStr = String.format("%02d", elapsedMinutes);
                    elapsedHoursStr = String.format("%01d", elapsedHours) + ":";
                }
            %>

            </tr>
            <tr>
                <td  colspan="3">Total Duration</td>
                <td><%=elapsedHoursStr%><%=elapsedMinutesStr%></td>
            </tr>
        </table>
    </body>
</html>
