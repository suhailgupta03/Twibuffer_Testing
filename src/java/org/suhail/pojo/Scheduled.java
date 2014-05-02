package org.suhail.pojo;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author user
 */
public class Scheduled {
    private int sid,id;
    private String tweet;
    private Date sdate;
    private Time stime;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Time getStime() {
        return stime;
    }

    public void setStime(Time stime) {
        this.stime = stime;
    }
}
