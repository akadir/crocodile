package com.kadir.twitterbots.crocodile.deleter;

import com.kadir.twitterbots.crocodile.enumeration.TwitterErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.TwitterException;

import java.util.List;

/**
 * @author akadir
 * Date: 2019-05-06
 * Time: 20:59
 */
public class BulkDeleter implements IBulkDeleter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private int deletedCount = 0;

    @Override
    public void delete(List<Long> statusList, IDeleter deleter) {
        for (Long statusId : statusList) {
            try {
                logger.info("Check id: {}", statusId);
                if (deleter.delete(statusId)) {
                    deletedCount++;
                }
            } catch (TwitterException e) {
                if (e.getErrorCode() == TwitterErrorCode.STATUS_NOT_FOUND.getValue()) {
                    logger.error("{} id not found", statusId);
                } else {
                    logger.error("An error occurred: ", e);
                }
            } catch (InterruptedException e) {
                logger.error("Thread interrupted: ", e);
                Thread.currentThread().interrupt();
            }
        }
        logger.info("{} statuses were deleted.", deletedCount);
    }

    @Override
    public void logDeletedCount() {
        logger.info("{} statuses were deleted.", deletedCount);
    }
}
