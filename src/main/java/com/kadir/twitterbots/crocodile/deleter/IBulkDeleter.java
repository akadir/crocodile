package com.kadir.twitterbots.crocodile.deleter;

import java.util.List;

/**
 * @author akadir
 * Date: 2019-05-06
 * Time: 21:00
 */
public interface IBulkDeleter {
    void delete(List<Long> statusList, IDeleter deleter);

    void logDeletedCount();
}
