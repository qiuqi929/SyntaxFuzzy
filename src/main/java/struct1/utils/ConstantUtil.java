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
        if (type.equals("int")) {
            constant = randomInt();
        } else if (type.equals("short")) {
            constant = randomShort();
        } else if (type.equals("byte")) {
            constant = randomByte();
        } else if (type.equals("char")) {
            constant = randomChar();
        } else if (type.equals("long")) {
            constant = randomLong();
        } else if (type.equals("float")) {
            constant = randomFloat();
        } else if (type.equals("double")) {
            constant = randomDouble();
        } else if (type.equals("boolean")) {
            constant = randomBoolean();
        } else if (type.equals("String")) {
            constant = "\"" + randomString() + "\"";
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
        String str = "";
        int length = random.nextInt(stringLength) + 1;
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(elementsLength);
            str += String.valueOf(elements.charAt(number));
        }
        return str;
    }

    private static String randomChar() {
        double probability = random.nextDouble();
        if (probability < 0.5) {
            int randomChar = getRandomNumberInRange(Character.MIN_VALUE, Character.MAX_VALUE);
            return Integer.toString(randomChar);
        } else {
            int number = random.nextInt(elementsLength);
            char randomChar = elements.charAt(number);
            return Character.toString(randomChar);
        }
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
