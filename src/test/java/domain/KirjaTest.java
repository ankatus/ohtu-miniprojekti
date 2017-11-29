
package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class KirjaTest {
    
    Kirja kirja;
    
    public KirjaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kirja = new Kirja(1, "testi", "testaaja", "12345678");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void kirjaToString(){
        assertEquals("testaaja             | testi                | 12345678             | ", kirja.toString());
    }
    
    @Test
    public void id(){
        assertEquals("K1", kirja.getID());
    }
}
