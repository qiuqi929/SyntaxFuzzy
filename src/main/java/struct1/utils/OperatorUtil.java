package utils;

import initial.Initialize;
import pool.OperatorPool;
import pool.VariablePool;
import struct.Node;
import struct.Operator;
import struct.Rule;
import struct.Variable;
import tree.MakeTree;

import java.util.List;
import java.util.Random;

public class OperatorUtil {

    private static OperatorPool operatorPool = Initialize.OperatorPool;

    private static Random random = Initialize.random;

    // random operator except block
    public static Operator randomOperator () {
        return operatorPool.randomElement();
    }

    public static Operator getBlockOperator () {
        return operatorPool.getSpecialBlock();
    }

    private static final double variableProbability = 0.3;

    private static final double operatorProbability = 0.2;

    public static void handleOperator(Node nullParent, Operator operator, VariablePool variablePool) {
        // add operator as a child
        nullParent.addChild(new Node(operator, nullParent));
        // get operator constraint parameters' type (typelist)
        Rule rule = DictionariesUtil.getRuleByOperator(operator);
        List<String> typelist = DictionariesUtil.getTypesByRule(rule);

        for (int i = 0; i < typelist.size(); i++) {
            // handle each type: choose a variable/ operator/ constant.
            String type = typelist.get(i);
            if (type.equals("block")){
                Node nullNode = new Node(nullParent);
                MakeTree.makeBlock(nullNode, variablePool);
            } else if (type.equals("while")) {
                nullParent.addChild(new Node("String", "while", nullParent));
            } else if (type.equals("else")) {
                nullParent.addChild(new Node("String", "else", nullParent));
            } else if (type.equals("variable")) {
                Variable variable = VariableUtil.RandomVariable();
                VariableUtil.handleVarible(nullParent, variable, variablePool);
            }else{
                double probability = random.nextDouble();
                if (probability < variableProbability) {
                    Variable variable = DictionariesUtil.findVariableByType(variablePool, type);
                    nullParent.addChild(new Node(variable, nullParent));
                } else if ( variableProbability <= probability && probability < variableProbability+operatorProbability){
                    Operator operatorByType = DictionariesUtil.findOperatorByType(type);
                    Node nullNode = new Node(nullParent);
                    handleOperator(nullNode, operatorByType, variablePool);
                } else {
                    String constant = ConstantUtil.randomConstantByType(type);
                    nullParent.addChild(new Node(type, constant, nullParent));
                }
            }
        }
    }

}
