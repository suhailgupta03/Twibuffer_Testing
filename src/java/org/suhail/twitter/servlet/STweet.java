package org.suhail.twitter.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.suhail.bean.scheduler.Checker;
import org.suhail.bean.twb.Sholder;
import org.suhail.bean.twb.Time;
import org.suhail.database.InsertTweet;

/**
 * Schedule Tweet
 * @author user
 */
public class STweet extends HttpServlet {

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
        String tweet = request.getParameter("tweet_entered");
        String scheduledDate = request.getParameter("SelectedDate");
        String scheduledTime = request.getParameter("timepicker");
        System.out.println("$$$Scheduled Time : " + scheduledTime);
        // Schedule if the time and date for the tweet are correct
        org.suhail.bean.twb.Date d = new org.suhail.bean.twb.Date();
        java.sql.Date sdate = d.format(scheduledDate);
        org.suhail.bean.twb.Time t = new org.suhail.bean.twb.Time();
        java.sql.Time stime = t.format(scheduledDate, scheduledTime);
        System.out.println("@@@@sdate : " + sdate);
        System.out.println("@@@@stime : " + stime);
        Checker checker = new Checker();
        System.out.println("Date : " + checker.isDateOk(sdate));
        System.out.println("Time : " + checker.isTimeOk(stime));
        if(!tweet.isEmpty() & checker.isDateOk(sdate) & checker.isTimeOk(stime)) {
            // schedule the tweet
            InsertTweet twt = new InsertTweet();
            twt.setDate(sdate);
            twt.setTime(stime);
            twt.setTweet(tweet);
            twt.setID((Integer)Sholder.getSession().getAttribute("uid"));
            boolean inserted = twt.insert();
            if(inserted) {
                request.setAttribute("scheduled", true);
                request.setAttribute("Incorrect data", false);
                RequestDispatcher dispatcher = request.getRequestDispatcher("User.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("scheduled", false);
                System.out.println("else statement@@@@@@@");
                request.setAttribute("Incorrect data", false);
                RequestDispatcher dispatcher = request.getRequestDispatcher("User.jsp");
                dispatcher.forward(request, response);
            }            
        }else {
            // Either the tweet string is empty or the date and time are set in the past!
            System.out.println("Incorrect Data$$$$$$");
            request.setAttribute("scheduled", false);
            request.setAttribute("Incorrect data", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("User.jsp");
            dispatcher.forward(request, response);
        }
    }
}
