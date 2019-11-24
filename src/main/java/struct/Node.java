package struct;

import lombok.Data;
import pool.FunctionPool;
import pool.VariablePool;
import utils.RandomUtil;

import java.util.ArrayList;

@Data
public class Node {

    private Rule rule;
    private Object[] children;
    private VariablePool variablePool;
    private FunctionPool functionPool;

    public Node(Rule rule) {
        this(null, rule);
    }

    public Node(Node parent, Rule rule) {
        this.rule = rule;
        this.variablePool = new VariablePool();
        this.functionPool = new FunctionPool();
        // 深拷贝父类的变量池来构建自身节点的变量池
        if (null != parent) {
            variablePool.getTypeToVariables().putAll(parent.getVariablePool().getTypeToVariables());
            // functionPool....
        }
        init();
    }

    private void init() {
        ArrayList<String> typeList = rule.getTypeList();
        int size = typeList.size();
        String ruleName = rule.getName();
        children = new Object[size];
        for (int i = 0; i < size; i++) {
            String type = typeList.get(i);
            // 一共有三种方法生成对应的值
            // 1. 直接随机一个常量 <- p1
            String value1 = RandomUtil.randomValue(type);
            // 2. 从相同类型的变量中随机的挑选一个 <- p2
            String value2 = variablePool.getRandomVariableByType(type);
            // 3. 看哪些操作能生成对应类型的值 <- p3
        }
    }

    public String toString() {
        return rule.format(children);
    }

}
