package com.kadir.twitterbots.crocodile.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akadir
 * Date: 2019-05-06
 * Time: 20:42
 */
public class SourceLoaderFromStatusIdFile implements Loader {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Long> load(String filePath) {
        List<String> statusIdList;
        Path file = Paths.get(filePath);
        try {
            statusIdList = Files.readAllLines(file);
            logger.info("{} status ids loaded from file {}", statusIdList.size(), filePath);
            return statusIdList.stream().map(Long::parseLong).collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("An error occurred while content loaded from file: ", e);
        }

        return new ArrayList<>();
    }
}
