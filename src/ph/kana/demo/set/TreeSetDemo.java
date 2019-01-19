package ph.kana.demo.set;

import ph.kana.demo.common.SampleFiles;
import ph.kana.demo.common.Timer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import static ph.kana.demo.common.Printer.printBreakLine;

public class TreeSetDemo {

    public static void main(String[] args) throws Exception {
        var inputs = SampleFiles.SHUFFLED
            .getData(100_000);
        printBreakLine();

        new Timer("Insert to TreeSet")
            .execute(() -> insertToTreeSet(inputs));

        new Timer("Insert then Sort")
            .execute(() -> insertThenSort(inputs));
    }

    private static void insertThenSort(List<String> inputs) {
        var data = new ArrayList<String>();

        for (var word : inputs) {
            data.add(word.toUpperCase());
            Collections.sort(data);
        }

        System.out.println("Insert then sort first word: " + data.get(0));
        System.out.println("Insert then sort last word: " + data.get(data.size()-1));
    }

    private static void insertToTreeSet(List<String> inputs) {
        var data = new TreeSet<String>();

        for (var word : inputs) {
            data.add(word.toUpperCase());
        }

        System.out.println("TreeSet insertion first word: " + data.first());
        System.out.println("TreeSet insertion last word: " + data.last());
    }
}
