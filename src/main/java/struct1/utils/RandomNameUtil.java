package utils;

import initial.Initialize;

import java.util.Random;

public class RandomNameUtil {

    private static Random random = Initialize.random;

    private static String elements = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final int methodNameLength = 7;

    private static final int variableNameLength = 7;

    /**
     * @return Random a method name.
     */
    public static String randomMethodName() {
        StringBuilder methodName = new StringBuilder();
        for (int i = 0; i < methodNameLength; i++) {
            int number = random.nextInt(elements.length());
            methodName.append(elements.charAt(number));
        }
        methodName.append("Method");
        return methodName.toString();
    }

    /**
     * @return Random a variable name.
     */

    public static String randomVariableName() {
        StringBuilder variableName = new StringBuilder();
        for (int i = 0; i < variableNameLength; i++) {
            int number = random.nextInt(elements.length());
            variableName.append(elements.charAt(number));
        }
        return variableName.toString();
    }

}
