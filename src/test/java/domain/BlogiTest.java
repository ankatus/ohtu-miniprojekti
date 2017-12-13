
package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BlogiTest {
    Blogi blogi;
    
    public BlogiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        blogi = new Blogi(1, "testi", "testaaja", "testi.fi", false);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void view() {
        assertEquals(
                "Otsikko:            testi\n" +
                        "Kirjoittaja:        testaaja\n" +
                        "url:                testi.fi\n" +
                        "luettu:             ei",
                        blogi.view()
        );
    }

    @Test
    public void blogiToString(){
        assertEquals("blogi                | testaaja             | testi                | ei                  ", blogi.toString());
    }
    
    @Test
    public void id(){
        assertEquals("B1", blogi.getID());
    }
    
    @Test
    public void getterit(){
        assertEquals("testi", blogi.getOtsikko());
        assertEquals("testaaja", blogi.getKirjoittaja());
        assertEquals("testi.fi", blogi.getUrl());
    }
    
    @Test
    public void setterit(){
        blogi.setId(2);
        blogi.setOtsikko("teste");
        blogi.setKirjoittaja("testaaje");
        blogi.setUrl("testi.fe");
        assertEquals("blogi                | testaaje             | teste                | ei                  ", blogi.toString());
        assertEquals("B2", blogi.getID());
    }
    
}
