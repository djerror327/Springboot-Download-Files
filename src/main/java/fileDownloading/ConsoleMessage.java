package fileDownloading;

import org.apache.log4j.Logger;

import java.util.TimerTask;

/**
 * @author User 1 on 9/14/2019
 * @project filedownloading
 */
public class ConsoleMessage extends TimerTask {
    private static Logger logger = Logger.getLogger(ConsoleMessage.class);
    private static int prg = 1;
    private static int svcNo = 1;

    @Override
    public void run() {

        if (prg <= 100) {
            logger.info("Service is running... Service No : " + svcNo + " Progress : " + prg + "% Do not close the terminal");
        } else {
            prg = 0;
            svcNo += 1;
        }
        prg += 1;
    }
}
