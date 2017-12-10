package task2.helpers;

public class WordFormatHelper {
    public static String getCorrectForm(int n) {
        String s = "ламп";
        if (n < 5 || n > 19) {
            switch (n % 10) {
                case 1:
                    s = "лампа";
                    break;
                case 2:
                case 3:
                case 4:
                    s = "лампы";
                    break;
            }
        }
        return s;
    }
}
