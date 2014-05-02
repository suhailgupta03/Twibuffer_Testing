package org.suhail.database;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.suhail.hibernate.HVR;
import org.suhail.pojo.UCred;

/**
 *
 * @author user
 */
public class Login extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Session session= new HVR().getSession();
        String hql = "from UCred";
        List list = session.createQuery(hql).list();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()) {
            UCred ucred = (UCred) iterator.next();
            if(ucred.getUsername().compareTo(username) == 0 && ucred.getPassword().compareTo(password) == 0) {
                // Combination Match!
                request.setAttribute("username", username);
                request.setAttribute("uid", ucred.getId());
                RequestDispatcher dispatcher = request.getRequestDispatcher("user");
                dispatcher.forward(request, response);
            } else {
                // Login Failed! Retry.
                request.setAttribute("loginfailed", true);
                System.out.println("Inside the else block !");
                RequestDispatcher dispatcher = request.getRequestDispatcher("#");
                dispatcher.forward(request, response);
            }
        }
        session.close();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
