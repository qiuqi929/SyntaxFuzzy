package tree;

import initial.Initialize;
import pool.VariablePool;
import struct.Node;
import struct.Operator;
import struct.Variable;

import java.io.IOException;
import java.util.List;

public class TreeParser {

    static {
        Initialize.initialTypePool();
        try {
            Initialize.initialOperatorPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(generateMethod(0));
    }

    public static String generateMethod(int indent) {
        // make method head
        Node root = makeMethodTree();
        String[] modifiers = new String[]{"public", "static"};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < indent; i++) res.append('\t');
        for (String str : modifiers) res.append(str).append(' ');
        List<Node> children = root.getChildNode();
        Node methodNode = children.get(0);
        res.append(methodNode.getType()).append(' ').append(methodNode.getValue()).append(" ( ");
        int size = children.size();
        for (int i = 1; i < size - 1; i++) {
            Variable v = children.get(i).getVariable();
            res.append(v.getType()).append(' ').append(v.getName());
            if (i != size - 2) res.append(", ");
            else res.append(" )");
        }

        // make method body
        Node blockNode = children.get(size - 1);
        res.append(generateBlock(blockNode, indent + 1));
        return res.toString();
    }

    public static String generateBlock(Node root, int indent) {
        StringBuilder res = new StringBuilder(" {\n");
        List<Node> children = root.getChildNode();
        for (int i = 1, size = children.size(); i < size; i++) {
            for (int j = 0; j < indent; j++) res.append('\t');
            Node tmpNode = children.get(i);
            if (tmpNode.getVariable() != null) {
                Variable v = tmpNode.getVariable();
                res.append(v.getType()).append(' ').append(v.getName()).append(" = ").append(tmpNode.getValue()).append(";");
            } else {
                System.out.println("hahaha");
            }
            res.append('\n');
        }
//        for (int i = 0; i < indent - 1; i++) res.append('\t');
        res.append("}\n");
        return res.toString();
    }

    private static String generateStatement(Node root) {
        List<Node> children = root.getChildNode();
        String format = children.get(0).getOperator().getFormat();
        Object[] args = new Object[children.size() - 1];
        for (int i = 0; i < args.length; i++) {
            Node tmpNode = children.get(i + 1);
            if (tmpNode.getValue() != null) {
                args[i] = tmpNode.getValue();
            } else if (tmpNode.getChildNode() != null) {
                args[i] = generateStatement(tmpNode);
            }
        }
        return String.format(format, args);
    }

    private static Node makeMethodTree() {
        Node root = new Node(null);
        VariablePool variablePool = new VariablePool();
        MakeTree.declareMethod(root, variablePool);
        Node nullNode = new Node(root);
        root.addChild(nullNode);
        MakeTree.makeBlock(nullNode, variablePool);
        return root;
    }

}
