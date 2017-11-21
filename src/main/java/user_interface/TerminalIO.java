package user_interface;

import java.util.Scanner;

public class TerminalIO implements IO{

    private Scanner scanner;

    public TerminalIO() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

    @Override
    public void println(String output) {
        System.out.println(output);
    }

    @Override
    public void println() {
        println("");
    }
}
