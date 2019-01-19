package ph.kana.demo.trie;

public class PrefixData {

    private final String prefix;
    private final String country;

    public static final PrefixData NULL = new PrefixData(null, "NULL ZONE");

    public PrefixData(String prefix, String country) {
        this.prefix = prefix;
        this.country = country;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getCountry() {
        return country;
    }
}
