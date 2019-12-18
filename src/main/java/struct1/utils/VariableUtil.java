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

    /**
     * Random a variable. Only declare it!
     * @return
     */
    public static Variable randomVariable() {
        // random a name and the type.
        String name = RandomNameUtil.randomVariableName();
        String type = RandomTypeUtil.randomVariableType();
        // return as a variable.
        Variable newVariable = makeVariable(type, name);
        return newVariable;
    }

    /**
     * Handle a variable: Connect to parent node and put it into the variablePool!
     * @param parent
     * @param variable
     * @param variablePool
     */
    public static void handleVariable(Node parent, Variable variable, VariablePool variablePool) {
        // package into Node and connect to parent.
        parent.addChild(new Node(variable, parent));
        // add variable into variablePool
        addVariableToPool(variablePool, variable);
    }

    /**
     * TODO: When handle a variable: Maybe we can modify Node -> value be a constant or a operator string (probabilistic)
     * 1. Random a constant by type. Put it into node.value
     * 2. Random a operator by type. Handle it. And get the operator string!!!
     */
}
