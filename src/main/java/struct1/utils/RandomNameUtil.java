package utils;

import initial.Initialize;

import java.util.Random;

public class RandomNameUtil {

    private static Random random = Initialize.random;

    private static String elements = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final int methodNameLength = 7;

    private static final int variableNameLength = 7;

    /**
     * Random a method name.
     * @return
     */
    public static String randomMethodName(){
        String methodName  = "";
        for (int i = 0; i < methodNameLength; i++) {
            int number = random.nextInt(52);
            methodName += String.valueOf(elements.charAt(number));
        }
        methodName = methodName + "Method";
        return methodName;
    }

    /**
     * Random a variable name.
     * @return
     */

    public static String randomVariableName() {
        String variableName = "";
        for (int i = 0; i < variableNameLength; i++) {
            int number = random.nextInt(52);
            variableName += String.valueOf(elements.charAt(number));
        }
        return variableName;
    }

}
