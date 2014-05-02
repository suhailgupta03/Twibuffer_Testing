package org.suhail.thread;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.suhail.hibernate.HVR;
import org.suhail.pojo.Scheduled;
import org.suhail.twitter.Twitter;

/**
 *
 * @author user
 */
public class Twfinder {
    
    private boolean isThreadRunning = true;
    
    /**
     * This method starts a thread that searches for the scheduled tweets.The thread works 
     * in a loop searching for tweets matching the current time.When tweet/tweets is/are found
     * it tweets on a separate thread.
     * 
     */
    public void start() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                find();
            }
        };
        new Thread(r,"twfinder").start();
    }
    
    /**
     * This method finds the next available tweet and calls a particular method that sends the tweet
     * to the user's timeline.
     */
    private void find() {
        Session session = new HVR().getSession();
        while(isThreadRunning) {
            String hql = "from Scheduled where stime <= current_time() and sdate = current_date()";
            List list = session.createQuery(hql).list();
            Iterator iterator = list.iterator();
            System.out.println("Outer loop");
            while(iterator.hasNext()) {
                System.out.println("Inside inner while loop");
                Scheduled scheduled = (Scheduled)iterator.next();
                String tweet = scheduled.getTweet();
                int id = scheduled.getId();
                int sid = scheduled.getSid();
                tweetThread(tweet, id, sid); // Tweet on a separate thread
            }
            try {Thread.sleep(2000);} catch(Exception exc) { System.out.println(exc); }
        }
    }
    
    /**
     * This method can be called from other class to stop this thread if required.
     */
    public void stopTwfinderThread() {
        isThreadRunning = false;
    }
    
    /**
     * This method calls the 'tweet' method of 'org.suhail.twitter.Twitter' class on a separate thread.
     * @param tweet
     * @param id 
     */
    private void tweetThread(final String tweet,final int id, final int sid) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                new Twitter().tweet(tweet, id);
                deleteTweet(sid);
            }
        };
        new Thread(r,"Tweet Thread").start();
    }
    
    /**
     * This method is called from `tweetThread` and deletes the tweet from the table
     * that is just sent to the user's timeline.
     * @param sid 
     */
    private void deleteTweet(final int sid) {
        try {
            Session session = new HVR().getSession();
            Transaction trans = session.beginTransaction();
            String hql = "delete from Scheduled where sid = '" + sid + "'";
            Query query = session.createQuery(hql);
            query.executeUpdate();
            trans.commit();
            session.close();
        }catch(Exception exc) {
            exc.printStackTrace();
        }
    }
}
