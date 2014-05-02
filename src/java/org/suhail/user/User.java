package org.suhail.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.suhail.bean.twb.Sholder;
import org.suhail.hibernate.HVR;
import org.suhail.pojo.UCred;

/**
 *
 * @author user
 */
public class User extends HttpServlet {

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
        String username = (String)request.getAttribute("username");
        int uid = (Integer)request.getAttribute("uid");
        
        request.getSession().invalidate();
        HttpSession uSession = request.getSession(true);
        uSession.setAttribute("username", username);
        uSession.setAttribute("uid", uid);
        uSession.setMaxInactiveInterval(3600);
        Sholder.setSession(uSession); // Stash the session in the static variable for future use
        if(appAuthorized(username)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("User.jsp");
            dispatcher.forward(request, response);
        }else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("sauth.jsp");
            dispatcher.forward(request, response);
        }        
    }
   
    /**
     * This method tells if the user has previously authorized APP.
     * @param username
     * @return true is the user has previously authorized the APP and false otherwise 
     */
    private boolean appAuthorized(String username) {
        boolean hasAuthorized = false;
        try {
            Session session = new HVR().getSession();
            String hql = "from UCred where username ='" + username + "'";
            List list = session.createQuery(hql).list();
            Iterator iterator = list.iterator();
            while(iterator.hasNext()) {
                UCred user = (UCred)iterator.next();
                if(user.getAccessToken() != null) {
                    hasAuthorized = true;
                }else {
                    hasAuthorized = false;
                }
            }
            session.close();
        }catch(Exception exc) {
            exc.printStackTrace();
            return false;
        }
        return hasAuthorized;
    }
    
}
