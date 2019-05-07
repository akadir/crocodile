package com.kadir.twitterbots.crocodile.loader;

import com.kadir.twitterbots.crocodile.enumeration.SourceType;
import com.kadir.twitterbots.crocodile.exceptions.UnknownSourceException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author akadir
 * Date: 2019-05-06
 * Time: 20:42
 */
public class SourceLoaderFactory {
    private static Map<SourceType, Loader> loaderMap;

    static {
        loaderMap = new HashMap<>();
        loaderMap.put(SourceType.STATUS_ID_LIST, new SourceLoaderFromStatusIdFile());
    }

    private SourceLoaderFactory() {

    }

    public static Loader getLoader(SourceType sourceType) {
        Loader loader = loaderMap.get(sourceType);

        if (loader == null) {
            throw new UnknownSourceException(sourceType);
        } else {
            return loader;
        }
    }
}
