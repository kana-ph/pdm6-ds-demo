package ph.kana.demo.trie;

import ph.kana.demo.common.SampleFiles;
import ph.kana.demo.common.Timer;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static ph.kana.demo.common.Printer.printBreakLine;

public class TrieDemo {

    public static void main(String[] args) throws Exception {
        var data = SampleFiles.PHONE_NUMBERS
            .getData()
            .stream()
            .map(line -> line.split(","))
            .map(columns -> new PrefixData(columns[0], columns[1]))
            .collect(toMap(PrefixData::getPrefix, identity()));

        var trie = new Timer("Build Trie")
            .execute(() -> buildTrie(data.keySet()));
        System.out.println(trie);

        printBreakLine();
        new Timer("Trie lookup location of phone number: 12017563214")
            .execute(() -> lookupPhoneNumber(data, trie, "12017563214"));
        new Timer("Trie lookup location of phone number: 2631234567")
            .execute(() -> lookupPhoneNumber(data, trie, "2631234567"));
        new Timer("Trie lookup location of phone number: 88621234567")
            .execute(() -> lookupPhoneNumber(data, trie, "88621234567"));
        new Timer("Trie lookup location of phone number: 0000000")
            .execute(() -> lookupPhoneNumber(data, trie, "0000000"));

        printBreakLine();
        new Timer("Brute-forced lookup location of phone number: 12017563214")
            .execute(() -> bruteForceLookup(data, "12017563214"));
        new Timer("Brute-forced lookup location of phone number: 2631234567")
            .execute(() -> bruteForceLookup(data, "2631234567"));
        new Timer("Brute-forced lookup location of phone number: 88621234567")
            .execute(() -> bruteForceLookup(data, "88621234567"));
        new Timer("Brute-forced lookup location of phone number: 0000000")
            .execute(() -> bruteForceLookup(data, "0000000"));

    }

    private static Trie buildTrie(Set<String> prefixes) {
        var trie = new Trie();
        prefixes.forEach(trie::insert);
        return trie;
    }

    private static void lookupPhoneNumber(Map<String, PrefixData> data, Trie trie, String number) {
        var prefix = trie.findPrefix(number);
        var location = data.getOrDefault(prefix, PrefixData.NULL);
        System.out.println(String.format("Location of %s: %s", number, location.getCountry()));
    }

    private static void bruteForceLookup(Map<String, PrefixData> data, String number) {
        var location = data.keySet()
            .stream()
            .filter(number::startsWith)
            .max(Comparator.comparing(String::length))
            .map(data::get)
            .orElse(PrefixData.NULL);
        System.out.println(String.format("Location of %s: %s", number, location.getCountry()));
    }
}
