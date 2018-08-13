<%-- 
    Document   : SeeDoctorQueue
    Created on : Aug 10, 2018, 8:53:49 PM
    Author     : JT
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.PatientInSeeDoctorQueue"%>
<%@page import="ADT.*"%>
<%@page import="model.Patients"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <META HTTP-EQUIV="Refresh" CONTENT="15">
        <title>JSP Page</title>
        
        <link rel="stylesheet" href="../css/hover-type-button.css">
        <link rel="stylesheet" href="../css/hover-button.css">
        <link rel="stylesheet" href="../css/hover-table.css">
        <link rel="stylesheet" href="../css/main.css">
    </head>
    <body>
        <a href="PatientRegistration.jsp"><li class="hover-button">+ Register <span>New Patient</span></li></a>
        <hr>
        <%
            Object patient404 = pageContext.getSession().getAttribute("patient404");
            if(patient404 != null && (Boolean) patient404 == true) {
                out.print("<label style=\"color:red\">* Patient not found in database. Recommended to <a href=\"PatientRegistration.jsp\">register</a> as a new patient for him/her.</label>");
            }
            Object patientExisted = pageContext.getSession().getAttribute("patientExisted");
            if(patientExisted != null && (Boolean) patientExisted == true) {
                out.print("<label style=\"color:red\">* Patient already in queue.</label>");
            }
        %>
        <form method="post" action="../AddPatientToSeeDoctorQueue">
            <table>
                <tr>
                    <td><label>Patient IC Number</label>&nbsp;</td>
                    <td><input width="100%" type="text" name="ic" required="required"/></td>
                    <td><label>Sickness</label></td>
                    <td>
                        <select name="sickness">
                            <option value ="0">0 - Unknown (Low Priority)</option>
                            <option value ="0">0 - Blood Test</option>
                            <option value ="1">1 - Unknown (Medium Priority)</option>
                            <option value ="1">1 - Skin Disease</option>
                            <option value ="1">2 - Unknown (High Priority)</option>
                            <option value ="2">2 - Fever</option>
                            <option value ="2">2 - Influenza</option>
                            <option value ="2">2 - Minor Injury</option>
                            <option value ="3">3 - Unknown (Highest Priority)</option>
                            <option value ="3">3 - Serious Injury</option>
                            <option value ="3">3 - Allergy</option>
                            <option value ="3">3 - Asthma</option>
                        </select>
                    </td>
                    <td rowspan="2"><input class="hover-type-button" type="submit" value="+ Add Patient To Queue" name="Add Patient To Queue"/></td>
                </tr>
            </table>
        </form>
        <br/>
        <table width="100%" id="hover-table">
            <caption><b>Current Queue for See Doctor</b></caption>
            <tr>
                <th width="10%">Queue no.</th>
                <th width="30%">Patient Name</th>
                <th width="20%">Arrival Time</th>
                <th width="10%">Priority(%)</th>
                <th>Operation</th>
            </tr>
            <%
                Object patientQueue = pageContext.getSession().getAttribute("patientsInSeeDoctorQueue");
                int patientCount = 0;
                if(patientQueue != null) {
                    LinkedListInterface<PatientInSeeDoctorQueue> pQueue = (LinkedList<PatientInSeeDoctorQueue>) patientQueue;
                    pQueue.sortDesc();
                    for(int i = 0; i < pQueue.size(); i++) {
                        pQueue.get(i).setPriority(pQueue.get(i).calPriority());
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        DecimalFormat fdf = new DecimalFormat("0.00");
                        out.print("<tr style=\"text-align: center;\">");
                        out.print("<td>" + pQueue.get(i).getQueueNo() + "</td>");
                        out.print("<td>" + pQueue.get(i).getPatient().getPatientname() + "</td>");
                        out.print("<td>" + df.format(pQueue.get(i).getArrivalTime()) + "</td>");
                        out.print("<td>" + fdf.format(pQueue.get(i).getPriority()) + "</td>");
                        out.print("<td><div><a href=\"#\"><li class=\"hover-button\">+ Add <span>New Medical Record</span></li></a></div></td>");
                        
                        out.print("</tr>");
                    }
                    patientCount = pQueue.size();
                }
                out.print("<tr><td colspan=\"5\" style=\"text-align: center;\"><i>Currently " + patientCount + " in queue.</i></td></tr>");
            %>
        </table>
    </body>
</html>
