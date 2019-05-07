package com.kadir.twitterbots.crocodile.enumeration;

/**
 * @author akadir
 * Date: 2019-05-06
 * Time: 19:24
 */
public enum SourceType {
    STATUS_ID_LIST, TWITTER_DATA_JS;

    private final int value;

    SourceType() {
        this.value = ordinal();
    }
}
