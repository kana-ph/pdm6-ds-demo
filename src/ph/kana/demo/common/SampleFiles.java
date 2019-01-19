package ph.kana.demo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public enum SampleFiles {
    SORTED("words_alpha.txt"),
    SHUFFLED("words_alpha_shuffled.txt"),
    DUPLICATED("words_alpha_duplicated.txt"),
    INDEXED("indexed_words.txt"),
    PHONE_NUMBERS("country_codes.csv")
    ;

    private final File file;
    SampleFiles(String filename) {
        file = new File(filename);
    }

    public List<String> getData() throws IOException {
        return getData(1_000_000);
    }

    public List<String> getData(long limit) throws IOException {
        var inputs = Files.lines(file.toPath())
            .limit(limit)
            .collect(Collectors.toList());
        System.out.println("Total number for inputs: " + inputs.size());
        return inputs;
    }
}
