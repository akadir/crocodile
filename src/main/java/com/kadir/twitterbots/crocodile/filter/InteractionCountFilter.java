package com.kadir.twitterbots.crocodile.filter;

import com.kadir.twitterbots.crocodile.util.Config;
import com.kadir.twitterbots.crocodile.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;

/**
 * @author akadir
 * Date: 2019-05-06
 * Time: 18:13
 */
public class InteractionCountFilter implements StatusFilter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int interactionCountLimit;

    public InteractionCountFilter() {
        this.interactionCountLimit = Config.getPropertyAsInt(Constants.INTERACTION_COUNT_LIMIT_KEY, "-1");
        logger.info("Set {} to {}", Constants.INTERACTION_COUNT_LIMIT_KEY, interactionCountLimit);
    }

    @Override
    public boolean passed(Status status) {
        int statusInteractionCount = status.getFavoriteCount() + status.getRetweetCount();

        if (interactionCountLimit > statusInteractionCount) {
            return true;
        } else {
            logger.info("{} not passed from filter. Interaction Count: {}", status.getId(), statusInteractionCount);
            return false;
        }
    }
}
