package ph.kana.demo.map;

import ph.kana.demo.common.SampleFiles;
import ph.kana.demo.common.Timer;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static ph.kana.demo.common.Printer.printBreakLine;

public class IndexedSearchDemo {

    public static void main(String[] args) throws Exception {
        long firstId = 1L;
        long lastId = 370_099L;
        long unknownId = 750_000L;

        var data = buildWordDb();

        var cache = new Timer("Build Cache")
            .execute(() -> buildCache(data));
        printBreakLine();

        new Timer("Search using Hash Tables (Early Result)")
            .execute(() -> searchWithHashTable(cache, firstId));
        new Timer("Search using Hash Tables (Late Result)")
            .execute(() -> searchWithHashTable(cache, lastId));
        new Timer("Search using Hash Tables (No Result)")
            .execute(() -> searchWithHashTable(cache, unknownId));

        printBreakLine();

        new Timer("Sequential Search (Early Result)")
            .execute(() -> sequentialSearch(firstId));
        new Timer("Sequential Search (Late Result)")
            .execute(() -> sequentialSearch(lastId));
        new Timer("Sequential Search (No Result)")
            .execute(() -> sequentialSearch(unknownId));
    }

    private static Map<Long, WordData> buildCache(List<WordData> data) {
        return data.stream()
            .collect(Collectors.toMap(WordData::getId, identity()));
    }

    private static void searchWithHashTable(Map<Long, WordData> cache, long id) {
        var data = cache.getOrDefault(id, WordData.NULL);
        System.out.println("Fetch data: " + data.getData());
    }

    private static void sequentialSearch(long id ) {
        String searchString = String.format("%d,", id);

        try {
            var data = SampleFiles.INDEXED
                .getData()
                .stream()
                .filter(line -> line.startsWith(searchString))
                .findFirst()
                .map(line -> line.split(","))
                .map(splitLine -> new WordData(Integer.parseInt(splitLine[0]), splitLine[1]))
                .orElse(WordData.NULL);
            System.out.println("Fetch data: " + data.getData());
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    private static List<WordData> buildWordDb() throws Exception {
        return SampleFiles.INDEXED
            .getData()
            .stream()
            .map(line -> line.split(","))
            .map(data -> new WordData(Integer.parseInt(data[0]), data[1]))
            .collect(toList());
    }
}
