package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    public static Date getLastPayPeriodStartDate(Date currentDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int daysSinceLastFriday = (dayOfWeek + 2) % 7; // Assuming Sunday is the first day of the week

        // Subtract days to get to the last Friday (start of pay period)
        calendar.add(Calendar.DAY_OF_MONTH, -daysSinceLastFriday);

        // Set time to midnight
        resetTime(calendar);

        return calendar.getTime();
    }

    private static void resetTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
    public static Date parseDate(String dateStr, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}

