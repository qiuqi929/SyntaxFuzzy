package utils;

import java.util.Random;

public class RandomUtil {

    private static Random random = new Random();

    private static final int methodNameLength = 7;

    private static String elements = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // 由于之后可能会涉及到类的自定义, 到时候会更改typeList的初始化方式, 并且为特定类型的class调用对应的构造器
    private static String[] typeList = new String[]{"int", "long", "short", "byte", "float", "double", "String", "boolean"};

    public static String randomMethodName() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < methodNameLength; i++) {
            int number = random.nextInt(52);
            stringBuilder.append(elements.charAt(number));
        }
        stringBuilder.append("Method");
        return stringBuilder.toString();
    }

    public static String randomName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('$');
        for (int i = 0; i < methodNameLength; i++) {
            stringBuilder.append(elements.charAt(random.nextInt(52)));
        }
        return stringBuilder.toString();
    }

    public static int randomInt() {
        return randomInt(-100, 100);
    }

    public static int randomInt(int start, int end) {
        return start + random.nextInt(end - start);
    }

    public static String randomType() {
        return typeList[random.nextInt(typeList.length)];
    }

    public static Object randomValue(String type) {
        if ("int".equals(type)) {
            return random.nextInt();
        } else if ("short".equals(type)) {
            return randomInt(-32768, 32767);
        } else if ("long".equals(type)) {
            return random.nextLong() + "L";
        } else if ("byte".equals(type)) {
            return randomInt(-128, 127);
        } else if ("float".equals(type)) {
            return random.nextFloat() + "f";
        } else if ("double".equals(type)) {
            return random.nextDouble();
        } else if ("boolean".equals(type)) {
            return true;
        } else if ("String".equals(type)) {
            return "\"" + randomName() + "\"";
        }
        return null;
    }

}
