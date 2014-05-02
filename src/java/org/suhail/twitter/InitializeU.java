
package org.suhail.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

/**
 *
 * @author user
 */
public class InitializeU {
    private String authURL;

    /**
     * This method returns the authorization URL, that will authorize the application 
     * to use twitter on behalf of another user.
     * @return authURL
     */
    public String getAuthURL() {
        Twitter twitter = SCollector.getTwitter();
        try {
            RequestToken rToken = twitter.getOAuthRequestToken();
            authURL = rToken.getAuthorizationURL();
        }catch(Exception exc) {
            System.out.println("$$$$$$$$Inside exception block$$$$$$$$$$");
            exc.printStackTrace();
        }
        return authURL;
    }
    
    /**
     * This method sets the required ' Consumer Key ' and ' Consumer Key Secret '.
     */
    public void setApplicationKeys() {
        try {
            String consumerKey = AppCred.getConsumerKey();
            String consumerKeySecret = AppCred.getConsumerKeySecret();
            Twitter twitter = new TwitterFactory().getInstance();
            twitter.setOAuthConsumer(consumerKey,consumerKeySecret);
            SCollector.setTwitter(twitter);
        }catch(Exception exc) {
            System.out.println("#######Inside exception block############");
            exc.printStackTrace();
        }
    }
}
