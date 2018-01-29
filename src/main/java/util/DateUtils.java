package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Parse date from file or input
 */
public class DateUtils {
    private static final Logger log = Logger.getLogger(DateUtils.class.getName());

    /**
     * Pattern - yyyy-MM-dd HH:mm:ss.SSS
     * @param date
     * @return
     */
    public static Date parseDateFile(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date parsedDate = null;
        try {
            parsedDate = simpleDateFormat.parse(date);
        } catch (ParseException ex) {
            log.info("Exception "+ex);
        }
        return parsedDate;
    }

    /**
     * Pattern - yyyy-MM-dd.HH:mm:ss
     * @param date
     * @return
     */
    public static Date parseDateInput(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");
        Date parsedDate = null;
        try {
            parsedDate = simpleDateFormat.parse(date);
        } catch (ParseException ex) {
            log.info("Exception "+ex);
        }
        return parsedDate;
    }

    /**
     * Add one hour to a date
     * @param date
     * @return
     */
    public static Date addHour(Date date){
        return addHours(date,1);
    }
    /**
     * Add 24h to a date
     * @param date
     * @return
     */
    public static Date addDay(Date date){
        return addHours(date,24);
    }

    private static Date addHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        cal.add(Calendar.HOUR, hours);

        return new Date(cal.getTimeInMillis());
    }
}

