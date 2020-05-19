package base;
import java.util.*;

public class Combinatory {
    public static void main(String[] args) throws InterruptedException {
        char[] chs = "radh".toCharArray();
        int maxCount = 3;

//        LinkedList<Character> result = new LinkedList<Character>();
//        combination1(chs, 0, 0, maxCount, result);
        System.out.println("++++++++++++++++++++++++++++++++++");
        combination2(chs, 0, 0, maxCount, "");
    }

    public static void combination1(char[] chs, int index, int count, int maxCount, LinkedList<Character> result) {
        if (count == maxCount) {
            System.out.println(result);
            return;
        }

        for (int i = index; i < chs.length; ++i) {
            result.addLast(chs[i]);
            combination1(chs, i + 1, count + 1, maxCount, result);
            result.removeLast();
        }
    }

    public static void combination2(char[] chs, int index, int count, int maxCount, String result) {
        if (count == maxCount) {
            System.out.println(result);
            return;
        }

        for (int i = index; i < chs.length; ++i) {
            combination2(chs, i + 1, count + 1, maxCount, result + chs[i] + " ");
        }
    }
}