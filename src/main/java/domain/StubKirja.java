package domain;

public class StubKirja implements Lukuvinkki {

    @Override
    public void setLuettu(boolean luettu) {

    }

    @Override
    public String getID() {
        return null;
    }

    public String toString() {
        String s = "minä olen testiolio " +"\n" +
                "Lorem ipsum dolor sit amet, jne jne jne.";
        return s;
    }
}
