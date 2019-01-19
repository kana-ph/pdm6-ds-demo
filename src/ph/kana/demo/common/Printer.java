package ph.kana.demo.common;

public class Printer {

    public static void printBreakLine() {
        for (int i = 0; i < 84; i++) {
            System.out.print('_');
        }
        System.out.println();
    }
}
