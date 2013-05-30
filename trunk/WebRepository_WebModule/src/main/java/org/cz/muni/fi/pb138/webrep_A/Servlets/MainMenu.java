/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cz.muni.fi.pb138.webrep_A.Servlets;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.DatabaseManager;

/**
 *
 * @author Andrej Makovicky
 */
@WebServlet(name = "MainMenu", urlPatterns = {"/MainMenu"})
public class MainMenu extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Locale locale = request.getLocale();
        ResourceBundle labelsBundle;
        labelsBundle = ResourceBundle.getBundle("labelsBundle", locale);

//        DatabaseManager dm = new DatabaseManager();
//        CollectionManager cm = new CollectionManager("", dm);
//        try {
//            request.setAttribute("xml_menu", cm.getLeagueList());
//            request.getRequestDispatcher("/index.jsp").forward(request, response);
//            return;
//        } catch (BaseXException ex) {
//            Logger.getLogger(GetMatchServlet.class.getName()).log(Level.SEVERE, null, ex);
//            request.setAttribute("error", labelsBundle.getString("database_error"));
//            request.getRequestDispatcher("/index.jsp").forward(request, response);
//            return;
//        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
