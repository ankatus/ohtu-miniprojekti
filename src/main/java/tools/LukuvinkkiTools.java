package tools;

import domain.IndexIdPair;
import domain.Lukuvinkki;
import domain.Type;
import filters.Filter;

import java.util.ArrayList;
import java.util.HashMap;

public class LukuvinkkiTools {


    public static ArrayList<IndexIdPair> getFilteredIndexedList(ArrayList<Lukuvinkki> lukuvinkkiList, Filter... filters) {
        ArrayList<IndexIdPair> filteredList = new ArrayList<>();
        int index = 1;
        for (Lukuvinkki l : lukuvinkkiList) {
            if (runFilters(l, filters)) {
                filteredList.add(new IndexIdPair(index, l.getID()));
                index++;
            }
        }
        return filteredList;
    }

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

    public static String getLukuvinkkiIdByIndex(int index, ArrayList<IndexIdPair> pairList) {
            for (IndexIdPair pair : pairList) {
                if (pair.getIndex() == index) {
                    return pair.getId();
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

    private static boolean runFilters(Lukuvinkki l, Filter... filters) {
        for (Filter filter : filters) {
            if (!filter.matches(l)) {
                return false;
            }
        }
        return true;
    }



}
