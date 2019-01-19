package ph.kana.demo.set;

import ph.kana.demo.common.SampleFiles;
import ph.kana.demo.common.Timer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class UniqueListDemo {

    public static void main(String[] args) throws Exception {
        var inputs = SampleFiles.DUPLICATED
            .getData(200_000);

        new Timer("Remove Duplicates using Set")
            .execute(() -> removeDuplicatesWithSet(inputs));

        new Timer("Remove duplicates by checking")
            .execute(() -> removeDuplicatesByChecks(inputs));
    }

    private static void removeDuplicatesWithSet(List<String> words) {
        var data = new HashSet<>(words);

        System.out.println("Deduplicated data size: " + data.size());
    }

    private static void removeDuplicatesByChecks(List<String> words) {
        var data = new ArrayList<String>();

        for (var word : words) {
            if (!data.contains(word)) {
                data.add(word);
            }
        }

        System.out.println("Deduplicated data size: " + data.size());
    }
}
