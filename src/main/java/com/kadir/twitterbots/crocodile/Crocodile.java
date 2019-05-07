package com.kadir.twitterbots.crocodile;

import com.kadir.twitterbots.crocodile.deleter.BulkDeleter;
import com.kadir.twitterbots.crocodile.deleter.TweetDeleter;
import com.kadir.twitterbots.crocodile.enumeration.SourceType;
import com.kadir.twitterbots.crocodile.loader.Loader;
import com.kadir.twitterbots.crocodile.loader.SourceLoaderFactory;
import com.kadir.twitterbots.crocodile.util.Config;
import com.kadir.twitterbots.crocodile.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Crocodile {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    BulkDeleter bulkDeleter;

    public Crocodile() {
        this.bulkDeleter = new BulkDeleter();
    }

    public static void main(String[] args) {
        Crocodile crocodile = new Crocodile();
        Runtime.getRuntime().addShutdownHook(new Thread(crocodile::logDeletedCount));
        crocodile.start();
    }

    private void start() {
        List<Long> statusIdList = loadStatusList();
        TweetDeleter tweetDeleter = new TweetDeleter();
        tweetDeleter.authenticate();
        bulkDeleter.delete(statusIdList, tweetDeleter);
    }

    private List<Long> loadStatusList() {
        SourceType sourceType = SourceType.values()[Config.getPropertyAsInt(Constants.SOURCE_TYPE_KEY)];
        logger.info("Get sourceType as {}", sourceType);
        String filePath = Config.getPropertyAsString(Constants.STATUS_LIST_FILE_NAME_LOCATION_KEY);
        logger.info("Get filePath as {}", filePath);
        Loader loader = SourceLoaderFactory.getLoader(sourceType);

        return loader.load(filePath);
    }

    public void logDeletedCount() {
        bulkDeleter.logDeletedCount();
    }
}
