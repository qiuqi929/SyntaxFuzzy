package utils;

import initial.Initialize;
import pool.VariablePool;
import struct.Node;
import struct.Operator;
import struct.Variable;

import java.util.Random;

public class VariableUtil {

    private static final Random random = Initialize.random;

    private static Variable makeVariable(String type, String name) {
        return new Variable(type, name);
    }

    private static void addVariableToPool(VariablePool variablePool, Variable variable) {
        variablePool.addElement(variable);
    }

    /**
     * Random a variable. Only declare it!
     *
     * @return
     */
    public static Variable randomVariable() {
        // random a name and the type.
        String name = RandomNameUtil.randomVariableName();
        String type = RandomTypeUtil.randomVariableType();
        // return as a variable.
        return makeVariable(type, name);
    }

    /**
     * Handle a variable: Connect to parent node and put it into the variablePool!
     */
    public static Node handleVariable(Node parent, Variable variable, VariablePool variablePool) {
        // package into Node and connect to parent.
        Node newNode = new Node(variable, parent);
        parent.addChild(newNode);
        // add variable into variablePool
        addVariableToPool(variablePool, variable);
        return newNode;
    }

    /**
     * When handle a variable: Maybe we can modify Node -> value be a constant or a operator string (probabilistic)
     * 1. Random a constant by type. Put it into node.value
     * 2. Random a operator by type. Handle it. And get the operator string!!!
     */
    public static void assignVariable(Node variableNode) {
        double probability = random.nextDouble();
        if (probability < 1) {
            String constant = ConstantUtil.randomConstantByType(variableNode.getVariable().getType());
            variableNode.setValue(constant);
        } else {
            Operator operator = DictionariesUtil.findOperatorByType(variableNode.getVariable().getType());
            // TODO: print the operator string -> A sub-tree of an operator.
        }
    }
}
