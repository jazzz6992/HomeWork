package task1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println(zipper("hellowoooorldhellowoooorldhellowoooorldhellowoooorld"));
        System.out.println(zipper("hellowoooo   rldhellowo   oorldhellowo  ooorldhellow.....oooorldhhh"));

    }

    public static String zipper(String s) {
        Pattern pattern = Pattern.compile("(.)\\1+");
        Matcher matcher = pattern.matcher(s);
        StringBuilder sb = new StringBuilder(s);
        int shift = 0;
        while (matcher.find()) {
            sb.replace(matcher.start() + 1 - shift, matcher.end() - shift, String.valueOf(matcher.end() - matcher.start()));
            shift += matcher.end() - matcher.start() - 2;
        }
        return sb.toString();
    }
}
