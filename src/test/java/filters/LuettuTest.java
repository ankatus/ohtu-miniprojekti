package filters;

import domain.Kirja;
import domain.Lukuvinkki;
import org.junit.Test;

import static org.junit.Assert.*;

public class LuettuTest {
    @Test
    public void luettuLukuvinkkiReturnsTrue() throws Exception {
        Lukuvinkki l = new Kirja(1, "a", "a", "a", true);
        assertTrue(new Luettu().matches(l));
    }

    @Test
    public void lukematonLukuvinkkiReturnsFalse() throws Exception {
        Lukuvinkki l = new Kirja(1, "a", "a", "a", false);
        assertTrue(!new Luettu().matches(l));
    }

}