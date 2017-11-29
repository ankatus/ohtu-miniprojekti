package data_access;

import org.junit.*;
import domain.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DbKirjaDAOTest {

    private ResultSet testRs;
    private Database mockDb;
    private DbKirjaDAO kirjaDAO;

    @Before
    public void setUp() throws SQLException {
        mockDb = mock(Database.class);
        kirjaDAO = new DbKirjaDAO(mockDb);

        testRs = mock(ResultSet.class);
        when(testRs.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(testRs.getString("otsikko")).thenReturn("Testikirja").thenReturn("Testikirja2").thenReturn("Testikirja3");
        when(testRs.getString("kirjoittaja")).thenReturn("Testi, Testaaja").thenReturn("Test, Test").thenReturn("Asdf, Asdf");
        when(testRs.getString("isbn")).thenReturn("123456").thenReturn("654321").thenReturn("987654");
    }

    @Test
    public void saveTest() {
        Kirja kirja = new Kirja("Testikirja", "Testi, Testaaja", "123456");
        
        kirjaDAO.save(kirja);
        
        ArrayList values = new ArrayList();
        values.add("Testikirja");
        values.add("Testi, Testaaja");
        values.add("123456"); 
        
        verify(mockDb).executeQueryUpdate("INSERT INTO Kirja "
                + "(otsikko, kirjoittaja, isbn)"
                + " VALUES (?, ?, ?)", values);
        verify(mockDb).closeConnection();
    }

    @Test
    public void getAllTest() throws SQLException {
        when(mockDb.executeQuerySelect(anyString())).thenReturn(testRs);

        ArrayList<Lukuvinkki> list = kirjaDAO.getAll();

        assertEquals(3, list.size());
        assertEquals(new Kirja("Testikirja", "Testi, Testaaja", "123456").toString(), list.get(0).toString());
        assertEquals(new Kirja("Testikirja2", "Test, Test", "654321").toString(), list.get(1).toString());
        assertEquals(new Kirja("Testikirja3", "Asdf, Asdf", "987654").toString(), list.get(2).toString()); 
        verify(mockDb).closeConnection();
    }
}
