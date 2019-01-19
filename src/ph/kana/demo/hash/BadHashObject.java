package ph.kana.demo.hash;

public class BadHashObject implements StringValueSupplier {

    private final String value;

    public BadHashObject(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return value;
    }
}
