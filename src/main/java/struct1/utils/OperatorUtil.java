package utils;

import initial.Initialize;
import pool.OperatorPool;
import struct.Node;
import struct.Operator;

public class OperatorUtil {

    private static OperatorPool operatorPool = Initialize.OperatorPool;

    // random operator except block
    public static Operator randomOperator () {
        return operatorPool.randomElement();
    }

    public static Operator getBlockOperator () {
        return operatorPool.getSpecialBlock();
    }

    public static void handleOperator(Node parent, Operator operator) {
        // add operator as a child
        parent.addChild(new Node(operator, parent));

    }

}
