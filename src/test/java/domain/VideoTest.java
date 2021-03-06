package domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class VideoTest {

    Video video;

    @Before
    public void setUp() {
        video = new Video(1, "Testiotsikko", "Testaaja", "testi.fi", false);
    }

    @Test
    public void getTest() {
        assertEquals("V1", video.getID());
        assertEquals("Testiotsikko", video.getOtsikko());
        assertEquals("Testaaja", video.getTekija());
        assertEquals("testi.fi", video.getUrl());
        assertEquals(false, video.getLuettu());
        assertEquals(Type.VIDEO, video.getType());
    }

    @Test
    public void setTest() {
        video.setId(2);
        video.setOtsikko("Testiotsikko2");
        video.setTekija("Testaaja2");
        video.setUrl("testi2.fi");
        video.setLuettu(true);

        assertEquals("V2", video.getID());
        assertEquals("Testiotsikko2", video.getOtsikko());
        assertEquals("Testaaja2", video.getTekija());
        assertEquals("testi2.fi", video.getUrl());
        assertEquals(true, video.getLuettu());
    }

    @Test
    public void view() {
        assertEquals(
                "Otsikko:            Testiotsikko\n" +
                        "Tekijä:             Testaaja\n" +
                        "url:                testi.fi\n" +
                        "katsottu:           ei",
                video.view()
        );
    }

    @Test
    public void toStringTest() {
        assertEquals("video                | Testaaja             | Testiotsikko         | ei                  ", video.toString());
        video.setLuettu(true);
        assertEquals("video                | Testaaja             | Testiotsikko         | kyllä               ", video.toString());
    }

}
