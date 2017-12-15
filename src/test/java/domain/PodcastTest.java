package domain;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PodcastTest {

    Podcast podcast;

    @Before
    public void setUp() {
        podcast = new Podcast(1, "Testiotsikko", "Testaaja", "testi.fi", false);
    }

    @Test
    public void getTest() {
        assertEquals("P1", podcast.getID());
        assertEquals("Testiotsikko", podcast.getOtsikko());
        assertEquals("Testaaja", podcast.getTekija());
        assertEquals("testi.fi", podcast.getUrl());
        assertEquals(false, podcast.getLuettu());
        assertEquals(Type.PODCAST, podcast.getType());
    }

    @Test
    public void setTest() {
        podcast.setId(2);
        podcast.setOtsikko("Testiotsikko2");
        podcast.setTekija("Testaaja2");
        podcast.setUrl("testi2.fi");
        podcast.setKuunneltu(true);

        assertEquals("P2", podcast.getID());
        assertEquals("Testiotsikko2", podcast.getOtsikko());
        assertEquals("Testaaja2", podcast.getTekija());
        assertEquals("testi2.fi", podcast.getUrl());
        assertEquals(true, podcast.getLuettu());
    }

    @Test
    public void view() {
        assertEquals(
                "Otsikko:            Testiotsikko\n"
                + "Tekijä:             Testaaja\n"
                + "url:                testi.fi\n"
                + "Kuunneltu:          ei",
                podcast.view()
        );
    }

    @Test
    public void toStringTest() {
        assertEquals("podcast              | Testaaja             | Testiotsikko         | ei                  ", podcast.toString());
        podcast.setKuunneltu(true);
        assertEquals("podcast              | Testaaja             | Testiotsikko         | kyllä               ", podcast.toString());
    }

}
