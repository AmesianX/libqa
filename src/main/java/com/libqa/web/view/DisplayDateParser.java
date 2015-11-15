package com.libqa.web.view;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DisplayDateParser {
    private static final String FEED_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String parseForFeed(Date date) {
        return new SimpleDateFormat(FEED_DATE_FORMAT).format(date);
    }
}