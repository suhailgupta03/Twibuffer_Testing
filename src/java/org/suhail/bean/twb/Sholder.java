package org.suhail.bean.twb;

import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class Sholder {
    
    private static HttpSession session;

    public static HttpSession getSession() {
        return session;
    }

    public static void setSession(HttpSession session) {
        Sholder.session = session;
    }   
    
}
