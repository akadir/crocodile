package com.kadir.twitterbots.crocodile.filter;

import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * @author akadir
 * Date: 2019-05-07
 * Time: 17:50
 */
public class FilterManager {
    private static List<StatusFilter> filters;

    private FilterManager() {
    }

    static {
        filters = new ArrayList<>();
        filters.add(new InteractionCountFilter());
    }

    public static boolean filterStatus(Status status) {
        for (StatusFilter filter : filters) {
            if (!filter.passed(status)) {
                return false;
            }
        }

        return true;
    }
}
