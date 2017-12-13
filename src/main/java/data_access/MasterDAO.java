package data_access;

import domain.*;
import tools.LukuvinkkiTools;

import java.sql.SQLException;
import java.util.ArrayList;

public class MasterDAO {

    private KirjaDAO kirjaDAO;
    private BlogiDAO blogiDAO;
    private KommenttiDAO kommenttiDAO;
    private VideoDAO videoDAO;
    private PodcastDAO podcastDAO;
    private TagDAO tagDAO;

    public MasterDAO(KirjaDAO kirjaDAO, BlogiDAO blogiDAO, 
            KommenttiDAO kommenttiDAO, VideoDAO videoDAO, PodcastDAO podcastDAO, TagDAO tagDAO) {
        this.kirjaDAO = kirjaDAO;
        this.blogiDAO = blogiDAO;
        this.kommenttiDAO = kommenttiDAO;
        this.videoDAO = videoDAO;
        this.podcastDAO = podcastDAO;
        this.tagDAO = tagDAO;
    }

    public ArrayList<Lukuvinkki> getAllLukuvinkki() {
        ArrayList<Lukuvinkki> all = new ArrayList<>();
        all.addAll(kirjaDAO.getAll());
        all.addAll(blogiDAO.getAll());
        all.addAll(videoDAO.getAll());
        all.addAll(podcastDAO.getAll());
        return all;
    }

    public void saveLukuvinkki(Lukuvinkki lukuvinkki) {
        if (lukuvinkki.getType() == Type.KIRJA) {
            kirjaDAO.save((Kirja) lukuvinkki);
        } else if (lukuvinkki.getType() == Type.BLOGI) {
            blogiDAO.save((Blogi) lukuvinkki);
        } else if (lukuvinkki.getType() == Type.VIDEO) {
            videoDAO.save((Video) lukuvinkki);
        } else if (lukuvinkki.getType() == Type.PODCAST) {
            podcastDAO.save((Podcast) lukuvinkki);
        } else {
            //muita tyyppejä odotellessa
        }
    }

    public void saveKommentti(String lukuvinkki_id, Kommentti kommentti) {
        kommenttiDAO.save(lukuvinkki_id, kommentti);
    }

    public void markAsLuettu(String id) {
        Type type = LukuvinkkiTools.parseTypeFromId(id);
        switch (type) {
            case KIRJA:
                kirjaDAO.markAsLuettu(id);
                break;
            case BLOGI:
                blogiDAO.markAsLuettu(id);
                break;
            case VIDEO:
                videoDAO.markAsLuettu(id);
                break;
            default:
                throw new IllegalArgumentException();

        }
    }

    public ArrayList<Kommentti> getAllKommenttiForId(String lukuvinkki_id) throws SQLException {
        return kommenttiDAO.getAllForID(lukuvinkki_id);
    }
    
    public ArrayList<Tag> getAllTagit(){
        return tagDAO.getAll();
    }
    
    public boolean saveTag(String tag){
       ArrayList<Tag> tagit = getAllTagit();
       for (Tag t : tagit){
           if (t.getTag().equals(tag)){
               return false;
           }
       }
       tagDAO.saveTag(tag);
       return true;
    }
    
    public boolean tagToVinkki(String lukuvinkki_id, int tag){
        ArrayList<Tag> tagit = getAllTagForId(lukuvinkki_id);
        for (Tag t : tagit){
           if (t.getId()==tag){
               return false;
           }
        }
        tagDAO.save(lukuvinkki_id, tag);
        return true;
    }
    
    
    
    public ArrayList<Tag> getAllTagForId(String lukuvinkki_id){
        return tagDAO.getAllForId(lukuvinkki_id);
    }
}
