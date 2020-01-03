package utils;

import initial.Initialize;

import java.util.Random;

public class ConstantUtil {

    private static Random random = Initialize.random;

    private static String elements = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*(){}|:,./?<>";

    private static final int elementsLength = 72;

    /**
     * Random a constant (String) by type.
     *
     * @param type
     * @return
     */
    public static String randomConstantByType(String type) {
        String constant = null;
        switch (type) {
            case "int":
                constant = randomInt();
                break;
            case "short":
                constant = randomShort();
                break;
            case "byte":
                constant = randomByte();
                break;
            case "char":
                constant = randomChar();
                break;
            case "long":
                constant = randomLong();
                break;
            case "float":
                constant = randomFloat();
                break;
            case "double":
                constant = randomDouble();
                break;
            case "boolean":
                constant = randomBoolean();
                break;
            case "String":
                constant = "\"" + randomString() + "\"";
                break;
        }
        return constant;
    }

    private static String randomInt() {
        int randomInt = getRandomNumberInRange(Integer.MIN_VALUE, Integer.MAX_VALUE);
        return Integer.toString(randomInt);
    }

    private static String randomShort() {
        int randomShort = getRandomNumberInRange(Short.MIN_VALUE, Short.MAX_VALUE);
        return Integer.toString(randomShort);
    }

    private static String randomByte() {
        int randomByte = getRandomNumberInRange(Byte.MIN_VALUE, Byte.MAX_VALUE);
        return Integer.toString(randomByte);
    }

    private static final int stringLength = 10;

    private static String randomString() {
        StringBuilder str = new StringBuilder();
        int length = random.nextInt(stringLength) + 1;
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(elementsLength);
            str.append(String.valueOf(elements.charAt(number)));
        }
        return str.toString();
    }

    private static String randomChar() {
        double probability = random.nextDouble();
        if (probability < 0.5) {
            int randomChar = getRandomNumberInRange(Character.MIN_VALUE, Character.MAX_VALUE);
            return "" + randomChar;
        } else {
            int number = random.nextInt(elementsLength);
            char randomChar = elements.charAt(number);
            return "'" + randomChar + "'";
        }
    }

    public static boolean inKeywords(String word) {
        String[] keywords = new String[]{"if", "else", "while", "try", "catch"};
        for (String keyword : keywords) {
            if (keyword.equals(word)) return true;
        }
        return false;
    }

    private static int getRandomNumberInRange(int min, int max) {
        double probability = random.nextDouble();
        if (probability < 0.5) {
            // random positive number.
            return random.nextInt(max); // 0 ~ max-1
        } else {
            // random negative number.
            return random.nextInt(max) + min + 1; // min+1 ~ max + min
        }
//        return  random.nextInt((max - min) + 1) + min;
    }

    private static String randomLong() {
        long randomLong = random.nextLong();
        return Long.toString(randomLong);
    }

    private static String randomFloat() {
        double probability = random.nextDouble();
        float randomDouble = 0;
        if (probability < 0.5) {
            randomDouble = random.nextFloat() * Float.MAX_VALUE;
        } else {
            randomDouble = random.nextFloat() * Float.MIN_VALUE;
        }
        return Float.toString(randomDouble);
    }

    private static String randomDouble() {
        double probability = random.nextDouble();
        double randomDouble = 0;
        if (probability < 0.5) {
            randomDouble = random.nextDouble() * Double.MAX_VALUE;
        } else {
            randomDouble = random.nextDouble() * Double.MIN_VALUE;
        }
        return Double.toString(randomDouble);
    }


    private static String randomBoolean() {
        boolean aBoolean = random.nextBoolean();
        return Boolean.toString(aBoolean);
    }
}
