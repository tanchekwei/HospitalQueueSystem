/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import ADT.ArrayList;
import ADT.ListInterface;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.StaffsService;
import model.TimeClockService;
import ADT.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Staffs;
import model.Timeclock;
/**
 *
 * @author Tan Chek Wei
 */
public class Login extends HttpServlet {

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
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");

        StaffsService staffService = new StaffsService(em);
        // find doctor contact
        HttpSession session = request.getSession();

        List<Object[]> doctorObj = staffService.findEmployed();

        boolean isUsername = false;
        boolean isPassword = false;
        boolean isValid = false;
        String id = "";
        String name = "";
        String position = "";
        if (doctorObj != null) {
            for (Object[] obj : doctorObj) {
                

                if (!isUsername && username.compareTo(obj[10].toString()) == 0) {
                    isUsername = true;
                }
                if (!isPassword && password.compareTo(obj[11].toString()) == 0) {
                    isPassword = true;
                }
                if (isUsername && isPassword) {
                    id = obj[0].toString();
                    name = obj[1].toString();
                    position = obj[2].toString();
                    isValid = true;
                    break;
                } else {
                    isValid = false;
                }
            }
        }
        System.out.println(isValid);

        if (isValid) {
            if (position.compareTo("doctor") == 0) {
                session.setAttribute("isValid", "true");
                session.setAttribute("name", name);
                session.setAttribute("id", id);

                response.sendRedirect(request.getContextPath() + "/DoctorMenu");
            } else if (position.compareTo("recep") == 0) {
                response.sendRedirect("Receptionist/SeeDoctorQueue.jsp");
            } else if (position.compareTo("admin") == 0) {
                response.sendRedirect(request.getContextPath() + "/DisplayDoctors");
            }
        } else {
            session.setAttribute("isValid", "false");
            response.sendRedirect("secureVisitor/Login.jsp");
        }
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
