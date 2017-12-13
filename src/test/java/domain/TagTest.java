package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TagTest {

    private Tag tag;

    @Before
    public void setUp() throws Exception {
        tag = new Tag(1, "tagi");
    }

    @Test
    public void getId() {
        assertEquals(1,tag.getId());
    }

    @Test
    public void getTag() {
        assertEquals("tagi", tag.getTag());
    }

    @Test
    public void toStringTest() {
        assertEquals("tagi", tag.toString());
    }


}