package utils;

import java.util.Random;

public class RandomUtil {

    /**
     * I suggest that methodName: xxxxMethod. -> Then we can know this is a method and we need to print it as methodName().
     */

    private static Random random = new Random();

    private static String elements = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final int methodNameLength = 7;

    public static String randomMethodName() {
        String methodName = "";
        for (int i = 0; i < methodNameLength; i++) {
            int number = random.nextInt(52);
            methodName += String.valueOf(elements.charAt(number));
        }
        methodName = methodName + "Method";
        return methodName;
    }

    public static int randomInt() {
        return randomInt(-100, 100);
    }

    public static int randomInt(int start, int end) {
        return start + random.nextInt(end - start);
    }

}
