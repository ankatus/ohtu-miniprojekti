package data_access;

import domain.Tag;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DbTagDAOTest {

    private ResultSet testRs;
    private Database mockDb;
    private DbTagDAO tagDAO;

    @Before
    public void setUp() throws SQLException {
        mockDb = mock(Database.class);
        tagDAO = new DbTagDAO(mockDb);

        testRs = mock(ResultSet.class);
        when(testRs.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(testRs.getInt("id")).thenReturn(1).thenReturn(2).thenReturn(3);
        when(testRs.getString("tag")).thenReturn("a").thenReturn("b").thenReturn("c");

    }

    @Test
    public void saveTag() {
        String tag = "tagi";

        tagDAO.saveTag(tag);

        ArrayList values = new ArrayList();
        values.add(tag);

        verify(mockDb).executeQueryUpdate("INSERT INTO Tag "
                + "(tag)"
                + " VALUES (?)", values);
        verify(mockDb).closeConnection();
    }

    @Test
    public void save() {
        String lukuvinkki_id = "K1";
        int tag_id = 1;

        tagDAO.save(lukuvinkki_id, tag_id);

        ArrayList values = new ArrayList<>();
        values.add(lukuvinkki_id);
        values.add(tag_id);

        verify(mockDb).executeQueryUpdate("INSERT INTO Taglink "
                + "(lukuvinkki, tagid)"
                + " VALUES (?, ?)", values);
        verify(mockDb).closeConnection();
    }

    @Test
    public void getAll() {
        when(mockDb.executeQuerySelect(anyString(), anyList())).thenReturn(testRs);
        ArrayList<Tag> tagList = tagDAO.getAll();

        assertEquals(1, tagList.get(0).getId());
        assertEquals("a", tagList.get(0).getTag());

        assertEquals(2, tagList.get(1).getId());
        assertEquals("b", tagList.get(1).getTag());

        assertEquals(3, tagList.get(2).getId());
        assertEquals("c", tagList.get(2).getTag());
    }

    @Test
    public void getAllForId() {
        when(mockDb.executeQuerySelect(anyString(), anyList())).thenReturn(testRs);
        ArrayList<Tag> tagList = tagDAO.getAllForId("asdf");

        assertEquals(1, tagList.get(0).getId());
        assertEquals("a", tagList.get(0).getTag());

        assertEquals(2, tagList.get(1).getId());
        assertEquals("b", tagList.get(1).getTag());

        assertEquals(3, tagList.get(2).getId());
        assertEquals("c", tagList.get(2).getTag());
    }
}