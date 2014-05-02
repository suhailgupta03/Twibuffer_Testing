package org.suhail.database;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.suhail.hibernate.HVR;
import org.suhail.pojo.UCred;

/**
 *
 * @author user
 */
public class AccessToken {
    
    /**
     * This method updates the UCred database by inserting the access token obtained from the Twitter API.
     * @param id - of the user in the database
     * @param accessToken of the user obtained from Twitter API
     */
    public void updateAccessToken(int id , String accessToken) {
        try {
            Session session = new HVR().getSession();
            Transaction trans = session.beginTransaction();
            String hql = "UPDATE UCred set accessToken ='" + accessToken + "'" + " WHERE id ='" + id + "'";
            Query query = session.createQuery(hql);
            query.executeUpdate();
            trans.commit();
            session.close();
        }catch(Exception exc) {
            exc.printStackTrace();
        }
    }
    
    /**
     * This method updates the access-token-secret key in the 'ucred' table.
     * @param id
     * @param accessTokenSecret 
     */
    public void updateAccessTokenSecret(int id ,  String accessTokenSecret) {
        try {
            Session session = new HVR().getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE UCred set accessTokenSecret = '" + accessTokenSecret + "'" + " WHERE id='" + id + "'";
            Query query = session.createQuery(hql);
            query.executeUpdate();
            transaction.commit();
            session.close();
        }catch(Exception exc) {
            exc.printStackTrace();
        }
    }
    /**
     * This method removes the access token by setting it to null.Required when user revokes access of the application.
     * @param id - of the user in the database UCred      
     */
    public void removeAccessToken(int id) {
        try {
            Session session = new HVR().getSession();
            String hql = "UPDATE UCred set accessToken = null WHERE id ='" + id + "'";
            Query query = session.createQuery(hql);
            query.executeUpdate();
        }catch(Exception exc) {
            exc.printStackTrace();
        }
    }
    
    /**
     * This method returns the Access Token acquired earlier from the Twitter API and 
     * submitted into the database.
     * @return accessToken
     * @param id - of the user in the database UCred
     */
    public String getAccessToken(int id) {
        String accessToken = null;
        try{
            Session session = new HVR().getSession();
            String hql = "from UCred where id ='" + id + "'";
            List list = session.createQuery(hql).list();
            Iterator iterator = list.iterator();
            while(iterator.hasNext()) {
                UCred ucred = (UCred)iterator.next();
                accessToken = ucred.getAccessToken();
            }
            session.close();
        }catch(Exception exc) {
            exc.printStackTrace();
        }
        return accessToken;
    }
    
    /**
     * This method returns the access-token-secret acquired during the callback.
     * @param id
     * @return access-token-secret string
     */
    public String getAccessTokenSecret(int id) {
        String accessTokenSecret = null;
        try {
            Session session = new HVR().getSession();
            String hql = "from UCred where id ='" + id + "'";
            List list = session.createQuery(hql).list();
            Iterator iterator = list.iterator();
            while(iterator.hasNext()) {
                UCred ucred = (UCred)iterator.next();
                accessTokenSecret = ucred.getAccessTokenSecret();
            }
            session.close();
        }catch(Exception exc) {
            exc.printStackTrace();
        }
        return accessTokenSecret;
    }
}
