package tree;

import initial.Initialize;
import pool.OperatorPool;
import pool.VariablePool;
import struct.Node;
import struct.Operator;
import struct.Rule;
import struct.Variable;
import utils.OperatorUtil;
import utils.RandomNameUtil;
import utils.RandomTypeUtil;
import utils.VariableUtil;

import java.util.Random;

public class MakeTree {

    private static Random random = Initialize.random;

    private static OperatorPool operatorPool = Initialize.OperatorPool;

    private static final int parameterThresholdSize = 6;

    private static final int variableThresholdSize = 11;

    private static final int operatorThresholdSize = 8;

    public Node generateMethod() {
        Node headNode = new Node(null);
        // Maintain a variablePool
        VariablePool variablePool = new VariablePool();
        // declare the method name/ return type / parameters (parameter is also usable variable)
        declareMethod(headNode, variablePool);
        // create a nullNode used to connect block
        Node nullNode = new Node(headNode);
        // make the block
        makeBlock(nullNode, variablePool);

        // TODO: add a return variable.
        return headNode;
    }

    public void declareMethod (Node parent, VariablePool variablePool) {
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
            Variable variable = VariableUtil.RandomVariable();
            rule.addElement(variable.getType());
            VariableUtil.handleVarible(parent, variable, variablePool);
        }
        // package the method as an operator
        Operator operator = new Operator(returnType, rule, methodName);
        // add it to the operatorPool
        operatorPool.addElement(operator);
    }

    public void makeBlock (Node nullParent, VariablePool upVariablePool) {
        // copy the variable list to a new variablePool
        VariablePool variablePool = new VariablePool(upVariablePool.getPoolList());
        // handle a block operator
        Operator blockOperator = OperatorUtil.getBlockOperator();
        OperatorUtil.handleOperator(nullParent, blockOperator);

        // Random the variable. Package these variable into Node and connect to parent.
        // Add these variable into variablePool.
        int variableThreshold = random.nextInt(variableThresholdSize);
        for (int i = 0; i < variableThreshold; i++) {
            Variable variable = VariableUtil.RandomVariable();
            VariableUtil.handleVarible(nullParent, variable, variablePool);
        }
        // Random the operators. Package these operator into Node and connect to parent
        int operatorThreshold = random.nextInt(operatorThresholdSize);
        for (int i = 0; i < operatorThreshold; i++) {
            Operator operator = OperatorUtil.randomOperator();
            Node nullNode = new Node(nullParent);
            OperatorUtil.handleOperator(nullParent, operator);
        }
    }


}
