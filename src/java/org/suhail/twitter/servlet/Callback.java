package org.suhail.twitter.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.suhail.bean.twb.Sholder;
import org.suhail.database.AccessToken;
import org.suhail.twitter.SCollector;

/**
 * This servlet is a callback URL of the APP
 * @author user
 */
public class Callback extends HttpServlet {

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oauth_verifier = request.getParameter("oauth_verifier");
        try {
          twitter4j.auth.AccessToken atoken = SCollector.getTwitter().getOAuthAccessToken(oauth_verifier);
          org.suhail.database.AccessToken atn = new AccessToken(); 
          // Get the stashed session
          HttpSession uSession = Sholder.getSession(); 
          // Update the access-token and access-token-secret in the 'ucred' table
          atn.updateAccessToken((Integer)uSession.getAttribute("uid"), atoken.getToken());
          atn.updateAccessTokenSecret((Integer)uSession.getAttribute("uid"), atoken.getTokenSecret());
          // send a redirect to the user page
          response.sendRedirect("User.jsp");
        }catch(Exception exc) {
            exc.printStackTrace();
        }
    }
}
