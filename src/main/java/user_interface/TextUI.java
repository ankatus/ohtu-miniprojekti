package user_interface;

import data_access.MasterDAO;
import domain.*;

import java.sql.SQLException;
import java.util.ArrayList;

import filters.Filter;
import filters.Luettu;
import filters.NotLuettu;
import tools.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import tools.TextTools;

public class TextUI {

    private IO io;
    private MasterDAO dao;

    public TextUI(IO io, MasterDAO dao) {
        this.io = io;
        this.dao = dao;
    }

    private void addRun() throws SQLException {
        addloop:
        while (true) {

            io.println("Valitse lisättävä tyyppi");
            io.println("Komento (1=kirja, 2=blogi, 3=video, \"\"=palaa):");
            String input = io.nextLine();

            switch (input) {

                case "1":
                    addBook();
                    break addloop;
                case "2":
                    addBlogi();
                    break addloop;
                case "3":
                    addVideo();
                    break addloop;
                //Tähän väliin muut tyypit
                case "":
                    break addloop;
                default:
                    io.println("Tuntematon komento.");
                    break;
            }
        }
    }

    public void run() throws SQLException {

        io.println("Hello!");

        loop:
        while (true) {

            io.println("Komento (1=lisää, 2=listaa kaikki, 3=listaa lukemattomat, 4=listaa luetut, \"\"=lopeta):");
            String input = io.nextLine();

            switch (input) {

                case "1":
                    addRun();
                    break;

                case "2":
                    list();
                    break;
                    
                case "3":
                    list(new NotLuettu());
                    break;
                    
                case "4":
                    list(new Luettu());
                    break;

                case "":
                    break loop;

                default:
                    io.println("Tuntematon komento.");
                    break;

            }
        }
        io.println("Kiitos ja näkemiin!");
    }

    private void addBook() throws SQLException {
        io.println("Kirjan nimi: ");
        String otsikko = io.nextLine();
        String kirjoittaja = addWriter();
        io.println("ISBN-tunnus: ");
        String isbn = io.nextLine();
        Kirja kirja = new Kirja(otsikko, kirjoittaja, isbn);
        dao.saveLukuvinkki(kirja);
    }

    private void addBlogi() throws SQLException {
        io.println("Blogin Otsikko: ");
        String otsikko = io.nextLine();
        String kirjoittaja = addWriter();
        io.println("URL: ");
        String url = io.nextLine();
        Blogi blogi = new Blogi(otsikko, kirjoittaja, url);
        dao.saveLukuvinkki(blogi);

    }
    
    private void addVideo() throws SQLException {
        io.println("Video otsikko: ");
        String otsikko = io.nextLine();
        String tekija = addWriter();
        io.println("URL: ");
        String url = io.nextLine();
        Video video = new Video(otsikko, tekija, url);
        dao.saveLukuvinkki(video);
    }

    private void list(Filter... filters) throws SQLException {
        while (true) {
            ArrayList<Lukuvinkki> lukuvinkkiList = dao.getAllLukuvinkki();
            ArrayList<IndexIdPair> filteredList = LukuvinkkiTools.getFilteredIndexedList(lukuvinkkiList, filters);
            //labelit ja headerit tähän
            printIndexIdPairList(filteredList, lukuvinkkiList);
            io.println();
            io.println("Haluatko tarkastella lukuvinkkiä?");
            io.println("Anna kohteen indeksi tai palaa (\"\")");
            String input = io.nextLine();
            if (input.equals("")) {
                return;
            }
            int wantedIndex = parseToIndex(input);
            if (wantedIndex == -1) {
                io.println("Tuntematon komento");
            }

            String lukuvinkkiId = LukuvinkkiTools.getLukuvinkkiIdByIndex(wantedIndex, filteredList);
            if (lukuvinkkiId == null) {
                io.println("Ei lukuvinkkiä tällä indeksillä");
            } else {
                viewLukuvinkki(lukuvinkkiId);

            }
        }
    }

    private void viewLukuvinkki(String id) throws SQLException {
        while (true) {
            //tarvitaan tietojen päivittämiseen, voidaan tehdä paremmin kun daosta saa yksittäisen vinkin tiedot.
            ArrayList<Lukuvinkki> lukuvinkkiList = dao.getAllLukuvinkki();
            Lukuvinkki current = LukuvinkkiTools.getLukuvinkkiById(id, lukuvinkkiList);

            io.println(current.toString());
            io.println();

            io.println("Kommentit:");
            io.println();

            printAllKommenttiForId(id);

            io.println("Komento (\"\"=palaa, m=merkitse luetuksi, u=uusi kommentti, a=avaa url):");
            String input = io.nextLine();
            switch (input.toLowerCase()) {
                case "":
                    return;
                case "m":
                    dao.markAsLuettu(id);
                    break;
                case "u":
                    addKommentti(id);
                    break;
                case "a":
                    openURLinBrowser(current);
                    break;
                default:
                    io.println("Tuntematon komento");
            }
        }
    }

    private void addKommentti(String id) {
        io.println("kommentoijan nimi:");
        String kommentoija = io.nextLine();
        io.println("kommentti:");
        String kommentti = io.nextLine();
        dao.saveKommentti(id, new Kommentti(kommentoija, kommentti));
    }

    //lukuvinkkilista tarvitaan, jotta saadaan lukuvinkin tiedot. muutetaan kun saadaan daon kautta.
    private void printIndexIdPairList(ArrayList<IndexIdPair> pairList, ArrayList<Lukuvinkki> lukuvinkkiList) {
        for (IndexIdPair pair : pairList) {
            //jos muutat indeksin sarakkeen kokoa, muista muuttaa myös vakiota headerimetodissa!
            io.println(TextTools.fit(pair.getIndex() + ".", 10) + LukuvinkkiTools.getLukuvinkkiById(pair.getId(), lukuvinkkiList));
        }
    }

    private int parseToIndex(String input) {
        int index;
        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;//tieto väärästä inputista
        }
        return index;
    }

    private void printAllKommenttiForId(String id) throws SQLException {
        for (Kommentti k : dao.getAllKommenttiForId(id)) {
            io.println(k.toString());
            io.println();
        }
    }

    public void openURLinBrowser(Lukuvinkki lukuvinkki) {
        try {
            Method method = lukuvinkki.getClass().getMethod("getUrl", (Class<?>[]) null);
            String url = (String) method.invoke(lukuvinkki, (Object[]) null);
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("xdg-open " + url);
        } catch (IOException | NoSuchMethodException | SecurityException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            io.println("Avaaminen ei onnistunut");
        }

    }
    private String addWriter() throws SQLException {
        io.println("Tekijän nimi muodossa \"Sukunimi, Etunimi\": ");
        String kirjoittaja = io.nextLine();
        return kirjoittaja;
    }
}
