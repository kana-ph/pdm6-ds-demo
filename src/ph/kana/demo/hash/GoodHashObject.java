package ph.kana.demo.hash;

public class GoodHashObject implements StringValueSupplier {

    private final String value;

    public GoodHashObject(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
