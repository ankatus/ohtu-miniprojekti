package tools;

import domain.Type;

import static domain.Type.BLOGI;
import static domain.Type.KIRJA;

public class DomainTools {
    public static Type getLukuvinkkiType(String id) {
        String s = id.substring(0,1);
        switch (s) {
            case "K":
                return KIRJA;
            case "B":
                return BLOGI;
            default:
                return null;
        }
    }
}
