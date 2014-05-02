package org.suhail.database;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.suhail.hibernate.HVR;
import org.suhail.pojo.Scheduled;

/**
 *
 * @author user
 */
public class InsertTweet {
    
    private java.sql.Date sdate;
    private java.sql.Time stime;
    private String stweet;
    /**
     * id is the foreign key referencing 'usercred' column 'id' from 'scheduled'.
     */
    private int id;
    
    /**
     * This method sets the date for a tweet
     * @param date 
     */
    public void setDate(java.sql.Date date) {
        sdate = date;
    }
    
    /**
     * This method sets the time for a tweet
     * @param time 
     */
    public void setTime(java.sql.Time time){
        stime = time;
    }
    
    /**
     * This method sets the tweet
     * @param tweet 
     */
    public void setTweet(String tweet) {
        stweet = tweet;
    }
    
    /**
     * This method sets User ID.ID in 'schedule' table is the foreign key to 'ucred' table.
     * @param id 
     */
    public void setID(int uid) {
        id = uid;
    }
    
    /**
     * This method inserts the Date,Time,Tweet into the 'scheduled' database.
     * @return boolean - true if the tweet was successfully inserted and false if the insertion failed
     */
    public boolean insert() {
        boolean inserted = false;
        try {
            Scheduled scheduled = new Scheduled();
            scheduled.setSdate(sdate);
            scheduled.setStime(stime);
            scheduled.setTweet(stweet);
            scheduled.setId(id);
            Session session = new HVR().getSession();
            Transaction transaction  = session.beginTransaction();
            session.save(scheduled);
            transaction.commit();
            inserted = true;
            session.close();
            return inserted;
        }catch(Exception exc) {
            exc.printStackTrace();
            return inserted;
        }
    }
}
