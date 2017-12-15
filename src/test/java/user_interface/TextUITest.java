package user_interface;

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
//        testIO = new StubIO("2", "a", "x", "x");
//        tyyppiIO = new StubIO("1","5","1","Kirja","tekija","222", "1", "2", "asd", "asd", "asd.com", "x", "1", "x");
//        KirjaDAO testkDAO = new StubKirjaDAO();
//        BlogiDAO testbDAO = new StubBlogiDAO();
//        runUI = new TextUI(testIO, testkDAO, testbDAO, new StubKommenttiDAO());
//        tyyppiUI = new TextUI(tyyppiIO, testkDAO, testbDAO, new StubKommenttiDAO());
    }

    @Test
    public void runTest() throws Exception {
//       StubKirja apuk = new StubKirja();
//       StubBlogi apub = new StubBlogi();
//       runUI.run();
//       assertEquals("Hello!", testIO.outputs.get(0));
//       assertEquals("Komento (1=lisää, 2=listaa, x=lopeta):", testIO.outputs.get(1));
//       assertEquals("kirjat:", testIO.outputs.get(2));
//       assertEquals("   kirjoittaja            otsikko                ISBN                   luettu                 ", testIO.outputs.get(3));
//       assertEquals("1. " + apuk.toString(), testIO.outputs.get(4));
//       assertEquals("2. " + apuk.toString(), testIO.outputs.get(5));
//       assertEquals("", testIO.outputs.get(6));
//       assertEquals("blogit:", testIO.outputs.get(7));
//       assertEquals("   kirjoittaja            otsikko                url                    luettu                 ", testIO.outputs.get(8));
//       assertEquals("3. " + apub.toString(), testIO.outputs.get(9));
//       assertEquals("4. " + apub.toString(), testIO.outputs.get(10));
//       assertEquals("", testIO.outputs.get(11));
//       assertEquals("", testIO.outputs.get(12));
//       assertEquals("Haluatko tarkastella lukuvinkkiä?", testIO.outputs.get(13));
//       assertEquals("Anna kohteen indeksi tai palaa (\"x\")", testIO.outputs.get(14));
//       assertEquals("Tuntematon komento", testIO.outputs.get(15));
//       assertEquals("kirjat:", testIO.outputs.get(16));
//       assertEquals("   kirjoittaja            otsikko                ISBN                   luettu                 ", testIO.outputs.get(17));
//       assertEquals("1. " + apuk.toString(), testIO.outputs.get(18));
//       assertEquals("2. " + apuk.toString(), testIO.outputs.get(19));
//       assertEquals("", testIO.outputs.get(20));
//       assertEquals("blogit:", testIO.outputs.get(21));
//       assertEquals("   kirjoittaja            otsikko                url                    luettu                 ", testIO.outputs.get(22));
//       assertEquals("3. " + apub.toString(), testIO.outputs.get(23));
//       assertEquals("4. " + apub.toString(), testIO.outputs.get(24));
//       assertEquals("", testIO.outputs.get(25));
//       assertEquals("", testIO.outputs.get(26));
    }

    @Test
    public void kirjanValinta() throws Exception {
//        tyyppiUI.run();
//        assertEquals("Hello!", tyyppiIO.outputs.get(0));
//        assertEquals("Komento (1=lisää, 2=listaa, x=lopeta):", tyyppiIO.outputs.get(1));
//        assertEquals("Valitse lisättävä tyyppi", tyyppiIO.outputs.get(2));
//        assertEquals("Komento (1=kirja, 2=blogi, x=palaa):", tyyppiIO.outputs.get(3));
//        assertEquals("Tuntematon komento.", tyyppiIO.outputs.get(4));
//        assertEquals("Valitse lisättävä tyyppi", tyyppiIO.outputs.get(5));
//        assertEquals("Komento (1=kirja, 2=blogi, x=palaa):", tyyppiIO.outputs.get(6));
//        assertEquals("Kirjan nimi: ", tyyppiIO.outputs.get(7));
//        assertEquals("Tekijän nimi muodossa \"Sukunimi, Etunimi\": ", tyyppiIO.outputs.get(8));
//        assertEquals("ISBN-tunnus: ", tyyppiIO.outputs.get(9));
//        assertEquals("Komento (1=lisää, 2=listaa, x=lopeta):", tyyppiIO.outputs.get(10));
    }
    
    @Test
    public void bloginValinta() throws Exception{
//        tyyppiUI.run();
//        assertEquals("Hello!", tyyppiIO.outputs.get(0));
//        assertEquals("Komento (1=lisää, 2=listaa, x=lopeta):", tyyppiIO.outputs.get(1));
//        assertEquals("Valitse lisättävä tyyppi", tyyppiIO.outputs.get(2));
//        assertEquals("Komento (1=kirja, 2=blogi, x=palaa):", tyyppiIO.outputs.get(3));
//        assertEquals("Blogin Otsikko: ", tyyppiIO.outputs.get(13));
//        assertEquals("Tekijän nimi muodossa \"Sukunimi, Etunimi\": ", tyyppiIO.outputs.get(14));
//        assertEquals("URL: ", tyyppiIO.outputs.get(15));
//        assertEquals("Komento (1=lisää, 2=listaa, x=lopeta):", tyyppiIO.outputs.get(16));
    }

}
