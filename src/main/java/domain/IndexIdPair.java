package domain;

public class IndexIdPair {

    private int index;
    private String id;

    public IndexIdPair(int index, String id) {
        this.index = index;
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
