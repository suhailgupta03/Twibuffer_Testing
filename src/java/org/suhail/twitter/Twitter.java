package org.suhail.twitter;

import org.suhail.database.AccessToken;

/**
 *
 * @author user
 */
public class Twitter {
    
    /**
     * This method sends the scheduled tweet to the user's timeline. 
     */
    public void tweet(String tweet,int id) {
        try {
            System.out.println("@@@--->>>>Inside the tweet method<<<<---@@@");
            twitter4j.Twitter twitter = SCollector.getTwitter();
            org.suhail.database.AccessToken atoken = new AccessToken();
            twitter.setOAuthAccessToken(new twitter4j.auth.AccessToken(atoken.getAccessToken(id), atoken.getAccessTokenSecret(id)));
            twitter.updateStatus(tweet);
        }catch(Exception exc) {
            exc.printStackTrace();
        }
    }
}
