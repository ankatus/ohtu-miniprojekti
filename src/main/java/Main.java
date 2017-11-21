import data_access.StubKirjaDAO;
import domain.StubKirja;
import user_interface.TerminalIO;
import user_interface.TextUI;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		TextUI ui = new TextUI(new TerminalIO(), new StubKirjaDAO());
		ui.run();
	}
}