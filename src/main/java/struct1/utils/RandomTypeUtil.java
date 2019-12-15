package utils;

import initial.Initialize;
import pool.Pool;
import pool.TypePool;

import java.util.Random;

public class RandomTypeUtil {

    private static TypePool typePool = Initialize.typePool;

    private static Random random = Initialize.random;

    public static String randomReturnType() {
        return typePool.randomElement();
    }

    public static String randomVariableType() {
        return typePool.randomElementExceptVoid();
    }

}
