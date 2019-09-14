package fileDownloading;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author User 1 on 9/13/2019
 * @project filedownloading
 */

@Service
class FileDetectorService {
    private static Logger logger = Logger.getLogger(FileDetectorService.class);
    @Autowired
    Environment env;

    List<String> getAllFileNames() throws IOException {
        logger.info("Detecting file path!");
        logger.info("Copping files from  : " + env.getProperty("app.downloadPath"));
        Stream<Path> walk = Files.walk(Paths.get(Objects.requireNonNull(env.getProperty("app.downloadPath"))));
        return walk.filter(Files::isRegularFile).map(Path::toString).collect(Collectors.toList());
    }
}
