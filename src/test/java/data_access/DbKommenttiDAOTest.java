
package data_access;

import domain.Kommentti;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class DbKommenttiDAOTest {
    
    private DbKommenttiDAO kommenttiDAO;
    private Database mockDb;
    private ResultSet testRs;
    
    @Before
    public void setUp() throws SQLException {
        mockDb = mock(Database.class);
        kommenttiDAO = new DbKommenttiDAO(mockDb);
        
        testRs = mock(ResultSet.class);
        when(testRs.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(testRs.getString("kommentoija")).thenReturn("Kommentoija1").thenReturn("Kommentoija2").thenReturn("Kommentoija3");
        when(testRs.getString("kommentti")).thenReturn("kommentti1").thenReturn("kommentti2").thenReturn("kommentti3");       
    }
    
    @Test
    public void saveTest() {
        Kommentti kommentti = new Kommentti("Testaaja", "testi kommentti");
        
        kommenttiDAO.save("2", kommentti);
        
        ArrayList values = new ArrayList();
        values.add("2");
        values.add("Testaaja");
        values.add("testi kommentti");
        
        verify(mockDb).executeQueryUpdate("INSERT INTO Kommentti "
                + "(lukuvinkki, kommentoija, kommentti)"
                + " VALUES (?, ?, ?)", values);
        verify(mockDb).closeConnection();
    }
    
    @Test
    public void getAllKommenttiForLukuvinkkiId() {
        when(mockDb.executeQuerySelect(anyString(), anyList())).thenReturn(testRs);
        
        ArrayList<Kommentti> list = kommenttiDAO.getAllForID("1");
        
        assertEquals(3, list.size());
        assertEquals(new Kommentti("Kommentoija1", "kommentti1").toString(), list.get(0).toString());
        assertEquals(new Kommentti("Kommentoija2", "kommentti2").toString(), list.get(1).toString());
        assertEquals(new Kommentti("Kommentoija3", "kommentti3").toString(), list.get(2).toString());
    }
}
