package user_interface;

import data_access.DbKirjaDAO;
import domain.Kirja;
import domain.Lukuvinkki;
import java.sql.SQLException;
import data_access.KirjaDAO;

public class TextUI {

    private IO io;
    private KirjaDAO dao;

    public TextUI(IO io, KirjaDAO dao) {
        this.io = io;
        this.dao = dao;
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
                    break;
                case "2":
                    //Tänne blogin lisäys
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
                    int index = 1;
                    for (Lukuvinkki l : dao.getAll()) {
                        io.println(index + ". " + l);
                        io.println();
                        index++;
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
        dao.save(kirja);

    }
}
