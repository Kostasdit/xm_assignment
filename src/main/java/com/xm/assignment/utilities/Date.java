package com.xm.assignment.utilities;

import org.springframework.stereotype.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Class to take the dates in a specific format
 */
@Component
public class Date {

    /**
     *
     * @return today
     */
    public String getTodayDate(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        DateFormat dateFormat = new SimpleDateFormat("EEEEE, MMMMM dd, yyyy");
        return dateFormat.format(cal.getTime());
    }

    /**
     *
     * @return yesterday
     */
    public String getYesterdayDate(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        DateFormat dateFormat = new SimpleDateFormat("EEEEE, MMMMM dd, yyyy");
        return dateFormat.format(cal.getTime());
    }

    /**
     *
     * @return tomorrow
     */
    public String getTomorrowDate(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        DateFormat dateFormat = new SimpleDateFormat("EEEEE, MMMMM dd, yyyy");
        return dateFormat.format(cal.getTime());
    }

    /**
     *
     * @return this week
     */
    public List<String> getWeekDate(){
        Calendar cal = Calendar.getInstance();
        List<String> date = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("EEEEE, MMMMM dd, yyyy");
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        for(int i=0;i<7;i++){
            cal.add(Calendar.DATE,i);
            date.add(dateFormat.format(cal.getTime()));
            cal.add(Calendar.DATE, -i);
        }
        return date;
    }


}
