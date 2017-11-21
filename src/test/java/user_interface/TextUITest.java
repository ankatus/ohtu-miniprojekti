package user_interface;

import data_access.DAO;
import data_access.KirjaDAO;
import data_access.StubKirjaDAO;
import domain.StubKirja;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextUITest {

    StubIO testIO;
    TextUI ui;

    @Before
    public void setUp() throws Exception {
        testIO = new StubIO("1","2","a","x");
        DAO testDAO = new StubKirjaDAO();
        ui = new TextUI(testIO, testDAO);
    }

    @Test
    public void runTest() throws Exception {
        StubKirja apu = new StubKirja();
        ui.run();
        assertEquals("Hello!", testIO.outputs.get(0));
        assertEquals("Komento (1=lisää, 2=listaa, x=lopeta):", testIO.outputs.get(1));
        assertEquals("toimintoa ei ole vielä toteutettu", testIO.outputs.get(2));
        assertEquals("Komento (1=lisää, 2=listaa, x=lopeta):", testIO.outputs.get(3));
        assertEquals("1. " + apu.toString(), testIO.outputs.get(4));
        assertEquals("", testIO.outputs.get(5));
        assertEquals("2. " + apu.toString(), testIO.outputs.get(6));
        assertEquals("", testIO.outputs.get(7));
        assertEquals("3. " + apu.toString(), testIO.outputs.get(8));
        assertEquals("", testIO.outputs.get(9));
        assertEquals("4. " + apu.toString(), testIO.outputs.get(10));
        assertEquals("", testIO.outputs.get(11));
        assertEquals("5. " + apu.toString(), testIO.outputs.get(12));
        assertEquals("", testIO.outputs.get(13));
        assertEquals("6. " + apu.toString(), testIO.outputs.get(14));
        assertEquals("", testIO.outputs.get(15));
        assertEquals("", testIO.outputs.get(16));
        assertEquals("Komento (1=lisää, 2=listaa, x=lopeta):", testIO.outputs.get(17));
        assertEquals("Tuntematon komento.", testIO.outputs.get(18));
        assertEquals("Komento (1=lisää, 2=listaa, x=lopeta):", testIO.outputs.get(19));
        assertEquals("Kiitos ja näkemiin!", testIO.outputs.get(20));
    }

}