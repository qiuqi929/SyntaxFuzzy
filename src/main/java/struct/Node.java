package struct;

import lombok.Data;
import util.RandomUtil;

import java.util.ArrayList;
import java.util.Random;

@Data
public class Node {

    /**
     * returnType表示该节点的值的返回类型
     * 因为考虑到将来会涉及到类类型的返回值, 所以这里不采取枚举类型
     * 如果是returnType == "statement", 则表示任意类型的语句都可以
     */
    private String returnType;

    /**
     * Pool, 一个假的数据库, 可以通过它来查询各种当前保存的值或变量
     */
    private Pool pool;

    /**
     * 没啥好说的, 儿子们
     */
    private Node[] children;

    /**
     * 在拆树的时候, 对每个子树分别拆树, 然后最终合并到母树上
     * 合并的规则就是Operator
     */
    private Operator operator;

    public Node() {
        this(null, null, null);
    }

    public Node(Node parent, String returnType, Operator operator) {
        this.pool = new Pool();
        this.returnType = returnType;
        this.operator = operator;
        if (parent != null) {
            this.pool.addAll(parent.getPool());
        }
        if (returnType == null) {
            this.returnType = "int";
        }
        if (operator == null) {
            this.operator = pool.getOperatorByType(this.returnType);
        }
    }

    private void initChildren() {
        if (operator.getReturnType().equals("literalConstant") || operator.getReturnType().equals("variable")) return;
        ArrayList<String> argTypeList = operator.getArgTypeList();
        Random random = new Random();
        int typeNum = argTypeList.size();
        children = new Node[typeNum];
        for (int i = 0; i < typeNum; i++) {
            String type = argTypeList.get(i);
            ArrayList<Node> candidates = candidate(type);
            children[i] = candidates.get(random.nextInt(candidates.size()));
        }
    }

    /**
     * @param type 所需求的类型
     * @return 一个列表
     * 根据当前pool中包含的变量和操作得到当前能够进行的操作的集合
     * 应该有四种方式可以得到特定type的值
     * 1. 直接随机一个常量
     * 2. 从相同类型的变量中随机挑选一个
     * 3. 检查基本操作
     * 4. 检查pool中已有的方法
     */
    private ArrayList<Node> candidate(String type) {
        ArrayList<Node> candidates = new ArrayList<>();

        // 1. 直接随机一个常量
        if (type.equals("byte") || type.equals("short") || type.equals("int") || type.equals("long") || type.equals("float") || type.equals("double") || type.equals("boolean"))
            candidates.add(new Node(this, type, new Operator("literalConstant", "%s", null)));

        // 2. 从相同类型的变量中随机挑选一个
        Variable variable = pool.getVariableByType(type);
        if (variable != null) {
            ArrayList<String> tmp = new ArrayList<>();
            tmp.add(variable.getName());
            candidates.add(new Node(this, type, new Operator("variable", "%s", tmp)));
        }

        // 3. 检查基本操作
        Operator operator = pool.getOperatorByType(type);
        if (operator != null)
            candidates.add(new Node(this, type, operator));

        // 4. 检查pool中已有方法
        // TODO
        return candidates;
    }

    /**
     * @return 拆树
     * 如果是叶子节点, 那么children == null, 直接随机一个相同类型常量或者选取池中一个变量
     */
    public String toString() {
        initChildren();
        if (operator.getReturnType().equals("literalConstant"))
            return RandomUtil.randomValue(returnType);
        assert children != null;
        Object[] objects = new Object[children.length];
        System.arraycopy(children, 0, objects, 0, children.length);
        return String.format(operator.getFormat(), objects);
    }

}
