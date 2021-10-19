package com.microservices.rentalservice.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    public static String dateNow() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new GregorianCalendar().getTime());
    }

    public static boolean matchingDate(String endDate) {
        Calendar calendar = new GregorianCalendar();

        calendar.add(Calendar.DATE, -1);

        return endDate == new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }
}