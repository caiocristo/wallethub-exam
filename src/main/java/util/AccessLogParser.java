package util;

import entities.AccessLog;
import entities.Scan;
import service.AccessLogService;
import service.ScanService;

import java.io.*;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Logger;

/**
 * This class load and parse the access.log to database
 */
public class AccessLogParser {

    private static final Logger log = Logger.getLogger(AccessLogParser.class.getName());

    AccessLogService accessLogService = new AccessLogService();
    ScanService scanService = new ScanService();
    int aux =1;


    /**
     * Parse file and save to DB
     */
    public void parse (){

        if(!isScanned()) {
            log.info("initing Access.log scan");
            try {
                File f = new File("access.log");
                BufferedReader b = new BufferedReader(new FileReader(f));
                String readLine = "";
                log.info("Parsing file");

                while ((readLine = b.readLine()) != null) {
                    parseLine(readLine);
                }
                log.info("Done parsing");
                saveScanDate();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            log.info("Access.log already scanned.");
        }

    }

    private void saveScanDate() {
        scanService.save(new Scan(null,new Date()));
    }

    private void parseLine(String readLine) {
        AccessLog accessLog = new AccessLog();
        String date;
        int i = 0;
        StringTokenizer st = new StringTokenizer(readLine, "|");
        while (st.hasMoreElements()) {
            switch (i){
                case 0:
                    date = st.nextToken();
                    accessLog.setDate(DateUtils.parseDateFile(date));
                    break;
                case 1:
                    accessLog.setIp(st.nextToken());
                    break;
                case 2:
                    accessLog.setProtocol(st.nextToken());
                    break;
                case 3:
                    accessLog.setStatusCode(Integer.parseInt(st.nextToken()));
                    break;
                case 4:
                    accessLog.setClient(st.nextToken());
                    break;
            }
            i++;
        }
        accessLogService.save(accessLog);
    }



    public boolean isScanned() {
       if(scanService.findById(1l)!=null){
           return true;
       }else {
           return false;
       }
    }
}
