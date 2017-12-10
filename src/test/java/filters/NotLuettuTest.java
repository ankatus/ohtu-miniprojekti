package filters;

import domain.Blogi;
import domain.Lukuvinkki;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotLuettuTest {

    @Test
    public void luettuLukuvinkkiReturnsFalse() {
        Lukuvinkki l = new Blogi(1, "a", "a", "a", true);
        assertTrue(!new NotLuettu().matches(l));
    }

    @Test
    public void lukematonLukuvinkkiReturnsTrue() {
        Lukuvinkki l = new Blogi(1, "a", "a", "a", false);
        assertTrue(new NotLuettu().matches(l));
    }
}