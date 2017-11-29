package user_interface;

import data_access.BlogiDAO;
import data_access.DbKirjaDAO;
import data_access.DbBlogiDAO;
import data_access.StubKirjaDAO;
import data_access.StubBlogiDAO;
import domain.StubKirja;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import data_access.KirjaDAO;
import domain.StubBlogi;

public class TextUITest {

    StubIO testIO;
    TextUI runUI;
    TextUI tyyppiUI;
    StubIO tyyppiIO;

    @Before
    public void setUp() throws Exception {
        testIO = new StubIO("2", "a", "x");
        tyyppiIO = new StubIO("1","5","1","Kirja","tekija","222", "1", "2", "asd", "asd", "asd.com","x");
        KirjaDAO kirjaDAO = new StubKirjaDAO();
        BlogiDAO blogiDAO = new StubBlogiDAO();
        runUI = new TextUI(testIO, kirjaDAO, blogiDAO);
        tyyppiUI = new TextUI(tyyppiIO, kirjaDAO, blogiDAO);
    }

    @Test
    public void runTest() throws Exception {
        StubKirja k = new StubKirja();
        StubBlogi b = new StubBlogi();
        runUI.run();
        assertEquals("Hello!", testIO.outputs.get(0));
        assertEquals("Komento (1=lisää, 2=listaa, x=lopeta):", testIO.outputs.get(1));
        assertEquals("Kirjat:", testIO.outputs.get(2));
        assertEquals("", testIO.outputs.get(3));
        assertEquals(k.toString(), testIO.outputs.get(4));
        assertEquals(k.toString(), testIO.outputs.get(5));
        assertEquals("", testIO.outputs.get(6));
        assertEquals("Blogit:", testIO.outputs.get(7));
        assertEquals("", testIO.outputs.get(8));
        assertEquals(b.toString(), testIO.outputs.get(9));
        assertEquals(b.toString(), testIO.outputs.get(10));
        assertEquals("", testIO.outputs.get(11));
        assertEquals("Komento (1=lisää, 2=listaa, x=lopeta):", testIO.outputs.get(12));
        assertEquals("Tuntematon komento.", testIO.outputs.get(13));
        assertEquals("Komento (1=lisää, 2=listaa, x=lopeta):", testIO.outputs.get(14));
        assertEquals("Kiitos ja näkemiin!", testIO.outputs.get(15));
    }

    @Test
    public void kirjanValinta() throws Exception {
        tyyppiUI.run();
        assertEquals("Hello!", tyyppiIO.outputs.get(0));
        assertEquals("Komento (1=lisää, 2=listaa, x=lopeta):", tyyppiIO.outputs.get(1));
        assertEquals("Valitse lisättävä tyyppi", tyyppiIO.outputs.get(2));
        assertEquals("Komento (1=kirja, 2=blogi, x=palaa)", tyyppiIO.outputs.get(3));
        assertEquals("Tuntematon komento.", tyyppiIO.outputs.get(4));
        assertEquals("Valitse lisättävä tyyppi", tyyppiIO.outputs.get(5));
        assertEquals("Komento (1=kirja, 2=blogi, x=palaa)", tyyppiIO.outputs.get(6));
        assertEquals("Kirjan nimi: ", tyyppiIO.outputs.get(7));
        assertEquals("Tekijän nimi muodossa \"Sukunimi, Etunimi\": ", tyyppiIO.outputs.get(8));
        assertEquals("ISBN-tunnus: ", tyyppiIO.outputs.get(9));
        assertEquals("Komento (1=lisää, 2=listaa, x=lopeta):", tyyppiIO.outputs.get(10));
    }
    
    @Test
    public void bloginValinta() throws Exception{
        tyyppiUI.run();
        assertEquals("Hello!", tyyppiIO.outputs.get(0));
        assertEquals("Komento (1=lisää, 2=listaa, x=lopeta):", tyyppiIO.outputs.get(1));
        assertEquals("Valitse lisättävä tyyppi", tyyppiIO.outputs.get(2));
        assertEquals("Komento (1=kirja, 2=blogi, x=palaa)", tyyppiIO.outputs.get(3));
        assertEquals("Blogin Otsikko: ", tyyppiIO.outputs.get(13));
        assertEquals("Tekijän nimi muodossa \"Sukunimi, Etunimi\": ", tyyppiIO.outputs.get(14));
        assertEquals("URL: ", tyyppiIO.outputs.get(15));
        assertEquals("Komento (1=lisää, 2=listaa, x=lopeta):", tyyppiIO.outputs.get(16));
    }

}
