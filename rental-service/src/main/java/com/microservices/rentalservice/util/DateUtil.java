package com.microservices.rentalservice.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String dateNow() {
        return new SimpleDateFormat("yyyy년 MM월 dd일").format(new Date());
    }
}