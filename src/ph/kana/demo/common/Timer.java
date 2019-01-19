package ph.kana.demo.common;

import java.util.function.Supplier;

public class Timer {

    private final String name;

    public Timer(String name) {
        this.name = name;
    }

    public void execute(Runnable task) {
        execute((Supplier<Void>) () -> {
            task.run();
            return null;
        });
    }

    public <T> T execute(Supplier<T> task) {
        System.out.println(buildMessage(name, "Started"));

        long start = System.currentTimeMillis();
        T result = task.get();
        long end = System.currentTimeMillis();

        var time = computeTime(end-start);
        System.out.println(buildMessage(name, "Completed: " + time));
        System.out.println();

        return result;
    }

    private static String computeTime(long elapsed) {
        if (elapsed < 1000) {
            return String.format("%d ms", elapsed);
        } else if (elapsed < 60_000) {
            return String.format("~%d seconds", elapsed / 1000);
        } else {
            var minutes = elapsed / 60_000;
            var seconds = (elapsed - (minutes * 60_000)) / 1000;
            return String.format("~%dm, %ds", minutes, seconds);
        }
    }

    private String buildMessage(String prefix, String suffix) {
        var message = new StringBuilder(prefix);
        int length = 80;

        int padding = length - (prefix.length() + suffix.length());
        for (int i = 0; i < padding; i++) {
            message.append('.');
        }
        message.append(suffix);

        return message.toString();
    }
}
