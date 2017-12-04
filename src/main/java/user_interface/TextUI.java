package user_interface;

import data_access.BlogiDAO;
import domain.*;
import data_access.KommenttiDAO;

import java.sql.SQLException;
import java.util.HashMap;

import data_access.KirjaDAO;
import tools.TextTools;

public class TextUI {

    private IO io;
    private KirjaDAO kirjaDAO;
    private KommenttiDAO kommenttiDAO;
    private BlogiDAO blogiDAO;
    private HashMap<Integer, String> lukuvinkkiIdIndex;

    public TextUI(IO io, KirjaDAO kirjaDao, BlogiDAO blogiDao, KommenttiDAO kommenttiDAO) {
        this.io = io;
        this.kirjaDAO = kirjaDao;
        this.kommenttiDAO = kommenttiDAO;
        this.blogiDAO = blogiDao;
        lukuvinkkiIdIndex = new HashMap<>();
    }

    private void addRun() throws SQLException {
        addloop:
        while (true) {

            io.println("Valitse lisättävä tyyppi");
            io.println("Komento (1=kirja, 2=blogi, x=palaa):");
            String input = io.nextLine();

            switch (input) {

                case "1":
                    addBook();
                    break addloop;
                case "2":
                    addBlogi();
                    break addloop;
                //Tähän väliin muut tyypit
                case "x":
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

            io.println("Komento (1=lisää, 2=listaa, x=lopeta):");
            String input = io.nextLine();

            switch (input) {

                case "1":
                    addRun();
                    break;

                case "2":
                    chooseLukuvinkki();
                    break;

                case "x":
                    break loop;

                default:
                    io.println("Tuntematon komento.");
                    break;

            }
        }
        io.println("Kiitos ja näkemiin!");
    }

    //ottaa parametrinaan HashMapin, joka sisältää listan indeksit ja niitä vastaavat lukuvinkit
    private void chooseLukuvinkki() throws SQLException {
        while (true) {
            HashMap<Integer, Lukuvinkki> indexMap = list();
            io.println();
            io.println("Haluatko tarkastella lukuvinkkiä?");
            io.println("Anna kohteen indeksi tai palaa (\"x\")");
            String input = io.nextLine();

            if (input.toLowerCase().equals("x")) {
                return;
            }
            int wantedIndex;
            try {
                wantedIndex = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                io.println("Tuntematon komento");
                continue;
            }
            if (indexMap.containsKey(wantedIndex)) {
                viewLukuvinkki(indexMap.get(wantedIndex));
            } else {
                io.println("Ei lukuvinkkiä tällä indeksillä");
                continue;
            }
        }


    }

    private void addBook() throws SQLException {
        io.println("Kirjan nimi: ");
        String otsikko = io.nextLine();
        io.println("Tekijän nimi muodossa \"Sukunimi, Etunimi\": ");
        String kirjoittaja = io.nextLine();
        io.println("ISBN-tunnus: ");
        String isbn = io.nextLine();
        Kirja kirja = new Kirja(otsikko, kirjoittaja, isbn);
        kirjaDAO.save(kirja);
    }

    private void addBlogi() throws SQLException {
        io.println("Blogin Otsikko: ");
        String otsikko = io.nextLine();
        io.println("Tekijän nimi muodossa \"Sukunimi, Etunimi\": ");
        String kirjoittaja = io.nextLine();
        io.println("URL: ");
        String url = io.nextLine();
        Blogi blogi = new Blogi(otsikko, kirjoittaja, url);
        blogiDAO.save(blogi);

    }

    //listaa lukuvinkit ja palauttaa HashMapin, jossa avaimina listan indeksit ja arvoina lukuvinkit
    private HashMap<Integer, Lukuvinkki> list() {
        HashMap<Integer, Lukuvinkki> indexMap = new HashMap<>();
        int index = 1;
        io.println("kirjat:");
        io.println(TextTools.createCharacters(' ', Integer.toString(index).length())
                + "  "
                + TextTools.createHeaders(20, "kirjoittaja", "otsikko", "ISBN", "luettu")
        );
        for (Lukuvinkki l : kirjaDAO.getAll()) {
            io.println(index + ". " + l);
            indexMap.put(index, l);
            index++;
        }
        io.println();
        io.println("blogit:");
        io.println(TextTools.createCharacters(' ', Integer.toString(index).length())
                + "  "
                + TextTools.createHeaders(20, "kirjoittaja", "otsikko", "url", "luettu")//korvaa blogin tietokenttien nimillä
        );
        for (Lukuvinkki l : blogiDAO.getAll()) {
            io.println(index + ". " + l);
            indexMap.put(index, l);
            index++;
        }
        io.println();
        return indexMap;
    }

    private void viewLukuvinkki(Lukuvinkki l) throws SQLException {
        while (true) {
            io.println(l.toString());
            io.println();
            io.println("Kommentit:");
            for (Kommentti k : kommenttiDAO.getAllForID(l.getID())) {
                io.println(k.toString());
                io.println();
            }
            io.println();
            io.println("Komento (x=palaa, m=merkitse luetuksi, u=uusi kommentti):");
            String input = io.nextLine();
            switch (input.toLowerCase()) {
                case "x":
                    return;
                case "m":
                    //blogiDAO tekee tämän nyt sekä kirjalle että blogille,
                    //pitää ulkoistaa omaan luokkaan joskus varmaan
                    Type type = l.getType();
                    if (type == Type.KIRJA) {
                        kirjaDAO.markAsLuettu(l.getID());
                    } else if (type == Type.BLOGI) {
                        blogiDAO.markAsLuettu(l.getID());
                    }
                    break;
                case "u":
                    addKommentti(l);
                    break;
                default:
                    io.println("tuntematon komento");
            }
        }
    }

    private void addKommentti(Lukuvinkki l) {
        io.println("kommentoijan nimi:");
        String kommentoija = io.nextLine();
        io.println("kommentti:");
        String kommentti = io.nextLine();
        kommenttiDAO.save(l.getID(), new Kommentti(kommentoija, kommentti));
    }
}
