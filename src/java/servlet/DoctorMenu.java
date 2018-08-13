/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import ADT.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Staffs;
import model.TimeClockService;
import model.Timeclock;

/**
 *
 * @author Tan Chek Wei
 */
public class DoctorMenu extends HttpServlet {

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
            out.println("<title>Servlet DoctorMenu</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DoctorMenu at " + request.getContextPath() + "</h1>");
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
        java.sql.Timestamp statusTime = null;
        String status = "";
        TimeClockService timeClockService = new TimeClockService(em);
        HttpSession session = request.getSession();
        ArrayList<Timeclock> timeClockList = new ArrayList<>();
        java.sql.Timestamp statusTimeTemp = null;
        String statusTemp = "";

        Timestamp timestamp = null;

        List<Object[]> timeClockObj = timeClockService.findToday(Integer.parseInt(session.getAttribute("id").toString()));
        for (Object[] obj : timeClockObj) {

            Timeclock temp = new Timeclock();
            temp.setTimeclockid(Integer.parseInt(obj[0].toString()));
            temp.setStaffid(new Staffs(Integer.parseInt(obj[1].toString())));
            temp.setTimeclocktype(obj[2].toString());

//            Calendar cal = Calendar.getInstance();
            Timestamp ts = (Timestamp) obj[3];

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(ts.getTime());

//            String pattern = "yyyy-MM-dd";
//            SimpleDateFormat df = new SimpleDateFormat(pattern);
//            try {
//                cal.setTime(df.parse(obj[3].toString()));
//            } catch (ParseException ex) {
//                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
//            }
            temp.setTimeclocktime(cal);

            timeClockList.add(temp);

            timestamp = (Timestamp) obj[3];
            if (timestamp != null) {
                statusTimeTemp = timestamp;
                statusTemp = obj[2].toString();
                System.out.println(timestamp);
                if (statusTime == null) {
                    statusTime = statusTimeTemp;
                    status = statusTemp;
                } else {
                    // Find the latest timestamp
                    if (statusTimeTemp.after(statusTime)) {
                        statusTime = statusTimeTemp;
                        status = statusTemp;
                    }
                }
            }

        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a EEE, d MMM yyyy");

        if (statusTime != null) {
            session.setAttribute("timeClock", timeClockList);
            session.setAttribute("time", dateFormat.format(statusTime));
            session.setAttribute("status", status);
        }

        response.sendRedirect("secureDoctor/DoctorMenu.jsp");
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
