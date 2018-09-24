package rental.util;

import java.util.concurrent.atomic.AtomicLong;

public class Constant {
    public static final int SECONDS_PER_DAY = 3600 * 24;
    public static final String format = "yyyy-MM-dd HH:mm:ss";
    private static final AtomicLong reservationNum = new AtomicLong(5);
}
