//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.util.Assert;

public class DateUtil {
    public DateUtil() {
    }

    public static Date addDate(int field, int amount) {
        return addDate((Date)null, field, amount);
    }

    public static Date addDate(Date date, int field, int amount) {
        if (amount == 0) {
            return date;
        } else {
            Calendar c = Calendar.getInstance();
            if (date != null) {
                c.setTime(date);
            }

            c.add(field, amount);
            return c.getTime();
        }
    }

    public static String format(Date date, String pattern) {
        return date == null ? "" : (new SimpleDateFormat(pattern)).format(date);
    }

    public static Date getDayBegin(Date date) {
        if (date == null) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            setCalender(calendar, 0, 0, 0, 0);
            return calendar.getTime();
        }
    }

    public static Date getDayBegin() {
        return getDayBegin(new Date());
    }

    public static Date getDayEnd(Date date) {
        if (date == null) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            setCalender(calendar, 23, 59, 59, 999);
            return calendar.getTime();
        }
    }

    public static Date getDayEnd() {
        return getDayEnd(new Date());
    }

    public static void setCalender(Calendar calendar, int hour, int minute, int second, int milliSecond) {
        calendar.set(11, hour);
        calendar.set(12, minute);
        calendar.set(13, second);
        calendar.set(14, milliSecond);
    }

    public static boolean isBetween(Date beginTime, Date endTime) {
        Assert.notNull(beginTime, "开始时间不能为空");
        Assert.notNull(endTime, "结束时间不能为空");
        Date now = new Date();
        return beginTime.getTime() <= now.getTime() && now.getTime() <= endTime.getTime();
    }
}
