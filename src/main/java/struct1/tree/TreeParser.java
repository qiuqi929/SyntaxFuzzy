package tree;

import initial.Initialize;
import pool.VariablePool;
import struct.Node;
import struct.Operator;
import struct.Rule;
import struct.Variable;
import utils.ConstantUtil;
import utils.DictionariesUtil;
import utils.RandomNameUtil;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class TreeParser {

    static {
        Initialize.initialTypePool();
        try {
            Initialize.initialOperatorPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String[] modifiers = new String[]{"public", "static"};

    public static void main(String[] args) {
        System.out.println(generateMethod());
    }

    public static String generateMethod() {
        Node root = new Node(null);
        VariablePool pool = makeMethodTree(root);
        return generateMethodHead(root) + generateMethodBody(root, pool);
    }

    private static String generateMethodHead(Node root) {
        StringBuilder res = new StringBuilder();
        List<Node> children = root.getChildNode();
        Node methodNode = children.get(0);
        for (String str : modifiers) res.append(str).append(' ');
        res.append(methodNode.getType()).append(' ').append(methodNode.getValue()).append(" (");
        if (children.size() == 2) {
            res.append(")");
        } else {
            for (int i = 1, size = children.size(); i < size - 1; i++) {
                Variable v = children.get(i).getVariable();
                res.append(v.getType()).append(' ').append(v.getName());
                if (i != size - 2) {
                    res.append(", ");
                } else {
                    res.append(")");
                }
            }
        }
        return res.toString();
    }

    private static String generateMethodBody(Node root, VariablePool pool) {
        List<Node> children = root.getChildNode();
        Node blockNode = children.get(children.size() - 1);
        return generateBlock(blockNode, pool, 1, false, root.getChildNode().get(0).getType());
    }

    public static String generateBlock(Node root, VariablePool pool, int indent, boolean addBreak, String type) {
        StringBuilder res = new StringBuilder(" {\n");
        List<Node> children = root.getChildNode();
        for (int i = 1, size = children.size(); i < size; i++) {
            for (int j = 0; j < indent; j++) res.append('\t');
            Node tmpNode = children.get(i);
            if (tmpNode.getVariable() != null) {
                Variable v = tmpNode.getVariable();
                res.append(v.getType()).append(' ').append(v.getName()).append(" = ").append(tmpNode.getValue()).append(";");
            } else {
                List<Node> twoSubNode = tmpNode.getChildNode();
                Operator operator = twoSubNode.get(0).getOperator();
                String returnType = operator.getReturnType();
                if (returnType.equals("void")) {
                    res.append(generateFlowControl(tmpNode, pool, indent));
                } else {
                    res.append(generateSingleStatement(tmpNode, pool, returnType));
                }
            }
            res.append('\n');
        }
        if (addBreak) {
            for (int i = 0; i < indent; i++) res.append('\t');
            res.append("break;").append('\n');
        }
        if (type != null && !type.equals("void")) {
            for (int i = 0; i < indent; i++) res.append('\t');
            switch (RandomNameUtil.nextInt(2)) {
                case 0:
                    res.append("return ").append(ConstantUtil.randomConstantByType(type)).append(";");
                    break;
                case 1:
                    res.append("return ").append(DictionariesUtil.findVariableByType(pool, type)).append(";");
                    break;
            }
            res.append('\n');
        }
        for (int i = 0; i < indent - 1; i++) res.append('\t');
        res.append("}\n");
        return res.toString();
    }

    private static String generateFlowControl(Node root, VariablePool pool, int indent) {
        List<Node> children = root.getChildNode();
        StringBuilder res = new StringBuilder();
        Operator operator = children.get(0).getOperator();
        String format = operator.getFormat();
        Rule rule = operator.getRule();
        List<String> typeList = rule.getTypelist();

        res.append(format);
        boolean addBreak = format.equals("while") || format.equals("do") || format.equals("for");
        for (int i = 0, size = typeList.size(); i < size; i++) {
            String type = typeList.get(i);
            if (type.equals("block")) {
                VariablePool newPool = new VariablePool(pool);
                res.append(generateBlock(children.get(i + 1), newPool, indent + 1, addBreak, null));
                addBreak = false;
            } else if (ConstantUtil.inKeywords(type)) {
                for (int j = 0; j < indent; j++) res.append('\t');
                res.append(type);
                if (type.equals("while")) addBreak = true;
            } else {
                res.append('(').append(generateStatement(children.get(i + 1))).append(')');
            }
        }
        if (format.equals("do")) res.append(';');
        return res.toString();
    }

    private static String generateSingleStatement(Node root, VariablePool pool, String returnType) {
        StringBuilder res = new StringBuilder();
        Variable v = DictionariesUtil.findVariableByType(pool, returnType);
        if (v == null) {
            v = new Variable(returnType, RandomNameUtil.randomVariableName());
            pool.addElement(v);
            res.append(returnType).append(' ');
        }
        res.append(v.getName()).append(" = ").append(generateStatement(root)).append(';');
        return res.toString();
    }

    private static String generateStatement(Node root) {
        if (root.getValue() != null)
            return root.getValue();
        if (root.getVariable() != null)
            return root.getVariable().getName();
        List<Node> children = root.getChildNode();
        String format = children.get(0).getOperator().getFormat();
        Object[] args = new Object[children.size() - 1];
        for (int i = 0; i < args.length; i++) {
            Node tmpNode = children.get(i + 1);
            if (tmpNode.getValue() != null) {
                args[i] = tmpNode.getValue();
            } else if (tmpNode.getChildNode() != null) {
                args[i] = generateStatement(tmpNode);
            } else if (tmpNode.getVariable() != null) {
                args[i] = tmpNode.getVariable().getName();
            }
        }
        return String.format(format, args);
    }

    /**
     * @param root the node need to be span
     * @return variable pool
     */
    private static VariablePool makeMethodTree(Node root) {
        VariablePool variablePool = new VariablePool();
        MakeTree.declareMethod(root, variablePool);
        Node nullNode = new Node(root);
        root.addChild(nullNode);
        return MakeTree.makeBlock(nullNode, variablePool);
    }

}
