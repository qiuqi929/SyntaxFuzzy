import initial.Initialize;
import org.junit.Before;
import org.junit.Test;
import pool.VariablePool;
import struct.Node;
import struct.Operator;
import struct.Rule;
import struct.Variable;
import tree.MakeTree;
import utils.*;

import java.io.*;
import java.util.*;

public class TestMain {

    private static final int RAND_NUM = 7;

    @Before
    public void before() throws IOException {
        Initialize.initialOperatorPool();
        Initialize.initialTypePool();
    }

    @Test
    public void testInitialTypePool() {
        List<String> typeList = Initialize.typePool.getPoolList();
        typeList.forEach(System.out::println);
    }

    @Test
    public void testInitialOperatorPool() {
        List<Operator> operatorList = Initialize.operatorPool.getPoolList();
        operatorList.forEach(System.out::println);
    }

    @Test
    public void testRandomMethodName() {
        for (int i = 0; i < RAND_NUM; i++) {
            System.out.println(RandomNameUtil.randomMethodName());
        }
    }

    @Test
    public void testRandomVariableName() {
        for (int i = 0; i < RAND_NUM; i++) {
            System.out.println(RandomNameUtil.randomVariableName());
        }
    }

    @Test
    public void testRandomReturnType() {
        for (int i = 0; i < RAND_NUM; i++) {
            System.out.println(RandomTypeUtil.randomReturnType());
        }
    }

    @Test
    public void testRandomVariableType() {
        for (int i = 0; i < RAND_NUM; i++) {
            System.out.println(RandomTypeUtil.randomVariableType());
        }
    }

    @Test
    public void testRandomConstantByType() {
        String[] types = new String[]{
                "byte", "char", "short", "int", "long",
                "float", "double", "boolean", "String"};
        for (String type : types) printConstant(type);
    }

    @Test
    public void testRandomVariable() {
        VariablePool variablePool = new VariablePool();
        Node nullNode = new Node(null);
        System.out.println("random-variable check");
        System.out.println("name\t\ttype");
        for (int i = 0; i < RAND_NUM; i++) {
            Variable variable = VariableUtil.randomVariable();
            System.out.printf("%s\t\t%s\n", variable.getName(), variable.getType());
            VariableUtil.handleVariable(nullNode, variable, variablePool);
        }
        System.out.println();

        List<Node> childNode = nullNode.getChildNode();
        System.out.println("sub-tree check");
        System.out.println("name\t\ttype");
        for (Node child : childNode) {
            Variable v = child.getVariable();
            if (v != null) {
                System.out.printf("%s\t\t%s\n", v.getName(), v.getType());
            }
        }
        System.out.println();

        List<Variable> variableList = variablePool.getPoolList();
        System.out.println("variable-pool check");
        System.out.println("name\t\ttype");
        variableList.forEach(v -> System.out.printf("%s\t\t%s\n", v.getName(), v.getType()));
    }

    @Test
    public void testRandomOperator() {
        System.out.println("returnType\t\tRule\t\t\t\tformat");
        for (int i = 0; i < RAND_NUM; i++) {
            Operator operator = OperatorUtil.randomOperator();
            System.out.printf("%-16s%-20s%s\n", operator.getReturnType(), operator.getRule().getTypelist(), operator.getFormat());
        }

        System.out.println("------- block operator ---------- \n");
        Operator blockOperator = OperatorUtil.getBlockOperator();
        System.out.println("returnType: " + blockOperator.getReturnType());
        if (blockOperator.getRule() == null) {
            System.out.println("Rule: null");
        }
        System.out.println("format: " + blockOperator.getFormat());
        System.out.println();
    }

    @Test
    public void testGetRuleBy() {
        // -------------------- Test getRuleByOperator/ getTypesByRule --------------------------
        for (int i = 0; i < RAND_NUM; i++) {
            Operator operator = OperatorUtil.randomOperator();
            Rule rule = DictionariesUtil.getRuleByOperator(operator);
            List<String> list = DictionariesUtil.getTypesByRule(rule);
            System.out.println("return type: " + operator.getReturnType());
            System.out.println("operator format: " + operator.getFormat());
            System.out.println("typeList: " + Arrays.toString(list.toArray()));
        }
    }

    @Test
    public void testFindVariableByType() {
        // -------------------- Test findVariableByType --------------------------
        System.out.println("-------------------- Test findVariableByType --------------------------");
        VariablePool variablePool = new VariablePool();
        printVariableByType("byte", variablePool);
        printVariableByType("char", variablePool);
        printVariableByType("short", variablePool);
        printVariableByType("int", variablePool);
        printVariableByType("long", variablePool);
        printVariableByType("float", variablePool);
        printVariableByType("double", variablePool);
        printVariableByType("boolean", variablePool);
        printVariableByType("String", variablePool);
    }

