package user_interface;

import data_access.DbKirjaDAO;
import data_access.DbBlogiDAO;
import domain.Kirja;
import domain.Blogi;
import domain.Lukuvinkki;
import java.sql.SQLException;
import data_access.KirjaDAO;
import data_access.BlogiDAO;

public class TextUI {

    private IO io;
    private KirjaDAO kirjaDao;
    private BlogiDAO blogiDao;

    public TextUI(IO io, KirjaDAO KirjaDao, BlogiDAO BlogiDao) {
        this.io = io;
        this.kirjaDao = KirjaDao;
        this.blogiDao = BlogiDao;
    }

    public void addRun() throws SQLException {
        addloop:
        while (true) {

            io.println("Valitse lisättävä tyyppi");
            io.println("Komento (1=kirja, 2=blogi, x=palaa)");
            String input = io.nextLine();

            switch (input) {

                case "1":
                    // Tänne kirjan lisääminen
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
                    //Kirjojen tulostus
                    io.println("Kirjat:");
                    io.println();
                    for (Lukuvinkki l : kirjaDao.getAll()) {
                        io.println(l.toString());
                    }
                    io.println();
                    //Blogien tulostus
                    io.println("Blogit:");
                    io.println();
                    for (Lukuvinkki l : blogiDao.getAll()) {
                        io.println(l.toString());
                    }
                    io.println();
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

    private void addBook() throws SQLException {
        io.println("Kirjan nimi: ");
        String otsikko = io.nextLine();
        io.println("Tekijän nimi muodossa \"Sukunimi, Etunimi\": ");
        String kirjoittaja = io.nextLine();
        io.println("ISBN-tunnus: ");
        String isbn = io.nextLine();
        Kirja kirja = new Kirja(otsikko, kirjoittaja, isbn);
        kirjaDao.save(kirja);

    }

    private void addBlogi() throws SQLException {
        io.println("Blogin Otsikko: ");
        String otsikko = io.nextLine();
        io.println("Tekijän nimi muodossa \"Sukunimi, Etunimi\": ");
        String kirjoittaja = io.nextLine();
        io.println("URL: ");
        String url = io.nextLine();
        Blogi blogi = new Blogi(otsikko, kirjoittaja, url);
        blogiDao.save(blogi);

    }
}
