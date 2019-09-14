package fileDownloading;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author User 1 on 9/13/2019
 * @project filedownloading
 */
@RestController
public class Controller {

    private static Logger logger = Logger.getLogger(Controller.class);

    @Autowired
    private FileDetectorService fileDetectorService;

    @RequestMapping("/")
    public String getHome() {
        logger.debug("Home page successful.");
        return "<h1 style='text-align:center;margin-top: 20%;'>File downloader Bot is online</h1>";
    }

    @RequestMapping("/download")
    public void getFile(HttpServletResponse response) throws IOException {
        logger.info("Download is started.");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=downloadVideos.zip");
        response.setStatus(HttpServletResponse.SC_OK);
        List<String> fileNames = fileDetectorService.getAllFileNames();
        try (ZipOutputStream zippedOut = new ZipOutputStream(response.getOutputStream())) {
            logger.info(fileNames.size() + " files found.");
            logger.info("Creating a zip file out of " + fileNames.size() + " files.");
            for (String file : fileNames) {
                FileSystemResource resource = new FileSystemResource(file);
                ZipEntry e = new ZipEntry(Objects.requireNonNull(resource.getFilename()));
                // Configure the zip entry, the properties of the file
                e.setSize(resource.contentLength());
                e.setTime(System.currentTimeMillis());
                // etc.
                zippedOut.putNextEntry(e);
                // And the content of the resource:
                StreamUtils.copy(resource.getInputStream(), zippedOut);
                zippedOut.closeEntry();
            }
            zippedOut.finish();
            logger.info("Download finalized!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
