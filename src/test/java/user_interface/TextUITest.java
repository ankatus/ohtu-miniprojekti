package user_interface;

import data_access.*;
import domain.StubKirja;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextUITest {

    StubIO testIO;
    TextUI runUI;
    TextUI tyyppiUI;
    StubIO tyyppiIO;

    @Before
    public void setUp() throws Exception {
        testIO = new StubIO("2", "a", "x", "x");
        tyyppiIO = new StubIO("1","5","1","Kirja","tekija","222","x","x");
        KirjaDAO testDAO = new StubKirjaDAO();
        runUI = new TextUI(testIO, testDAO, new StubBlogiDAO(), new StubKommenttiDAO());
        tyyppiUI = new TextUI(tyyppiIO, testDAO, new StubBlogiDAO(), new StubKommenttiDAO());
    }

    @Test
    public void runTest() throws Exception {
//        StubKirja apu = new StubKirja();
//        runUI.run();
//        assertEquals("Hello!", testIO.outputs.get(0));
//        assertEquals("Komento (1=lisää, 2=listaa, x=lopeta):", testIO.outputs.get(1));
//        assertEquals("1. " + apu.toString(), testIO.outputs.get(2));
//        assertEquals("2. " + apu.toString(), testIO.outputs.get(3));
//        assertEquals("3. " + apu.toString(), testIO.outputs.get(4));
//        assertEquals("4. " + apu.toString(), testIO.outputs.get(5));
//        assertEquals("5. " + apu.toString(), testIO.outputs.get(6));
//        assertEquals("6. " + apu.toString(), testIO.outputs.get(7));
//        assertEquals("", testIO.outputs.get(8));
//        assertEquals("", testIO.outputs.get(9));
//        assertEquals("Haluatko tarkastella lukuvinkkiä?", testIO.outputs.get(10));
//        assertEquals("Anna kohteen indeksi, listaa uudestaan (\"l\") tai palaa (\"x\")", testIO.outputs.get(11));
//        assertEquals("Tuntematon komento", testIO.outputs.get(12));
//        assertEquals("", testIO.outputs.get(13));
//        assertEquals("Haluatko tarkastella lukuvinkkiä?", testIO.outputs.get(14));
//        assertEquals("Anna kohteen indeksi, listaa uudestaan (\"l\") tai palaa (\"x\")", testIO.outputs.get(15));
//        assertEquals("Komento (1=lisää, 2=listaa, x=lopeta):", testIO.outputs.get(16));
//        assertEquals("Kiitos ja näkemiin!", testIO.outputs.get(17));
    }

    @Test
    public void tyypinValinta() throws Exception {
        tyyppiUI.run();
        assertEquals("Hello!", tyyppiIO.outputs.get(0));
        assertEquals("Komento (1=lisää, 2=listaa, x=lopeta):", tyyppiIO.outputs.get(1));
        assertEquals("Valitse lisättävä tyyppi", tyyppiIO.outputs.get(2));
        assertEquals("Komento (1=kirja, x=palaa):", tyyppiIO.outputs.get(3));
        assertEquals("Tuntematon komento.", tyyppiIO.outputs.get(4));
        assertEquals("Valitse lisättävä tyyppi", tyyppiIO.outputs.get(5));
        assertEquals("Komento (1=kirja, x=palaa):", tyyppiIO.outputs.get(6));
        assertEquals("Kirjan nimi: ", tyyppiIO.outputs.get(7));
    }

}
