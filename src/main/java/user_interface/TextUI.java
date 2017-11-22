package user_interface;

import data_access.KirjaDAO;
import domain.Lukuvinkki;

public class TextUI {

	private IO io;
	private KirjaDAO dao;

	public TextUI(IO io, KirjaDAO dao) {
		this.io = io;
		this.dao = dao;
	}
        
        public void addRun(){
            addloop:
		while (true) {
                    
                    io.println("Valitse lisättävä tyyppi");
                    io.println("Komento (1=kirja, x=palaa)");
                    String input = io.nextLine();
                    
                    switch (input){
                        
                        case "1":
                            // Tänne kirjan lisääminen
                            io.println("Tässä lisättäisiin kirja");
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

	public void run() {

		io.println("Hello!");

		loop:
		while (true) {

			io.println("Komento (1=lisää, 2=listaa, x=lopeta):");
			String input = io.nextLine();

			switch (input) {

				case "1":
					//tänne tyypin valinta
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
}