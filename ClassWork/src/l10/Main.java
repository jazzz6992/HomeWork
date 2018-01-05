package l10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

    }

    public static boolean checkCarNumber(String s) {
        Pattern pattern = Pattern.compile("\\d{4} [A-Z]{2}-[1-7]");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    public static boolean checkPhoneMumber(String s) {
        Pattern pattern1 = Pattern.compile("(\\+|810)375(17|29|25|33|44)[0-9]{7}");
        Matcher matcher1 = pattern1.matcher(s);
        return matcher1.matches();
    }
}
