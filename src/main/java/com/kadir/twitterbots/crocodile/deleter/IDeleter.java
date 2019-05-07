package com.kadir.twitterbots.crocodile.deleter;

import twitter4j.TwitterException;

public interface IDeleter {
    boolean delete(Long id) throws TwitterException, InterruptedException;
}
