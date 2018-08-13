/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import model.Patients;
import model.PatientsService;

/**
 *
 * @author JT
 */
public class PatientRegistration extends HttpServlet {
    
    @PersistenceContext(unitName = "HospitalQueueSystemPU")
    EntityManager em;
    @Resource
    UserTransaction utx;
    @Override
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        String id = (String) request.getParameter("id");
        String name = (String) request.getParameter("name");
        String ic = (String) request.getParameter("ic");
        String gender = (String) request.getParameter("gender");
        String telNo = (String) request.getParameter("telno");
        String addr = (String) request.getParameter("addr1")
                + ", " + (String) request.getParameter("addr2")
                + ", " + (String) request.getParameter("addr3")
                + ", " + (String) request.getParameter("addr4");
        
        if (id != null && name != null && ic != null && gender != null && telNo != null && addr != null) {
            try {
                name = name.toUpperCase();
                gender = gender.toUpperCase();
                addr.toUpperCase();
                
                Patients newPatient = new Patients(Integer.parseInt(id), name, ic, gender, telNo, addr);
                
                utx.begin();;
                boolean success = new PatientsService(em).addPatients(newPatient);
                utx.commit();
                
                if(success) {
                    session.setAttribute("newPatient", newPatient);
                    session.setAttribute("newPatientRegistered", true);
                } else {
                    session.setAttribute("newPatientRegistered", false);
                }
                response.sendRedirect("\\Receptionist\\PatientRegisterSuccessful.jsp");
            } catch (Exception ex) {
                session.setAttribute("invalidIC", true);
                response.sendRedirect("\\Receptionist\\PatientRegistration.jsp");
//                out.println("ERROR: " + ex.getMessage());
//                StackTraceElement[] arr = ex.getStackTrace();
//                for (int i = 0; i < arr.length; ++i) {
//                    out.println(arr[i].getClassName() + ": " + arr[i].getLineNumber() + ": " + arr[i].toString() + "<br>");
//                }
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
