package tools;

import domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class LukuvinkkiToolsTest {
    @Test
    public void pairListsByType() throws Exception {
        ArrayList<Lukuvinkki> testLukuvinkkiList = new ArrayList<>();
        testLukuvinkkiList.add(new Kirja(1, "a", "a", "a", true));
        testLukuvinkkiList.add(new Kirja(2, "b", "b", "b", true));
        testLukuvinkkiList.add(new Blogi(1, "c", "c", "c", true));
        testLukuvinkkiList.add(new Blogi(2, "d", "d", "d", true));
        HashMap<Type, ArrayList<IndexIdPair>> byTypeMap = LukuvinkkiTools.pairListsByType(testLukuvinkkiList);

        assertEquals("K1",byTypeMap.get(Type.KIRJA).get(0).getId());
        assertEquals(1,byTypeMap.get(Type.KIRJA).get(0).getIndex());

        assertEquals("K2",byTypeMap.get(Type.KIRJA).get(1).getId());
        assertEquals(2,byTypeMap.get(Type.KIRJA).get(1).getIndex());

        assertEquals("B1",byTypeMap.get(Type.BLOGI).get(0).getId());
        assertEquals(3,byTypeMap.get(Type.BLOGI).get(0).getIndex());

        assertEquals("B2",byTypeMap.get(Type.BLOGI).get(1).getId());
        assertEquals(4,byTypeMap.get(Type.BLOGI).get(1).getIndex());
    }

    @Test
    public void getLukuvinkkiById() throws Exception {
        Lukuvinkki testLukuvinkki1 = new Kirja(1, "a", "a", "a", true);
        Lukuvinkki testLukuvinkki2 = new Kirja(2, "b", "b", "b", true);
        ArrayList<Lukuvinkki> testLukuvinkit = new ArrayList<>();
        testLukuvinkit.add(testLukuvinkki1);
        testLukuvinkit.add(testLukuvinkki2);
        assertEquals("K1", LukuvinkkiTools.getLukuvinkkiById("K1", testLukuvinkit).getID());
    }

    @Test
    public void getLukuvinkkiByIdDifferentTypes() throws Exception {
        Lukuvinkki testLukuvinkki1 = new Kirja(1, "a", "a", "a", true);
        Lukuvinkki testLukuvinkki2 = new Blogi(1, "b", "b", "b", true);
        ArrayList<Lukuvinkki> testLukuvinkit = new ArrayList<>();
        testLukuvinkit.add(testLukuvinkki1);
        testLukuvinkit.add(testLukuvinkki2);
        assertEquals("B1", LukuvinkkiTools.getLukuvinkkiById("B1", testLukuvinkit).getID());
    }

    @Test
    public void getLukuvinkkiIdByIndex() throws Exception {
        ArrayList<IndexIdPair> testList = new ArrayList<>();
        testList.add(new IndexIdPair(1, "K1"));
        testList.add(new IndexIdPair(2, "K2"));
        testList.add(new IndexIdPair(3, "B1"));
        testList.add(new IndexIdPair(4, "B2"));
        assertEquals("K1", LukuvinkkiTools.getLukuvinkkiIdByIndex(1,testList));
        assertEquals("K2", LukuvinkkiTools.getLukuvinkkiIdByIndex(2,testList));
        assertEquals("B1", LukuvinkkiTools.getLukuvinkkiIdByIndex(3,testList));
        assertEquals("B2", LukuvinkkiTools.getLukuvinkkiIdByIndex(4,testList));
    }

    @Test
    public void parseTypeFromId() throws Exception {
        assertEquals(Type.KIRJA, LukuvinkkiTools.parseTypeFromId("K1"));
        assertEquals(Type.BLOGI, LukuvinkkiTools.parseTypeFromId("B1"));
    }

}