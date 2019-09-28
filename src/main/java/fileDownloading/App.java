package fileDownloading;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Timer;

/**
 * @author User 1 on 9/13/2019
 * @project filedownloading
 */
@SpringBootApplication
public class App {
    private static Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

        logger.info("App started");
        Timer timer = new Timer();
        timer.schedule(new ConsoleMessage(), 0, 2000);
    }
}
