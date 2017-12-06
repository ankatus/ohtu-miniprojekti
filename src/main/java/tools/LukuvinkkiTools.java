package tools;

import domain.IndexIdPair;
import domain.Lukuvinkki;
import domain.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class LukuvinkkiTools {


    public static HashMap<Type, ArrayList<IndexIdPair>> pairListsByType(ArrayList<Lukuvinkki> lukuvinkkiList) {
        HashMap<Type, ArrayList<IndexIdPair>> mapOfLists = new HashMap<>();
        int index = 1;
        for (Type type : Type.values()) {
            mapOfLists.put(type, pairListSingleType(lukuvinkkiList, type, index));
            index += mapOfLists.get(type).size();//inkrementoidaan indeksiä tarpeeksi
        }
        return mapOfLists;
    }

    public static Lukuvinkki getLukuvinkkiById(String id, ArrayList<Lukuvinkki> lukuvinkkiList) {
        for (Lukuvinkki l : lukuvinkkiList) {
            if (l.getID().equals(id)) {
                return l;
            }
        }
        return null;
    }

    public static String getLukuvinkkiIdByIndex(int index, HashMap<Type, ArrayList<IndexIdPair>> map) {
        for (ArrayList<IndexIdPair> list : map.values()) {
            for (IndexIdPair pair : list) {
                if (pair.getIndex() == index) {
                    return pair.getId();
                }
            }
        }
        return null;
    }

    public static Type parseTypeFromId(String id) {
        String typeString = id.substring(0,1);
        switch (typeString) {
            case "K":
                return Type.KIRJA;
            case "B":
                return Type.BLOGI;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static ArrayList<IndexIdPair> pairListSingleType(ArrayList<Lukuvinkki> lukuvinkkiList, Type type, int startIndex) {
        int i = startIndex;
        ArrayList<IndexIdPair> pairList = new ArrayList<>();
        for (Lukuvinkki l : lukuvinkkiList) {
            if (l.getType() == type) {
                pairList.add(new IndexIdPair(i, l.getID()));
                i++;
            }
        }
        return pairList;
    }



}
