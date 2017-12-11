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

    public MasterDAO(KirjaDAO kirjaDAO, BlogiDAO blogiDAO, KommenttiDAO kommenttiDAO, VideoDAO videoDAO) {
        this.kirjaDAO = kirjaDAO;
        this.blogiDAO = blogiDAO;
        this.kommenttiDAO = kommenttiDAO;
        this.videoDAO = videoDAO;
    }

    public ArrayList<Lukuvinkki> getAllLukuvinkki() {
        ArrayList<Lukuvinkki> all = new ArrayList<>();
        all.addAll(kirjaDAO.getAll());
        all.addAll(blogiDAO.getAll());
        all.addAll(videoDAO.getAll());
        return all;
    }
    
    public ArrayList<Lukuvinkki> getAllLukuvinkkiBoolean(int i){
        ArrayList<Lukuvinkki> vinkit = new ArrayList<>();
        ArrayList<Lukuvinkki> apu = getAllLukuvinkki();
        for (Lukuvinkki l: apu){
            if (l.getLuettu()==i){
                vinkit.add(l);
            }
        }
        return vinkit;
    }
    

    public void saveLukuvinkki(Lukuvinkki lukuvinkki) {
        if (lukuvinkki.getType() == Type.KIRJA) {
            kirjaDAO.save((Kirja) lukuvinkki);
        } else if (lukuvinkki.getType() == Type.BLOGI) {
            blogiDAO.save((Blogi) lukuvinkki);
        } else if (lukuvinkki.getType() == Type.VIDEO) {
            videoDAO.save((Video) lukuvinkki);
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


}
