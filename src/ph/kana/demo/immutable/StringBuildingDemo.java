package ph.kana.demo.immutable;

import ph.kana.demo.common.SampleFiles;
import ph.kana.demo.common.Timer;

import java.util.List;

public class StringBuildingDemo {

    public static void main(String[] args) throws Exception {
        var words = SampleFiles.SORTED
            .getData(200_000);

        new Timer("Large String using StringBuilder")
            .execute(() -> buildWithStringBuilder(words));

        new Timer("Large String using Join")
            .execute(() -> buildWithJoin(words));

        new Timer("Large String using Concat")
            .execute(() -> buildWithConcat(words));
    }

    private static void buildWithStringBuilder(List<String> words) {
        StringBuilder builder = new StringBuilder();
        words.forEach(builder::append);
        String string = builder.toString();

        System.out.println("String character count: " + string.length());
    }

    private static void buildWithJoin(List<String> words) {
        String string = String.join("", words);
        System.out.println("String character count: " + string.length());
    }

    private static void buildWithConcat(List<String> words) {
        String string = "";

        for (var word : words) {
            string += word;
        }
        System.out.println("String character count: " + string.length());
    }
}
