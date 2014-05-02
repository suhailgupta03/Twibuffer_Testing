package org.suhail.bean.twb;

import org.suhail.database.AccessToken;
import org.suhail.twitter.SCollector;

/**
 * Twitter Account
 * @author user
 */
public class TwAc {
    
    /**
     * This method returns the last tweet made by the user.
     * @param id
     * @return 
     */
    public String getLastTweet(int id) {
        try {
            twitter4j.Twitter twitter = SCollector.getTwitter();
            org.suhail.database.AccessToken atoken = new AccessToken();
            twitter.setOAuthAccessToken(new twitter4j.auth.AccessToken(atoken.getAccessToken(id), atoken.getAccessTokenSecret(id)));
          //  twitter.
        }catch(Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }
}
