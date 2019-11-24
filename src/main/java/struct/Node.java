package struct;

import lombok.Data;
import utils.RandomUtil;

import java.util.ArrayList;

@Data
public class Node {

    private Rule rule;
    private Object[] children;
    private Pool pool;

    public Node(Rule rule) {
        this(null, rule);
    }

    public Node(Node parent, Rule rule) {
        this.rule = rule;
        this.pool = new Pool();
        // 深拷贝父类的变量池来构建自身节点的变量池
        if (null != parent) {
            pool.getVariables().putAll(parent.getPool().getVariables());
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
            double p1 = null == value1 ? 0 : Math.random();
            // 2. 从相同类型的变量中随机的挑选一个 <- p2
            Variable value2 = pool.getRandomVariableByType(type);
            double p2 = null == value2 ? 0 : Math.random();
            // 3. 看哪些操作能生成对应类型的值 <- p3
            Function value3 = pool.getRandomFunctionByType(type);
            double p3 = null == value3 ? 0 : Math.random();

            // 根据p1, p2, p3的大小选择该节点最终进行哪个操作
            // 不知道这里会不会给我整一个空指针异常...直觉上不会
            Object value = null;
            if (p1 >= p2 && p1 >= p3) value = value1;
            if (p2 >= p1 && p2 >= p3) value = value2;
            if (p3 >= p1 && p3 >= p2) value = value3;
            children[i] = value;
        }
    }

    public String toString() {
        return rule.format(children);
    }

}
