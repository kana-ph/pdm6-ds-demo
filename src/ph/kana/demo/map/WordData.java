package ph.kana.demo.map;

public class WordData {

    private final long id;
    private final String data;

    public final static WordData NULL = new WordData(-1, "<NULL>");

    public WordData(long id, String data) {
        this.id = id;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return String.format("{id:%d, data:%s}", id, data);
    }
}
