package utils;

import pool.VariablePool;
import struct.Node;
import struct.Variable;

public class VariableUtil {


    private static Variable makeVariable (String type, String name) {
        return new Variable(type, name);
    }

    private static void addVariableToPool (VariablePool variablePool, Variable variable) {
        variablePool.addElement(variable);
    }

    public static Variable RandomVariable () {
        // random a name and the type.
        String name = RandomNameUtil.randomVariableName();
        String type = RandomTypeUtil.randomVariableType();
        // return as a variable.
        Variable newVariable = makeVariable(type, name);
        return newVariable;
    }

    public static void handleVarible (Node parent, Variable variable, VariablePool variablePool) {
        // package into Node and connect to parent.
        parent.addChild(new Node(variable, parent));
        // add variable into variablePool
        addVariableToPool(variablePool, variable);
    }

}
