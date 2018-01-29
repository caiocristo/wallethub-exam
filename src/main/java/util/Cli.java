package util;

import entities.BlockedAccess;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import service.AccessLogService;
import service.BlockedAccessService;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Command line tools
 */
public class Cli {
    private static final Logger log = Logger.getLogger(Cli.class.getName());
    private String[] args = null;
    private Options options = new Options();

    private String startDate;
    private long threshold;
    private String duration;
    private Date endDate;
    private String accessLog;

    public Cli(String[] args) {

        this.args = args;

        options.addOption("h", "help", false, "show help.");
        options.addOption("accesslog", "al", true, "--accesslog /path/to/file");
        options.addOption("startDate", "sd", true, "--startDate 2017-01-01.13:00:00");
        options.addOption("duration", "d", true, "--duration hourly");
        options.addOption("threshold", "t", true, "--threshold 100");

    }

    public void parse() {
        CommandLineParser parser = new BasicParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);

            if (cmd.hasOption("h"))
                help();

            if (cmd.hasOption("accesslog")) {
                log.log(Level.INFO, "Using cli argument -accesslog=" + cmd.getOptionValue("accesslog"));
                accessLog = cmd.getOptionValue("accesslog");
            } else {
                log.log(Level.SEVERE, "MIssing accesslog option");
                help();
            }

            if (cmd.hasOption("startDate")) {
                log.log(Level.INFO, "Using cli argument -startDate=" + cmd.getOptionValue("startDate"));
                startDate = cmd.getOptionValue("startDate");
            } else {
                log.log(Level.SEVERE, "MIssing startDate option");
                help();
            }

            if (cmd.hasOption("duration")) {
                log.log(Level.INFO, "Using cli argument -duration=" + cmd.getOptionValue("duration"));
                duration = cmd.getOptionValue("duration");
            } else {
                log.log(Level.SEVERE, "MIssing duration option");
                help();
            }

            if (cmd.hasOption("threshold")) {
                log.log(Level.INFO, "Using cli argument -threshold=" + cmd.getOptionValue("threshold"));
                threshold = Integer.valueOf(cmd.getOptionValue("threshold"));
                // Whatever you want to do with the setting goes here
            } else {
                log.log(Level.SEVERE, "MIssing threshold option");
                help();
            }

        } catch (ParseException e) {
            log.log(Level.SEVERE, "Failed to parse comand line properties", e);
            help();
        }


        run();


    }

    private void help() {
        // This prints out some help
        HelpFormatter formater = new HelpFormatter();

        formater.printHelp("Main", options);
        System.exit(0);
    }

    private void run(){
        //read and parse access.log
        AccessLogParser accessLogParser = new AccessLogParser(accessLog);
        accessLogParser.parse();

        search();


        HibernateUtil.shutdown();
    }

    private void search() {
        AccessLogService accessLogService = new AccessLogService();
        BlockedAccessService blockedAccessService = new BlockedAccessService();
        Date beginDate = DateUtils.parseDateInput(startDate);
        List<String> ips = null;

        //search hourly or daily
        if(duration.equalsIgnoreCase("hourly")){
            endDate = DateUtils.addHour(beginDate);
            ips = accessLogService.findByDateAndThreshold(beginDate,endDate,threshold);
            if(ips!=null){
                System.out.println("####---- BLOCKED IPs ----####");
                for (String ip: ips) {
                    blockedAccessService.save(new BlockedAccess(null,ip,"Did more than " +threshold+" requests in one hour" ));
                    System.out.println(ip);
                }
            }
        }else if (duration.equalsIgnoreCase("daily")){
            endDate = DateUtils.addDay(beginDate);
            ips = accessLogService.findByDateAndThreshold(beginDate,endDate,threshold);
            if(ips!=null){
                System.out.println("####---- BLOCKED IPs ----####");
                for (String ip: ips) {
                    blockedAccessService.save(new BlockedAccess(null,ip,"Did more than " +threshold+" requests in one day" ));
                    System.out.println(ip);
                }
            }
        }else{
            help();
        }



    }
}