package com.kadir.twitterbots.crocodile.deleter;

import com.kadir.twitterbots.authentication.BotAuthenticator;
import com.kadir.twitterbots.crocodile.filter.FilterManager;
import com.kadir.twitterbots.crocodile.util.Constants;
import com.kadir.twitterbots.ratelimithandler.handler.RateLimitHandler;
import com.kadir.twitterbots.ratelimithandler.process.ApiProcessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TweetDeleter implements IDeleter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Twitter twitter;


    public void authenticate() {
        this.twitter = BotAuthenticator.authenticate(Constants.AUTH_FILE_NAME, "");
    }

    @Override
    public boolean delete(Long id) throws TwitterException, InterruptedException {
        Status status = twitter.showStatus(id);
        String statusTextLineBreaksRemoved = status.getText().replaceAll("\\r\\n|\\r|\\n", " ");
        String statusLink = "https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId();
        logger.info("found: {} - {}", statusTextLineBreaksRemoved, statusLink);
        RateLimitHandler.handle(twitter.getId(), status.getRateLimitStatus(), ApiProcessType.SHOW_STATUS);
        boolean deleted = delete(status);
        if (deleted) {
            logger.info("deleted: {}", statusTextLineBreaksRemoved);
        } else {
            logger.info("not deleted: {} - {}", statusTextLineBreaksRemoved, statusLink);
        }
        return deleted;
    }

    private boolean delete(Status status) throws TwitterException, InterruptedException {
        if (status.getQuotedStatusId() != -1 && status.getQuotedStatus() == null) {
            logger.info("quoted tweet not found, tweet will be deleted");
            twitter.destroyStatus(status.getId());
            RateLimitHandler.handle(twitter.getId(), status.getRateLimitStatus(), ApiProcessType.DESTROY_STATUS);
            return true;
        } else if(status.isRetweet() && status.getRetweetedStatus().getUser().getId() != twitter.getId()){
            logger.info("retweet found");
            twitter.destroyStatus(status.getId());
            return true;
        }else {
            if (FilterManager.filterStatus(status)) {
                twitter.destroyStatus(status.getId());
                RateLimitHandler.handle(twitter.getId(), status.getRateLimitStatus(), ApiProcessType.DESTROY_STATUS);
                return true;
            }
        }
        return false;
    }
}