    @Test
    public void testFindOperatorByType() {
        printOperatorByType("byte");
        printOperatorByType("char");
        printOperatorByType("short");
        printOperatorByType("int");
        printOperatorByType("long");
        printOperatorByType("float");
        printOperatorByType("double");
        printOperatorByType("boolean");
        printOperatorByType("String");
        System.out.println();
    }

    @Test
    public void testMakeTree() {
        Node headNode = new Node(null);
        VariablePool variablePool = new VariablePool();
        MakeTree.declareMethod(headNode, variablePool);
        List<Node> childNodes = headNode.getChildNode();
        for (Node child : childNodes) {
            if (child.getVariable() != null) {
                Variable variable = child.getVariable();
                System.out.println("name: " + variable.getName());
                System.out.println("type: " + variable.getType());
            } else if (child.getType() != null && child.getValue() != null) {
                System.out.println("Method name: " + child.getValue());
                System.out.println("return Type: " + child.getType());
            }
        }

        List<Operator> operatorList = Initialize.operatorPool.getPoolList();
        System.out.println("------- Operator pool (last one) ----- ");
        Operator lastOperator = operatorList.get(operatorList.size() - 1);
        System.out.println("returnType: " + lastOperator.getReturnType());
        System.out.println("Rule: " + Arrays.toString(lastOperator.getRule().getTypelist().toArray()));
        System.out.println("format: " + lastOperator.getFormat());

        System.out.println("------- variable pool ----------");
        List<Variable> variableList = variablePool.getPoolList();
        for (int i = 0; i < variableList.size(); i++) {
            System.out.printf("------- variable %s ------\n", i);
            Variable variable = variableList.get(i);
            System.out.println("name: " + variable.getName());
            System.out.println("type: " + variable.getType());
        }
    }

    @Test
    public void testMakeBlock() {
        Node nullNode = new Node(null);
        MakeTree.makeBlock(nullNode, new VariablePool());
    }

    @Test
    public void testReturnValue() {
        System.out.println("------------------- Test getReturnValue ---------------------");
        Node head = MakeTree.generateMethod();
        System.out.printf("Method: [name: %s, returnType: %s]\n", head.getChildNode().get(0).getValue(), head.getChildNode().get(0).getType());
        System.out.printf("Return value: %s\n", head.getChildNode().get(head.getChildNode().size() - 1).getValue());
    }

    @Test
    public void testAssignVariable() {
        VariablePool variablePool = new VariablePool();
        Node parent3 = new Node(null);
        for (int i = 0; i < 5; i++) {
            Variable variable = VariableUtil.randomVariable();
            System.out.printf("variable %d: [type: %s ; name: %s]\n", i, variable.getType(), variable.getName());
            Node variableNode = VariableUtil.handleVariable(parent3, variable, variablePool);
            VariableUtil.assignVariable(variableNode);
        }
        List<Node> childNodes3 = parent3.getChildNode();
        for (int i = 0; i < childNodes3.size(); i++) {
            Node child = childNodes3.get(i);
            System.out.printf("variable %d: [type: %s ; name: %s]\n", i, child.getVariable().getType(), child.getVariable().getName());
            System.out.println("variable value: " + child.getValue());
        }
    }

    public static void printConstant(String type) {
        System.out.printf("%s: ", type);
        for (int i = 0; i < 5; i++) {
            System.out.print(ConstantUtil.randomConstantByType(type) + " ");
        }
    }

    public static void printVariableByType(String type, VariablePool variablePool) {
        for (int i = 0; i < 5; i++) {
            System.out.printf("---------- variable %d by type: %s -----------\n", i, type);
            Variable variable = DictionariesUtil.findVariableByType(variablePool, type);
            if (variable == null) {
                System.out.println("no such variable!");
            } else {
                System.out.println("type: " + variable.getType());
                System.out.println("name: " + variable.getName());
            }
        }
    }

    public static void printOperatorByType(String type) {
        for (int i = 0; i < 5; i++) {
            System.out.printf("--------- operator %d by type %s ------------ \n", i, type);
            Operator operator = DictionariesUtil.findOperatorByType(type);
            System.out.println("returnType: " + operator.getReturnType());
            System.out.println("Rule: " + Arrays.toString(operator.getRule().getTypelist().toArray()));
            System.out.println("format: " + operator.getFormat());
        }
    }

}
