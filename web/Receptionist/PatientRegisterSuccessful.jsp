<%-- 
    Document   : PatientRegisterSuccessful
    Created on : Aug 12, 2018, 1:22:43 PM
    Author     : JT
--%>

<%@page import="model.Patients"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <%
            Object newPatientRegistered = pageContext.getSession().getAttribute("newPatientRegistered");
            Patients newPatient = (Patients) pageContext.getSession().getAttribute("newPatient");
            if(newPatientRegistered != null && newPatient != null && (Boolean) newPatientRegistered == true) {
                out.print("<label style=\"color:green\">* New patient " + newPatient.getPatientname() + " was registered successfully just now. Do you want to add ");
                if(newPatient.getPatientgender().equalsIgnoreCase("M")) {
                    out.print("him ");
                } else {
                    out.print("her ");
                }
                out.print("into the queue?</label>");
        %>
        <br>
        <form method="post" action="../AddPatientToSeeDoctorQueue">
            <table>
                <tr>
                    <td><label>Patient IC Number</label>&nbsp;</td>
                    <td><input width="100%" type="text" name="ic" value="${newPatient.getPatientic()}" readonly/></td>
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
                
        <form action="SeeDoctorQueue.jsp">
        <input class="hover-type-button" type="submit" value="× Cancel"/>
        </form>
        <%
                pageContext.getSession().setAttribute("newPatientRegistered", null);
                pageContext.getSession().setAttribute("newPatient", null);
            }
        %>
    </body>
</html>
