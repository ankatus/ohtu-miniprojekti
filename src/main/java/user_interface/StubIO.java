package user_interface;

import java.util.ArrayList;

public class StubIO implements IO {

    String[] inputs;
    int inputIndex;
    ArrayList<String> outputs;


    public StubIO(String... inputs) {
        this.inputs = inputs;
        outputs = new ArrayList<>();
        inputIndex = 0;
    }

    @Override
    public String nextLine() {
        String line = inputs[inputIndex];
        inputIndex++;
        return line;
    }

    @Override
    public void println(String output) {
        outputs.add(output);
    }

    @Override
    public void println() {
        println("");
    }

}
