/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ADT.ArrayList;
import ADT.ListInterface;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import model.Staffs;
import model.TimeClockService;
import model.Timeclock;

/**
 *
 * @author Tan Chek Wei
 */
public class DisplayDoctors extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DisplayDoctors</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DisplayDoctors at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TimeClockService timeClockService = new TimeClockService(em);
        HttpSession session = request.getSession();
        ArrayList<Timeclock> timeClockList = new ArrayList<>();
        ArrayList<Integer> uniqueIDList = new ArrayList<>();

        List<Object[]> timeClockObj = timeClockService.findTodayAll();
        
        for (int i = timeClockObj.size() - 1; i >= 0; i--){
            Timeclock temp = new Timeclock();
            temp.setTimeclockid(Integer.parseInt(timeClockObj.get(i)[0].toString()));
            temp.setStaffid(new Staffs(Integer.parseInt(timeClockObj.get(i)[1].toString())));
            temp.setTimeclocktype(timeClockObj.get(i)[2].toString());
            Timestamp ts = (Timestamp) timeClockObj.get(i)[3];
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(ts.getTime());
            temp.setTimeclocktime(cal);
            int index = 0;
            if (uniqueIDList.contains(Integer.parseInt(timeClockObj.get(i)[1].toString()))) {
                for (int j = timeClockList.getNumberOfEntries() - 1; j >= 0; j--) {
                    if (timeClockList.getEntry(j).getStaffid() == timeClockObj.get(i)[1]) {
                        index = j;
                        break;
                    }
                }
                timeClockList.replace(index, temp);
            } else {
                timeClockList.add(temp);
                uniqueIDList.add(Integer.parseInt(timeClockObj.get(i)[1].toString()));

            }
            
            
            
            
            
            
            
            
        }
//        for (Object[] obj : timeClockObj) {
//            Timeclock temp = new Timeclock();
//            temp.setTimeclockid(Integer.parseInt(obj[0].toString()));
//            temp.setStaffid(new Staffs(Integer.parseInt(obj[1].toString())));
//            temp.setTimeclocktype(obj[2].toString());
//            Timestamp ts = (Timestamp) obj[3];
//            Calendar cal = Calendar.getInstance();
//            cal.setTimeInMillis(ts.getTime());
//            temp.setTimeclocktime(cal);
//            int index = 0;
//            if (uniqueIDList.contains(Integer.parseInt(obj[1].toString()))) {
//                for (int i = 0; i < timeClockList.getNumberOfEntries(); i++) {
//                    if (timeClockList.getEntry(i).getStaffid() == obj[1]) {
//                        index = i;
//                        break;
//                    }
//                }
//                timeClockList.replace(index, temp);
//            } else {
//                timeClockList.add(temp);
//                uniqueIDList.add(Integer.parseInt(obj[1].toString()));
//
//            }
//        }
        session.setAttribute("timeClock", timeClockList);

        // Get doctor details
        ArrayList<Staffs> staffs = new ArrayList<>();

        response.sendRedirect("secureAdmin/DisplayDoctors.jsp");
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
