package com.kadir.twitterbots.crocodile.exceptions;

import com.kadir.twitterbots.crocodile.enumeration.SourceType;

/**
 * @author akadir
 * Date: 2019-05-06
 * Time: 21:12
 */
public class UnknownSourceException extends RuntimeException {
    private static final String MESSAGE = "Unknown Source Exception: ";

    public UnknownSourceException(SourceType sourceType) {
        super(MESSAGE + sourceType);
    }

}