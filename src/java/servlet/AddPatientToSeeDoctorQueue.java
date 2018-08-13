/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import ADT.LinkedList;
import ADT.LinkedListInterface;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import model.Patients;
import model.PatientsService;
import model.PatientInSeeDoctorQueue;

/**
 *
 * @author JT
 */
@WebServlet(name = "AddPatientToSeeDoctorQueue", urlPatterns = {"/AddPatientToSeeDoctorQueue"})
public class AddPatientToSeeDoctorQueue extends HttpServlet {
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
        
        String ic = (String) request.getParameter("ic");
        String sickness = (String) request.getParameter("sickness");
        
        if(ic != null && sickness != null) {
            // Get all patient to search for specific one.
            List<Patients> allPatient = (List<Patients>) new PatientsService(em).findAll();
            // If database is not empty.
            if (allPatient != null) {
                // Start searching.
                Patients selectedPatient = null;
                for(int i = 0; i < allPatient.size(); i++) {
                    if(allPatient.get(i).getPatientic().equalsIgnoreCase(ic)) {
                        selectedPatient = allPatient.get(i);
                    }
                }
                // If found.
                if(selectedPatient != null) {
                    // If session for queue is not empty, directly proceed. Else create a new adt.
                    LinkedListInterface<PatientInSeeDoctorQueue> sdq = (LinkedList<PatientInSeeDoctorQueue>) session.getAttribute("patientsInSeeDoctorQueue");
                    if(sdq == null) {
                        sdq = new LinkedList<PatientInSeeDoctorQueue>();
                    }
                    // Calculate priority.
                    PatientInSeeDoctorQueue patient = new PatientInSeeDoctorQueue(Integer.parseInt(sickness), selectedPatient);
                    
                    // Check patient in queue or not.
                    boolean patientExisted = false;
                    for(int i = 0; i < sdq.size(); i++) {
                        if(sdq.get(i).getPatient().equals(selectedPatient)) {
                            patientExisted = true;
                        }
                    }
                    if(!patientExisted) {
                        // Add into queue.
                        sdq.addLast(patient);
                        // Store necessary session.
                        session.setAttribute("patientsInSeeDoctorQueue", sdq);
                        session.setAttribute("patient404", false);
                        session.setAttribute("patientExisted", false);
                    } else {
                        session.setAttribute("patient404", false);
                        session.setAttribute("patientExisted", true);
                    }
                } else { // If not found.
                    session.setAttribute("patient404", true);
                    session.setAttribute("patientExisted", false);
                }
            } else { // If database is empty.
                session.setAttribute("patient404", true);
                session.setAttribute("patientExisted", false);
            }
        }
        response.sendRedirect("\\Receptionist\\SeeDoctorQueue.jsp");
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
