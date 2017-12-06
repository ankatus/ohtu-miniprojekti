package tools;

import domain.Type;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextToolsTest {
    @Test
    public void fit() throws Exception {
        String testString = "hei";
        assertEquals("hei       ", TextTools.fit(testString, 10));
    }

    @Test
    public void createCharacters() throws Exception {
        assertEquals("aaa", TextTools.createCharacters('a', 3));
    }

    @Test
    public void createLabelForTypeKirja() throws Exception {
        assertEquals("Kirjat:", TextTools.createLabelForType(Type.KIRJA));
    }

    @Test
    public void createLabeLforTypeBlogi() {
        assertEquals("Blogit:", TextTools.createLabelForType(Type.BLOGI));
    }

    @Test
    public void createHeadersForTypeKirja() throws Exception {
        String expected = TextTools.fit("index", 10)
                + TextTools.fit("kirjoittaja", 23)
                + TextTools.fit("otsikko", 23)
                + TextTools.fit("ISBN", 23)
                + TextTools.fit("luettu", 23);
        assertEquals(expected, TextTools.createHeadersForType(20, Type.KIRJA));
    }

    @Test
    public void createHeadersForTypeBlogi() throws Exception {
        String expected = TextTools.fit("index", 10)
                + TextTools.fit("kirjoittaja", 23)
                + TextTools.fit("otsikko", 23)
                + TextTools.fit("url", 23)
                + TextTools.fit("luettu", 23);
        assertEquals(expected, TextTools.createHeadersForType(20, Type.BLOGI));
    }

}