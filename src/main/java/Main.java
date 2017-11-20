import data_access.StubKirjaDAO;
import user_interface.TextUI;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		TextUI ui = new TextUI();
		ui.run(new Scanner(System.in), new StubKirjaDAO());
	}
}