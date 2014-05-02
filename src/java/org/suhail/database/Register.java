package org.suhail.database;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.suhail.hibernate.HVR;
import org.suhail.pojo.UCred;

/**
 *
 * @author user
 */
public class Register extends HttpServlet {

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String website = request.getParameter("website");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Session session = new HVR().getSession(); // Get Hibernate Session Object
        Transaction trans = session.beginTransaction();
        
        UCred ucred = new UCred();
        ucred.setFirstName(firstName);
        ucred.setLastName(last_name);
        ucred.setWebsite(website);
        ucred.setUsername(username);
        ucred.setEmail(email);
        ucred.setPassword(password);
        ucred.setAccessToken("null"); // Initially set access token to null
        
        session.save(ucred);
        trans.commit();
        
        request.setAttribute("isregistered", true);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
        session.close();
    }
}
