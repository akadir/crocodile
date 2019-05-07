package com.kadir.twitterbots.crocodile.filter;

import twitter4j.Status;

/**
 * @author akadir
 * Date: 2019-05-06
 * Time: 18:12
 */
public interface StatusFilter {

    boolean passed(Status status);
}
