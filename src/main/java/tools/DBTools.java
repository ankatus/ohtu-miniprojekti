package tools;

public class DBTools {

    public static String parseIdToTable(String id) {
        char letter = id.toCharArray()[0];
        if (letter == 'K') {
            return "kirja";
        } else if (letter == 'B') {
            return "blogi";
        }
        return null;
    }
}