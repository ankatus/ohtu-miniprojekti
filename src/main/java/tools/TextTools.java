package tools;

import domain.Type;

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


    public static String createLabelForType(Type type) {
        if (type == Type.KIRJA) {
            return "Kirjat:";
        } else if (type == Type.BLOGI) {
            return "Blogit:";
        } else {
            return null;//uusia tyyppejä odotellessa
        }
    }

    private static String createHeaders(int length, String... headers) {
        String headersRow = fit("index", 10);
        for (String s : headers) {
            headersRow += fit(s, length);
            headersRow += "   ";
        }
        return headersRow;
    }

    public static String createHeadersForType(int length, Type type) {
        String[] headers;
        if (type == Type.KIRJA) {
            headers = new String[]{"kirjoittaja", "otsikko", "ISBN", "luettu"};
        } else if (type == Type.BLOGI) {
            headers = new String[]{"kirjoittaja", "otsikko", "url", "luettu"};
        } else {
            headers = new String[]{};//uusia tyyppejä odotellessa
        }
        return createHeaders(length, headers);
    }
}
