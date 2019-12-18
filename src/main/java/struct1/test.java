import initial.Initialize;
import pool.VariablePool;
import struct.Node;
import struct.Operator;
import struct.Rule;
import struct.Variable;
import tree.MakeTree;
import utils.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class test {

    public static void main(String[] args) throws IOException {
//        // Test Initialize
//        // --------------------- Test initialTypePool -----------------------------
//        System.out.println("--------------------- Test initialTypePool -----------------------------");
        List<String> typelist = Initialize.typePool.getPoolList();
        Initialize.initialTypePool();
//        for (int i = 0; i < typelist.size(); i++) {
//            System.out.println(typelist.get(i) + " ");
//        }
//        System.out.println();
//
//        // --------------------- Test initialOperatorPool ----------------------------
//        System.out.println("--------------------- Test initialOperatorPool ----------------------------");
        List<Operator> operatorList = Initialize.operatorPool.getPoolList();
        Initialize.initialOperatorPool();
//        for (int i = 0; i < operatorList.size(); i++) {
//            System.out.printf("---------- operator %d ---------- \n", i);
//            System.out.println("returnType: " + operatorList.get(i).getReturnType());
//            if(operatorList.get(i).getRule() != null) {
//                System.out.println("Rule: " + Arrays.toString(operatorList.get(i).getRule().getTypelist().toArray()));
//            }else{
//                System.out.println("Rule: null");
//            }
//            System.out.println("format: " + operatorList.get(i).getFormat());
//        }
//        System.out.println();
//
//
//        // Test RandomNameUtil
//        // -------------------- Test randomMethodName ----------------------------
//        System.out.println("-------------------- Test randomMethodName ----------------------------");
//        for (int i = 0; i < 5; i++) {
//            System.out.println(RandomNameUtil.randomMethodName());
//        }
//        System.out.println();
//
//
//        // -------------------- Test randomVariableName ----------------------------
//        System.out.println("-------------------- Test randomVariableName ----------------------------");
//        for (int i = 0; i < 5; i++) {
//            System.out.println(RandomNameUtil.randomVariableName());
//        }
//        System.out.println();
//
//
//        //Test RandomTypeUtil
//        // -------------------- Test randomReturnType ----------------------------
//        System.out.println("-------------------- Test randomReturnType ----------------------------");
//        for (int i = 0; i < 10; i++) {
//            System.out.print(RandomTypeUtil.randomReturnType() + " ");
//        }
//        System.out.println();
//
//        // -------------------- Test randomVariableType ----------------------------
//        System.out.println("-------------------- Test randomVariableType ----------------------------");
//        for (int i = 0; i < 10; i++) {
//            System.out.print(RandomTypeUtil.randomVariableType() + " ");
//        }
//        System.out.println();
//
//        //Test ConstantUtil
//        // -------------------- Test randomConstantByType --------------------------
//        System.out.println("-------------------- Test randomConstantByType --------------------------");
//        printConstant("byte");
//        printConstant("char");
//        printConstant("short");
//        printConstant("int");
//        printConstant("long");
//        printConstant("float");
//        printConstant("double");
//        printConstant("boolean");
//        printConstant("String");
//
//
//        // Test VariableUtil
//        // -------------------- Test randomVariable / handleVariable--------------------------
//        System.out.println("-------------------- Test randomVariable / handleVariable--------------------------");
//        VariablePool variablePool = new VariablePool();
//        Node nullNode = new Node(null);
//        for (int i = 0; i < 10; i++) {
//            System.out.printf("----------- variable %d ----------\n", i);
//            Variable variable = VariableUtil.randomVariable();
//            System.out.println("name: " + variable.getName());
//            System.out.println("type: " + variable.getType());
//            VariableUtil.handleVariable(nullNode, variable, variablePool);
//        }
//        System.out.println();
//
//        // check sub-tree
//        System.out.println("nullNode address: " + nullNode.hashCode());
//        List<Node> childNode = nullNode.getChildNode();
//        for (int i = 0; i < childNode.size(); i++) {
//            System.out.printf("----------- child %d ----------\n", i);
//            Node child = childNode.get(i);
//            if(child.getVariable() != null) {
//                System.out.println("name: " + child.getVariable().getName());
//                System.out.println("type: " + child.getVariable().getType());
//                System.out.println("parent address: " + child.getParent().hashCode());
//            }
//            System.out.println();
//        }
//
//        // check variable pool
//        List<Variable> variableList = variablePool.getPoolList();
//        for (int i = 0; i < variableList.size(); i++) {
//            System.out.printf("----------- pool variable %d ----------\n", i);
//            Variable variable = variableList.get(i);
//            System.out.println("name: " + variable.getName());
//            System.out.println("type: " + variable.getType());
//        }
//
//        // Test OperatorUtil
//        // -------------------- Test randomOperator --------------------------
//        System.out.println("-------------------- Test randomOperator --------------------------");
//        for (int i = 0; i < 5; i++) {
//            System.out.printf("------- operator %d ---------- \n", i);
//            Operator operator = OperatorUtil.randomOperator();
//            System.out.println("returnType: " + operator.getReturnType());
//            System.out.println("Rule: " + Arrays.toString(operator.getRule().getTypelist().toArray()));
//            System.out.println("format: " + operator.getFormat());
//        }
//
//        // test block operator
//        System.out.println("------- block operator ---------- \n");
//        Operator blockOperator = OperatorUtil.getBlockOperator();
//        System.out.println("returnType: " + blockOperator.getReturnType());
//        if(blockOperator.getRule() == null){
//            System.out.println("Rule: null");
//        }
//        System.out.println("format: " + blockOperator.getFormat());
//        System.out.println();
//
//        // Test DictionariesUtil
//        // -------------------- Test getRuleByOperator/ getTypesByRule --------------------------
//        System.out.println("-------------------- Test getRuleByOperator/ getTypesByRule --------------------------");
//        for (int i = 0; i < 5; i++) {
//            Operator operator = OperatorUtil.randomOperator();
//            System.out.println("operator format: " + operator.getFormat());
//            Rule rule = DictionariesUtil.getRuleByOperator(operator);
//            List<String> typeList = DictionariesUtil.getTypesByRule(rule);
//            System.out.println("typeList: " + Arrays.toString(typeList.toArray()));
//        }
//
//        // -------------------- Test findVariableByType --------------------------
//        System.out.println("-------------------- Test findVariableByType --------------------------");
//        printVariableByType("byte", variablePool);
//        printVariableByType("char", variablePool);
//        printVariableByType("short", variablePool);
//        printVariableByType("int", variablePool);
//        printVariableByType("long", variablePool);
//        printVariableByType("float", variablePool);
//        printVariableByType("double", variablePool);
//        printVariableByType("boolean", variablePool);
//        printVariableByType("String", variablePool);
//
//
//        // -------------------- Test  findOperatorByType --------------------------
//        System.out.println("-------------------- Test  findOperatorByType --------------------------");
//        printOperatorByType("byte");
//        printOperatorByType("char");
//        printOperatorByType("short");
//        printOperatorByType("int");
//        printOperatorByType("long");
//        printOperatorByType("float");
//        printOperatorByType("double");
//        printOperatorByType("boolean");
//        printOperatorByType("String");
//        System.out.println();


        // Test MakeTree
        // -------------------- Test  declareMethod -------------------------------------
        System.out.println("-------------------- Test  declareMethod --------------------------");
        Node headNode = new Node(null);
        VariablePool variablePool2 = new VariablePool();
        MakeTree.declareMethod(headNode, variablePool2);
        List<Node> childNodes = headNode.getChildNode();
        System.out.println("headNode address: " + headNode.hashCode());
        for (int i = 0; i < childNodes.size(); i++) {
            Node child = childNodes.get(i);
            if(child.getVariable() != null) {
                System.out.printf("------- parameter %s ------\n", i);
                Variable variable = child.getVariable();
                System.out.println("name: " + variable.getName());
                System.out.println("type: " + variable.getType());
                System.out.println("parent address:" + child.getParent().hashCode());
            }else if(child.getType() != null && child.getValue() != null){
                System.out.println("Method name: " + child.getValue());
                System.out.println("return Type: " + child.getType());
            }
        }

        System.out.println("------ Operator pool (last one) ----- ");
        Operator lastOperator = operatorList.get(operatorList.size()-1);
        System.out.println("returnType: " + lastOperator.getReturnType());
        System.out.println("Rule: " + Arrays.toString(lastOperator.getRule().getTypelist().toArray()));
        System.out.println("format: " + lastOperator.getFormat());

        System.out.println("------- variable pool ----------");
        List<Variable> variableList2 = variablePool2.getPoolList();
        for (int i = 0; i < variableList2.size(); i++) {
            System.out.printf("------- variable %s ------\n", i);
            Variable variable = variableList2.get(i);
            System.out.println("name: " + variable.getName());
            System.out.println("type: " + variable.getType());
        }

        // ------------------- Test makeBlock and handleOperator (using debug!!) -----------------
        System.out.println("--------------- test make block and handleOperator -----------------");
        Node nullNode2 = new Node(headNode);
        headNode.addChild(nullNode2);
        MakeTree.makeBlock(nullNode2, variablePool2);
        System.out.println("----- Already out ! -------");
        System.out.println("----- Already out ! -------");
        System.out.println("----- Already out ! -------");

        // ------------------- Test getReturnValue ---------------------
        System.out.println("------------------- Test getReturnValue ---------------------");
        Node head = MakeTree.generateMethod();
        System.out.printf("Method: [name: %s, returnType: %s]\n", head.getChildNode().get(0).getValue(), head.getChildNode().get(0).getType());
        System.out.printf("Return value: %s\n", head.getChildNode().get(head.getChildNode().size()-1).getValue());


//        // VariableUtil add method
//        // ------------------- Test assignVariable ------------------------------
//        VariablePool variablePool3 = new VariablePool();
//        Node parent3 = new Node(null);
//        for (int i = 0; i < 5; i++) {
//            Variable variable = VariableUtil.randomVariable();
//            System.out.printf("variable %d: [type: %s ; name: %s]\n", i, variable.getType(), variable.getName());
//            Node variableNode = VariableUtil.handleVariable(parent3, variable, variablePool3);
//            VariableUtil.assignVariable(variableNode);
//        }
//        List<Node> childNodes3 = parent3.getChildNode();
//        for (int i = 0; i < childNodes3.size(); i++) {
//            Node child = childNodes3.get(i);
//            System.out.printf("variable %d: [type: %s ; name: %s]\n", i, child.getVariable().getType(), child.getVariable().getName());
//            System.out.println("variable value: " + child.getValue());
//        }

    }

    public static void printConstant(String type){
        System.out.printf("%s: ", type);
        for (int i = 0; i < 5; i++) {
            System.out.print(ConstantUtil.randomConstantByType(type) + " ");
        }
        System.out.println();
    }

    public static void printVariableByType(String type, VariablePool variablePool) {
        for (int i = 0; i < 5; i++) {
            System.out.printf("---------- variable %d by type: %s -----------\n", i, type);
            Variable variable = DictionariesUtil.findVariableByType(variablePool, type);
            if(variable == null){
                System.out.println("no such variable!");
            }else{
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
