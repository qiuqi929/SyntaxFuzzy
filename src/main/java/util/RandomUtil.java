package util;

import java.util.Random;

public class RandomUtil {

    private static Random random = new Random();

    private static final int methodNameLength = 7;

    public static String randomName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('$');
        String elements = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < methodNameLength; i++) {
            stringBuilder.append(elements.charAt(random.nextInt(52)));
        }
        return stringBuilder.toString();
    }

    public static String randomInt() {
        return random.nextInt() + "";
    }

    public static String randomLong() {
        return random.nextLong() + "L";
    }

    public static String randomByte() {
        return random.nextInt(256) - 128 + "";
    }

    public static String randomShort() {
        return random.nextInt(65536) - 32768 + "";
    }

    public static String randomFloat() {
        return random.nextFloat() + "F";
    }

    public static String randomDouble() {
        return random.nextDouble() + "";
    }


    public static String randomValue(String type) {
        switch (type) {
            case "string":
                return randomName();
            case "int":
                return randomInt();
            case "long":
                return randomLong();
            case "byte":
                return randomByte();
            case "short":
                return randomShort();
            case "float":
                return randomFloat();
            case "double":
                return randomDouble();
        }
        return null;
    }
}
