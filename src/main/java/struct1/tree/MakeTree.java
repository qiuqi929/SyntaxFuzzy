package tree;

import initial.Initialize;
import pool.VariablePool;
import struct.Node;
import struct.Variable;
import utils.RandomNameUtil;
import utils.RandomTypeUtil;
import utils.VariableUtil;

import java.util.Random;

public class MakeTree {

    private static Random random = Initialize.random;

    private static final int parameterThresholdSize = 6; // 0 ~ 5

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
        // TODO
        return headNode;
    }

    public void declareMethod (Node parent, VariablePool variablePool) {
        // Random the Method Name and return Type. Package it as ONE Node.
        String methodName = RandomNameUtil.randomMethodName();
        String returnType = RandomTypeUtil.randomReturnType();
        parent.addChild(new Node(returnType, methodName, parent));
        // Random the parameters. Package these parameter(also variable) into Node and connect to parent.
        // Add these parameters into variablePool.
        int parameteThreshold = random.nextInt(parameterThresholdSize);
        for (int i = 0; i < parameteThreshold; i++) {
            Variable variable = VariableUtil.RandomVariable();
            VariableUtil.handleVarible(parent, variable, variablePool);
        }
        // TODO
    }

    public void makeBlock (Node parent, VariablePool upVariablePool) {
        // copy the variable list to a new variablePool
        VariablePool variablePool = new VariablePool(upVariablePool.getPoolList());

        // TODO
    }


}
