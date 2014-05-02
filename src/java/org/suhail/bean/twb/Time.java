package org.suhail.bean.twb;

import java.util.GregorianCalendar;

/**
 *
 * @author user
 */
public class Time {
    
    /**
     * This method formats the time before putting into the database 'scheduled'.
     * @param date MM/dd/yyyy
     * @param time 11:45pm
     * @return 
     */
    public java.sql.Time format(String date,String time) {
        java.sql.Time stime = null;
        int i = 0;
        int dateAttr[];
        dateAttr = new int[3];
        for(String v : date.split("/")) { 
            dateAttr[i] = Integer.parseInt(v);
            i++;
        }
        i = 0;
        int timeAttr[];
        timeAttr = new int[2];
        if(time.contains("am")) {
            time = time.replace("am", "").trim();
            for(String v : time.split(":")) {
                timeAttr[i] = Integer.parseInt(v);
                i++;
            }
        }else if(time.contains("pm")) {
            time = time.replace("pm", "").trim();
            for(String v : time.split(":")) {
                timeAttr[i] = Integer.parseInt(v);
                i++;
            }
            timeAttr[0] = timeAttr[0] + 12 ;
        }
        GregorianCalendar gcalendar = new GregorianCalendar();
        gcalendar.set(dateAttr[2], dateAttr[0]-1, dateAttr[1],timeAttr[0], timeAttr[1], 0);
        //             YEAR         MONTH          DayOfMon    HR            MIN       SEC
        stime = new java.sql.Time(gcalendar.getTimeInMillis());
        return stime;
    }
}
