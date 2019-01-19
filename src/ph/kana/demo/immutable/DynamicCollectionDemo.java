package ph.kana.demo.immutable;

import ph.kana.demo.common.SampleFiles;
import ph.kana.demo.common.Timer;

import java.util.ArrayList;
import java.util.List;

import static ph.kana.demo.common.Printer.printBreakLine;

public class DynamicCollectionDemo {

    public static void main(String[] args) throws Exception {
        var data = SampleFiles.SORTED
            .getData(200_000);
        printBreakLine();

        new Timer("Append to List")
            .execute(() -> appendToList(data));

        new Timer("Append to Array[?]")
            .execute(() -> appendToArray(data));
    }

    private static void appendToList(List<String> data) {
        var words = new ArrayList<>();
        for (var word : data) {
            words.add(word.toUpperCase());
        }
        System.out.println("Added words: " + words.size());
    }

    private static void appendToArray(List<String> data) {
        String[] words = new String[0];

        for (var word : data) {
            var oldLength = words.length;
            var temp = new String[oldLength + 1];

            System.arraycopy(words, 0, temp, 0, oldLength);
            temp[oldLength] = word;

            words = temp;
        }
        System.out.println("Added words: " + words.length);
    }
}
