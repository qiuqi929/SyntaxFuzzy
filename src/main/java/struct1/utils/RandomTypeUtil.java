package utils;

import initial.Initialize;
import pool.TypePool;

import java.util.Random;

public class RandomTypeUtil {

    private static TypePool typePool = Initialize.typePool;

    private static Random random = Initialize.random;

    /**
     * Random a return type (including void)
     * @return
     */
    public static String randomReturnType() {
        return typePool.randomElement();
    }

    /**
     * Random a variable type (except void)
     * @return
     */
    public static String randomVariableType() {
        return typePool.randomElementExceptVoid();
    }

}
