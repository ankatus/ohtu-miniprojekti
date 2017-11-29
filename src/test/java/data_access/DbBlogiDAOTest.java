
package data_access;

import domain.Blogi;
import domain.Lukuvinkki;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DbBlogiDAOTest {
    
    private ResultSet testRs;
    private Database mockDb;
    private DbBlogiDAO blogiDAO;
    
    public DbBlogiDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SQLException {
        mockDb = mock(Database.class);
        blogiDAO = new DbBlogiDAO(mockDb);

        testRs = mock(ResultSet.class);
        when(testRs.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(testRs.getString("otsikko")).thenReturn("Testiblogi").thenReturn("Testiblogi2").thenReturn("Testiblogi3");
        when(testRs.getString("kirjoittaja")).thenReturn("Testi, Testaaja").thenReturn("Test, Test").thenReturn("Asdf, Asdf");
        when(testRs.getString("url")).thenReturn("testi.fi").thenReturn("test.com").thenReturn("asd.net");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void saveTest() {
        Blogi blogi = new Blogi("Testiblogi", "Testi, Testaaja", "testi.fi");
        
        blogiDAO.save(blogi);
        
        ArrayList values = new ArrayList();
        values.add("Testiblogi");
        values.add("Testi, Testaaja");
        values.add("testi.fi"); 
        
        verify(mockDb).executeQueryUpdate("INSERT INTO Blogi "
                + "(otsikko, kirjoittaja, url)"
                + " VALUES (?, ?, ?)", values);
        verify(mockDb).closeConnection();
    }

    @Test
    public void getAllTest() throws SQLException {
        when(mockDb.executeQuerySelect(anyString())).thenReturn(testRs);

        ArrayList<Lukuvinkki> list = blogiDAO.getAll();

        assertEquals(3, list.size());
        assertEquals(new Blogi("Testiblogi", "Testi, Testaaja", "testi.fi").toString(), list.get(0).toString());
        assertEquals(new Blogi("Testiblogi2", "Test, Test", "test.com").toString(), list.get(1).toString());
        assertEquals(new Blogi("Testiblogi3", "Asdf, Asdf", "asd.net").toString(), list.get(2).toString()); 
        verify(mockDb).closeConnection();
    }
}
