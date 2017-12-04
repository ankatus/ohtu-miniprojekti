package tools;

public class TextTools {

    public static String fit(String s, int length) {
        if (s.length() > length) {
            s = s.substring(length - 1);
        } else {
            int difference = length - s.length();
            for (int i = 0; i < difference; i++) {
                s += " ";
            }
        }
        return s;
    }

    public static String createCharacters(char c, int amount) {
        String s = "";
        for (int i = 0; i < amount; i++) {
            s += c;
        }
        return s;
    }

    public static String createHeaders(int length, String... headers) {
        String headersRow = "";
        for (String s : headers) {
            headersRow += fit(s, length);
            headersRow += "   ";
        }
        return headersRow;
    }
}
