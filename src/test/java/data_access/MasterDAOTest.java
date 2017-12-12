
package data_access;

import domain.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class MasterDAOTest {
    
    private MasterDAO masterDao;
    private KirjaDAO mockKirjaDao;
    private BlogiDAO mockBlogiDao;
    private VideoDAO mockVideoDao;
    private PodcastDAO mockPodcastDao;
    private KommenttiDAO mockKommenttiDao;
    
    @Before
    public void setUp() {
        mockKirjaDao = mock(KirjaDAO.class);
        mockBlogiDao = mock(BlogiDAO.class);
        mockKommenttiDao = mock(KommenttiDAO.class);
        mockVideoDao = mock(VideoDAO.class);
        mockPodcastDao = mock(PodcastDAO.class);
         
        masterDao = new MasterDAO(mockKirjaDao, mockBlogiDao, mockKommenttiDao, mockVideoDao, mockPodcastDao);
    }
    
    @Test
    public void getAllLukuvinkkiTest() {
        ArrayList<Lukuvinkki> allKirjaObjects = new ArrayList<Lukuvinkki>();
        allKirjaObjects.add(new Kirja("Otsikko1", "Kirjoittaja1", "isbn1"));
        allKirjaObjects.add(new Kirja("Otsikko2", "Kirjoittaja2", "isbn2"));
        
        ArrayList<Lukuvinkki> allBlogiObjects = new ArrayList();
        allBlogiObjects.add(new Blogi("Otsikko1", "Kirjoittaja1", "url1"));
        allBlogiObjects.add(new Blogi("Otsikko2", "Kirjoittaja2", "url2"));
 
        when(mockKirjaDao.getAll()).thenReturn(allKirjaObjects);
        when(mockBlogiDao.getAll()).thenReturn(allBlogiObjects);
        
        ArrayList all = masterDao.getAllLukuvinkki();
        
        assertEquals(4, all.size());
        assertEquals(new Kirja("Otsikko1", "Kirjoittaja1", "isbn1").toString(), all.get(0).toString());
        assertEquals(new Kirja("Otsikko2", "Kirjoittaja2", "isbn2").toString(), all.get(1).toString());
        assertEquals(new Blogi("Otsikko1", "Kirjoittaja1", "url1").toString(), all.get(2).toString());
        assertEquals(new Blogi("Otsikko2", "Kirjoittaja2", "url2").toString(), all.get(3).toString());
    }
    
    @Test
    public void saveLukuvinkkiTest() {
        Kirja kirja = new Kirja("otsikko1", "kirjoittaja1", "isbn1");
        masterDao.saveLukuvinkki(kirja);
        verify(mockKirjaDao).save(kirja);
        
        Blogi blogi = new Blogi("ostikko1", "kirjoittaja1", "url1");
        masterDao.saveLukuvinkki(blogi);
        verify(mockBlogiDao).save(blogi);
    }
    
    @Test
    public void saveKommenttiTest() {
        Kommentti kommentti = new Kommentti("kommentoija1", "kommentti1");
        masterDao.saveKommentti("1", kommentti);
        verify(mockKommenttiDao).save("1", kommentti);
    }
    
    @Test
    public void markAsLuettuTest() {
        masterDao.markAsLuettu("K1");
        verify(mockKirjaDao).markAsLuettu("K1");
        
        masterDao.markAsLuettu("B2");
        verify(mockBlogiDao).markAsLuettu("B2");
    }
    
    @Test
    public void getAllKommenttiForIdTest() {
        try {
            masterDao.getAllKommenttiForId("K2");
            verify(mockKommenttiDao).getAllForID("K2");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
