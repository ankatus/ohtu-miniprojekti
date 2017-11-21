import data_access.Database;
import data_access.KirjaDAO;
import user_interface.TerminalIO;
import user_interface.TextUI;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException {
                TextUI ui = new TextUI(new TerminalIO(), new KirjaDAO(new Database("/src/sql/Database.db")));
		ui.run();
	}
}