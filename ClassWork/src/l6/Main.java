package l6;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("dd-MM");
        String test = format.format(date);
        System.out.println(test);

        String inputDate = "2017-10-05 17-30";
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH-mm");
        try {
            Date date1 = format1.parse(inputDate);
            System.out.println(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        System.out.println("task");
        Date dateTask = new Date();
        String suffix = "th' ";
        switch (dateTask.getDate()) {
            case 1:
            case 21:
            case 31:
                suffix = "st' ";
                break;
            case 2:
            case 22:
                suffix = "nd' ";
                break;
            case 3:
            case 23:
                suffix = "rd' ";
                break;
        }
        String pattern = "MMM d'" + suffix + "h:mm a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String res = simpleDateFormat.format(dateTask);
        System.out.println(res);


        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(dateTask);
        int day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        int month = calendar.get(GregorianCalendar.MONTH);
        System.out.println(day);
        System.out.println(month);
        calendar.add(GregorianCalendar.MONTH, 1);
        day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        month = calendar.get(GregorianCalendar.MONTH);
        System.out.println(day);
        System.out.println(month);
        calendar.add(GregorianCalendar.MONTH, 1);
        day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        month = calendar.get(GregorianCalendar.MONTH);
        System.out.println(day);
        System.out.println(month);

    }
}
