package user_interface;

import data_access.BlogiDAO;
import domain.Kommentti;
import data_access.KommenttiDAO;
import domain.Kirja;
import domain.Lukuvinkki;
import java.sql.SQLException;
import java.util.HashMap;

import data_access.KirjaDAO;
import tools.TextTools;

public class TextUI {

    private IO io;
    private KirjaDAO kirjaDAO;
    private KommenttiDAO kommenttiDAO;
    private BlogiDAO blogiDAO;

    public TextUI(IO io, KirjaDAO kirjaDao, BlogiDAO blogiDAO, KommenttiDAO kommenttiDAO) {
        this.io = io;
        this.kirjaDAO = kirjaDao;
        this.kommenttiDAO = kommenttiDAO;
        this.blogiDAO = blogiDAO;
    }

    private void addRun() throws SQLException {
        addloop:
        while (true) {

            io.println("Valitse lisättävä tyyppi");
            io.println("Komento (1=kirja, x=palaa):");
            String input = io.nextLine();

            switch (input) {

                case "1":
                    // Tänne kirjan lisääminen
                    addBook();
                    break;
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
                    HashMap indexMap = list();
                    chooseLukuvinkki(indexMap);
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
    private void chooseLukuvinkki(HashMap<Integer, Lukuvinkki> indexMap) {
        while (true) {
            io.println();
            io.println("Haluatko tarkastella lukuvinkkiä?");
            io.println("Anna kohteen indeksi, listaa uudestaan (\"l\") tai palaa (\"x\")");
            String input = io.nextLine();

            if (input.toLowerCase().equals("x")) {
                return;
            }
            if (input.toLowerCase().equals("l")) {
                list();
                continue;
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

    //listaa lukuvinkit (vain kirjat atm) ja palauttaa HashMapin, jossa avaimina listan indeksit ja arvoina lukuvinkit
    private HashMap<Integer, Lukuvinkki> list() {
        HashMap<Integer, Lukuvinkki> indexMap = new HashMap<>();
        int index = 1;
        io.println("kirjat:");
        io.println("   " + TextTools.createHeaders(20, "kirjoittaja", "otsikko", "ISBN"));
        for (Lukuvinkki l : kirjaDAO.getAll()) {
            io.println(index + ". " + l);
            indexMap.put(index, l);
            index++;
        }
        io.println("blogit");
        io.println("   " + TextTools.createHeaders(20, "headeri"));
        for (Lukuvinkki l : blogiDAO.getAll()) {
            io.println(index + ". " + l);
            index++;
            indexMap.put(index, l);
        }
        io.println();
        return indexMap;
    }

    private void viewLukuvinkki(Lukuvinkki l) {
        io.println(l.toString());
        io.println();
        io.println("Kommentit:");
        for (Kommentti k : kommenttiDAO.getAllForID(l.getID())) {
            io.println(k.toString());
            io.println();
        }
        while (true) {
            io.println();
            io.println("Komento (x=palaa):");
            String input = io.nextLine();
            if (input.toLowerCase().equals("x")) {
                return;
            } else {
                io.println("Tuntematon komento");
            }
        }
    }
}
