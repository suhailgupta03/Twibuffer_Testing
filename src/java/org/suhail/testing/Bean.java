/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.suhail.testing;

import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class Bean {
    
    private static HttpSession session;

    public static HttpSession getSession() {
        return session;
    }

    public static void setSession(HttpSession session) {
        Bean.session = session;
    }
    
    
}
