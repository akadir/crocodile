package com.kadir.twitterbots.crocodile.enumeration;

/**
 * @author akadir
 * Date: 2019-05-07
 * Time: 18:53
 */
public enum TwitterErrorCode {
    STATUS_NOT_FOUND(144);

    private int value;

    TwitterErrorCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
