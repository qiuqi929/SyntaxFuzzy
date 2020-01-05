package tree;

import initial.Initialize;
import pool.OperatorPool;
import pool.VariablePool;
import struct.Node;
import struct.Operator;
import struct.Rule;
import struct.Variable;
import utils.*;

import java.util.List;
import java.util.Random;

public class MakeTree {

    private static Random random = Initialize.random;

    private static OperatorPool operatorPool = Initialize.operatorPool;

    private static final int parameterThresholdSize = 5;

    private static final int variableThresholdSize = 3;

    private static final int operatorThresholdSize = 6;

    public static Node generateMethod() {
        Node headNode = new Node(null);
        // Maintain a variablePool
        VariablePool variablePool = new VariablePool();
        // declare the method name/ return type / parameters (parameter is also usable variable)
        Operator operator = declareMethod(headNode, variablePool);
        // create a nullNode used to connect block
        Node nullNode = new Node(headNode);
        headNode.addChild(nullNode);
        // make the block
        makeBlock(headNode, variablePool);

        getReturnValue(operator.getReturnType(), headNode);

        operatorPool.addElement(operator);
        return headNode;
    }

    public static Operator declareMethod(Node parent, VariablePool variablePool) {
        // Random the Method Name and return Type. Package it into ONE Node.
        String methodName = RandomNameUtil.randomMethodName();
        String returnType = RandomTypeUtil.randomReturnType();
        parent.addChild(new Node(returnType, methodName, parent));
        // Random the parameters. Package these parameter(also variable) into Node and connect to parent.
        // Add these parameters into variablePool.
        int parameterThreshold = random.nextInt(parameterThresholdSize);
        // make Rule for this method
        Rule rule = new Rule();
        for (int i = 0; i < parameterThreshold; i++) {
            Variable variable = VariableUtil.randomVariable();
            rule.addElement(variable.getType());
            VariableUtil.handleVariable(parent, variable, variablePool);
        }
        // package the method as an operator
        Operator operator = new Operator(returnType, rule, buildMethodFormat(rule, methodName));
        operatorPool.addElement(operator);
        return operator;
    }

    private static String buildMethodFormat(Rule rule, String methodName) {
        StringBuilder res = new StringBuilder();
        res.append(methodName).append("(");
        List<String> typeList = rule.getTypelist();
        for (int i = 0, size = typeList.size(); i < size; i++) {
            if (i != size - 1) {
                res.append("%s, ");
            } else {
                res.append("%s)");
            }
        }
        if (typeList.size() == 0) res.append(')');
        return res.toString();
    }

    /**
     * Consider: assign -> we don't need to care about the variable value. And the change of the value.
     * But we need to print it! And sometimes the value maybe an operator such as "1+3".
     * How can we put it in the tree? If we put in variable, the value may change.
     * Maybe we can put it in the Node -> value.
     * The constant has type & value. But variable only has value.
     */
    public static VariablePool makeBlock(Node nullParent, VariablePool upVariablePool) {
        // copy the variable list to a new variablePool
        VariablePool variablePool = new VariablePool(upVariablePool.getPoolList());
        // handle a block operator
        Operator blockOperator = OperatorUtil.getBlockOperator();
        nullParent.addChild(new Node(blockOperator, nullParent));
        // Random the variable. Package these variable into Node and connect to parent.
        // Add these variable into variablePool.
        int variableThreshold = random.nextInt(variableThresholdSize);
        for (int i = 0; i < variableThreshold; i++) {
            Variable variable = VariableUtil.randomVariable();
            Node variableNode = VariableUtil.handleVariable(nullParent, variable, variablePool);
            VariableUtil.assignVariable(variableNode);
        }
        // Random the operators. Package these operator into Node and connect to parent
        int operatorThreshold = random.nextInt(operatorThresholdSize);
        for (int i = 0; i < operatorThreshold; i++) {
            Operator operator = OperatorUtil.randomOperator();
            Node nullNode = new Node(nullParent);
            nullParent.addChild(nullNode);
            OperatorUtil.handleOperator(nullNode, operator, variablePool);
        }
        return variablePool;
    }

    /**
     * Last child node for headNode
     */
    private static void getReturnValue(String returnType, Node parent) {
        parent.addChild(new Node(returnType, ConstantUtil.randomConstantByType(returnType), parent));
    }
}
