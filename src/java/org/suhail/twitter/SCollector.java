
package org.suhail.twitter;

import twitter4j.Twitter;

/**
 *
 * @author user
 */
public class SCollector {
    /**
     * This variable is the already initialized twitter variable for some AuthURL.
     */
    private static Twitter twitter;

    public static Twitter getTwitter() {
        return twitter;
    }

    public static void setTwitter(Twitter twitter) {
        SCollector.twitter = twitter;
    }
    
}
