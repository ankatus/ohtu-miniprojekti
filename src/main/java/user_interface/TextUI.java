package user_interface;

import java.util.Scanner;

import data_access.KirjaDAO;
import domain.Lukuvinkki;

public class TextUI {
	public void run(Scanner scanner, KirjaDAO dao) {
		System.out.println("Hello!");

		loop:
		while (true) {

			System.out.println("Komento (1=lisää, 2=listaa, x=lopeta):");
			String input = scanner.nextLine();

			switch (input) {

				case "1":
					//tänne kirjan lisääminen
					System.out.println("toimintoa ei ole vielä toteutettu");
					break;

				case "2":
					int index = 1;
					for (Lukuvinkki l : dao.getAll()) {
						System.out.println(index + ". " + l);
						System.out.println();
						index++;
					}
					System.out.println();
					break;

				case "x":
					break loop;
			}
		}
		System.out.println("Kiitos ja näkemiin!");
	}
}