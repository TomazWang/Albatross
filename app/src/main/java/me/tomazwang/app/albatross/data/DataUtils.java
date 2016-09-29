package me.tomazwang.app.albatross.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by TomazWang on 2016/9/29.
 */

public class DataUtils {

    public static String DATE_FORMATE = "yyyy-MM-dd";
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMATE);

    public static String dateToString(Date date) {
        return simpleDateFormat.format(date);
    }


    public static Date stringToDate(String dateText) throws ParseException{
            return simpleDateFormat.parse(dateText);
    }
}
