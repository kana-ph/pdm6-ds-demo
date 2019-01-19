package ph.kana.demo.hash;

import ph.kana.demo.common.SampleFiles;
import ph.kana.demo.common.Timer;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static ph.kana.demo.common.Printer.printBreakLine;

public class HashDemo {

    public static void main(String[] args) throws Exception {
        var data = SampleFiles.SORTED
            .getData(100_000);
        var firstIndex = 0;
        var midIndex = data.size() / 2;
        var lastIndex = data.size() - 1;

        printBreakLine();

        var goodHashObjects = data.stream()
            .map(GoodHashObject::new)
            .collect(toList());
        var firstGoodObject = goodHashObjects.get(firstIndex);
        var middleGoodObject = goodHashObjects.get(midIndex);
        var lastGoodObject = goodHashObjects.get(lastIndex);

        var goodHashMap = new Timer("Build good HashMap")
            .execute(() -> buildHashMap(goodHashObjects));


        new Timer("Get value from good map (Early result)")
            .execute(() -> System.out.println("Value: " + goodHashMap.get(firstGoodObject)));
        new Timer("Get value from good map (Middle result)")
            .execute(() -> System.out.println("Value: " + goodHashMap.get(middleGoodObject)));
        new Timer("Get value from good map (Late result)")
            .execute(() -> System.out.println("Value: " + goodHashMap.get(lastGoodObject)));

        printBreakLine();

        var badHashObjects = data.stream()
            .map(BadHashObject::new)
            .collect(toList());
        var firstBadObject = badHashObjects.get(firstIndex);
        var middleBadObject = badHashObjects.get(midIndex);
        var lastBadObject = badHashObjects.get(lastIndex);

        var badHashMap = new Timer("Build bad HashMap")
            .execute(() -> buildHashMap(badHashObjects));

        new Timer("Get value from bad map (Early result)")
            .execute(() -> System.out.println("Value: " + badHashMap.get(firstBadObject)));
        new Timer("Get value from bad map (Middle result)")
            .execute(() -> System.out.println("Value: " + badHashMap.get(middleBadObject)));
        new Timer("Get value from bad map (Late result)")
            .execute(() -> System.out.println("Value: " + badHashMap.get(lastBadObject)));
    }

    private static <T extends StringValueSupplier> Map<T, String> buildHashMap(List<T> objects) {
        return objects.stream()
            .collect(toMap(identity(), T::getValue));
    }
}
