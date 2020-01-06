package tree;

import pool.VariablePool;
import struct.Node;
import struct.Operator;
import struct.Rule;
import struct.Variable;
import utils.DictionariesUtil;
import utils.RandomNameUtil;

import java.util.List;

public class PythonTreeParser {

    public static String generateMethod() {
        Node root = new Node(null);
        VariablePool pool = makeMethodTree(root);
        return generateMethodHead(root) + generateMethodBody(root, pool);
    }

    private static String generateMethodHead(Node root) {
        StringBuilder res = new StringBuilder();
        List<Node> children = root.getChildNode();
        Node methodNode = children.get(0);
        res.append("def ").append(methodNode.getValue()).append(" (");
        if (children.size() == 2) {
            res.append("):\n");
        } else {
            for (int i = 1, size = children.size(); i < size - 1; i++) {
                Variable v = children.get(i).getVariable();
                // 在python中变量可以指定类型, 但是由于不和java完全兼容, 此处省略
                res.append(v.getName());
                if (i != size - 2) {
                    res.append(", ");
                } else {
                    res.append("):\n");
                }
            }
        }
        return res.toString();
    }

    private static String generateMethodBody(Node root, VariablePool pool) {
        List<Node> children = root.getChildNode();
        Node blockNode = children.get(children.size() - 1);
        return generateBlock(blockNode, pool, 1, false, root.getType());
    }

    private static String generateBlock(Node root, VariablePool pool, int indent, boolean addBreak, String type) {
        StringBuilder res = new StringBuilder();
        List<Node> children = root.getChildNode();
        for (int i = 1, size = children.size(); i < size; i++) {
            Node tmpNode = children.get(i);
            if (tmpNode.getValue() == null && (tmpNode.getChildNode() == null || tmpNode.getChildNode().size() == 0) && tmpNode.getVariable() == null && tmpNode.getOperator() == null)
                continue;
            for (int j = 0; j < indent; j++) res.append('\t');
            if (tmpNode.getVariable() != null) {
                if (tmpNode.getValue().equals("true")) {
                    tmpNode.setValue("True");
                } else if (tmpNode.getValue().equals("false")) {
                    tmpNode.setValue("False");
                }
                Variable v = tmpNode.getVariable();
                res.append(v.getName()).append(" = ").append(tmpNode.getValue());
            } else if (tmpNode.getChildNode().size() != 0) {
                List<Node> twoSubNode = tmpNode.getChildNode();
                Operator operator = twoSubNode.get(0).getOperator();
                String format = operator.getFormat();
                String returnType = operator.getReturnType();
                if (returnType.equals("void") && (format.equals("if") || format.equals("while"))) {
                    res.append(generateFlowControl(tmpNode, pool, indent));
                } else {
                    res.append(generateSingleStatement(tmpNode, pool, returnType));
                }
            } else {
                res.append("# something strange happens, it should be not reached");
            }
            res.append('\n');
        }
        return res.toString();
    }

    private static String generateFlowControl(Node root, VariablePool pool, int indent) {
        List<Node> children = root.getChildNode();
        StringBuilder res = new StringBuilder();
        Operator operator = children.get(0).getOperator();
        String format = operator.getFormat();
        Rule rule = operator.getRule();
        List<String> typeList = rule.getTypelist();

        res.append(format).append(" ");
        boolean addBreak = format.equals("while");
        if (addBreak && children.get(1) != null && children.get(1).getValue() != null) {
            children.get(1).setValue("True");
        }
        for (int i = 0, size = typeList.size(); i < size; i++) {
            String type = typeList.get(i);
            if ("block".equals(type)) {
                VariablePool p = new VariablePool(pool);
                res.append(generateBlock(children.get(i + 1), p, indent + 1, addBreak, null));
                for (int j = 0; j < indent + 1; j++) res.append('\t');
                res.append("pass\n");
            } else if ("else".equals(type)) {
                for (int j = 0; j < indent; j++) res.append('\t');
                res.append("else: \n");
            } else {
                res.append(generateStatement(children.get(i + 1))).append(": \n");
            }
        }
        return res.toString();
    }

    private static String generateSingleStatement(Node root, VariablePool pool, String returnType) {
        StringBuilder res = new StringBuilder();
        Variable v = DictionariesUtil.findVariableByType(pool, returnType);
        if (v == null) {
            v = new Variable(returnType, RandomNameUtil.randomVariableName());
            pool.addElement(v);
        }
        res.append(v.getName()).append(" = ").append(generateStatement(root));
        return res.toString();
    }

    private static String generateStatement(Node root) {
        String ans = "";
        if (root.getValue() != null) {
            ans = root.getValue();
        } else if (root.getVariable() != null) {
            ans = root.getVariable().getName();
        } else {
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
            ans = String.format(format, args);
        }
        ans = ans.replaceAll("true", "True");
        ans = ans.replaceAll("false", "False");
        return ans;
    }

    private static VariablePool makeMethodTree(Node root) {
        VariablePool variablePool = new VariablePool();
        MakeTree.declareMethod(root, variablePool);
        Node nullNode = new Node(root);
        root.addChild(nullNode);
        return MakeTree.makeBlock(nullNode, variablePool);
    }

}
