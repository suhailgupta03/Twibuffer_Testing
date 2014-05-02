
package org.suhail.bean.scheduler;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author user
 */
public class Checker {
    
    /**
     * This method checks if the tweet is scheduled for the correct date.
     * @return 
     */
    public boolean isDateOk(java.sql.Date date) {
        boolean isOk = false;
        Calendar now = Calendar.getInstance();
        int nowYear = now.get(Calendar.YEAR);
        int nowDOY = now.get(Calendar.DAY_OF_YEAR);
        Calendar dt = Calendar.getInstance();
        dt.setTime(date);
        int dtYear = dt.get(Calendar.YEAR);
        int dtDOY = dt.get(Calendar.DAY_OF_YEAR);
        
        if(dtYear >= nowYear & dtDOY >= nowDOY) isOk = true;
        return isOk;
    }
    
    /**
     * This method checks if the tweet scheduled is for the correct time.
     * @return 
     */
    public boolean isTimeOk(java.sql.Time time){
        boolean isOk = false;
        if(time.getTime() > new GregorianCalendar().getTimeInMillis()) isOk = true;
        return isOk;
    }
}
