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

    private static OperatorPool operatorPool = Initialize.operatorPool;

    private static Random random = Initialize.random;

    /**
     * Random an operator except special operator (block)
     * It may while/ for/ if ...
     *
     * @return
     */
    // random operator except block
    public static Operator randomOperator() {
        return operatorPool.randomElement();
    }

    /**
     * Get special operator -> block operator
     */
    public static Operator getBlockOperator() {
        return operatorPool.getSpecialBlock();
    }

    private static final double variableProbability = 0.3;

    private static final double operatorProbability = 0.4;

    /**
     * Handle the operator which may call in the block or call in the function parameter.
     * Consider: If the operator call in the function parameter. It must have return type!
     * And the operator is a simple function instead of while/for/if/etc
     * If the operator call in the block. Only CALL in the BLOCK, it may call operator while/for/if/etc
     * Those operators(while/for/if..) have special types(block/(do)while/else..) in the rule typelist -> Special handling
     * If there is a block. Call makeBlock method! Then operator while/for/if/etc will only call in the block.
     */

    public static int nestedLayer = 1;
    private static final int BLOCK_LIMIT = 100;
    public static void handleOperator(Node nullParent, Operator operator, VariablePool variablePool) {
        if(nestedLayer > BLOCK_LIMIT){
            return;
        }
        // add operator as a child
        nullParent.addChild(new Node(operator, nullParent));
        // get operator constraint parameters' type (typeList)
        Rule rule = DictionariesUtil.getRuleByOperator(operator);
        List<String> typeList = DictionariesUtil.getTypesByRule(rule);

        for (String type : typeList) {
            // handle each type in the rule.
            // special handling: the special type (example: while(Rule: boolean block), if(boolean block else block))
            if (type.equals("block")) {
                nestedLayer++;
                Node nullNode = new Node(nullParent);
                nullParent.addChild(nullNode);
                MakeTree.makeBlock(nullNode, variablePool);
            } else if (type.equals("while")) {
                nullParent.addChild(new Node("String", "while", nullParent));
            } else if (type.equals("else")) {
                nullParent.addChild(new Node("String", "else", nullParent));
            } else if (type.equals("variable")) {
                Variable variable = VariableUtil.randomVariable();
                Node variableNode = VariableUtil.handleVariable(nullParent, variable, variablePool);
                VariableUtil.assignVariable(variableNode);
            } else {
                // Don't have special type. The request type may be a variable/ operator/ constant.
                double probability = random.nextDouble();
                if (probability < variableProbability) {
                    // Maybe there is no variable that conform to the type
                    Variable variable = DictionariesUtil.findVariableByType(variablePool, type);
                    if (variable == null) {
                        handleConstant(type, nullParent);
                    } else {
                        nullParent.addChild(new Node(variable, nullParent));
                    }
                } else if (variableProbability <= probability && probability < variableProbability + operatorProbability) {
                    Operator operatorByType = DictionariesUtil.findOperatorByType(type);
                    Node nullNode = new Node(nullParent);
                    nullParent.addChild(nullNode);
                    handleOperator(nullNode, operatorByType, variablePool);
                } else {
                    handleConstant(type, nullParent);
                }
            }
        }
    }

    private static void handleConstant(String type, Node nullParent) {
        String constant = ConstantUtil.randomConstantByType(type);
        nullParent.addChild(new Node(type, constant, nullParent));
    }

}
