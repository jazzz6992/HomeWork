package task2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String s = "ygigkuih/jhgk/uyyuj.hvfhjbevh.xml";
        System.out.println(getFormat(s));
    }

    public static String getFormat(String file) {
        Pattern pattern = Pattern.compile("\\.[\\w]+$");
        Matcher matcher = pattern.matcher(file);
        if (matcher.find()) {
            String res = matcher.group().substring(1);
            return res;
        }
        return null;
    }
}
