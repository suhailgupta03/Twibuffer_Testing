
package org.suhail.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author user
 */
public class HVR {
    
    /**
     * This method returns the Hibernate Session object.
     * @return 
     */
    public Session getSession() {
        try {
            Configuration conf = new Configuration().configure();
            SessionFactory sessFact = conf.buildSessionFactory();
            Session session = sessFact.openSession();
            return session;
        }catch(Exception exc) {
            exc.printStackTrace();
            return null;
        }
    }
}
