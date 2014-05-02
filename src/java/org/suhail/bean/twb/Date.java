package org.suhail.bean.twb;

import java.util.GregorianCalendar;

/**
 *
 * @author user
 */
public class Date {
    
    /**
     * This method formats the date before putting into the database 'scheduled'.
     * @param date : MM/dd/yyyy
     * @return java.sql.date instance
     */
    public java.sql.Date format(String date) {
        int i = 0;
        int dateAttr[];
        dateAttr = new int[3];
        for(String v : date.split("/")) {
            dateAttr[i] = Integer.parseInt(v);
            i++;
        }

        GregorianCalendar gcalendar = new GregorianCalendar();
        gcalendar.set(dateAttr[2], dateAttr[0]-1, dateAttr[1]); // Year,Month,Day Of Month
        java.sql.Date sdate = new java.sql.Date(gcalendar.getTimeInMillis());
        return sdate;
    }
}
