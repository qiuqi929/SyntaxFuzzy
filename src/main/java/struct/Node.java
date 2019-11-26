package struct;

import lombok.Data;
import utils.NodeUtil;
import utils.RandomUtil;

import java.util.ArrayList;
import java.util.Iterator;

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
        children = new Object[size];
        for (int i = 0; i < size; i++) {
            String type = typeList.get(i);
            // 一共有四种方法生成对应的值
            // 1. 直接随机一个常量 <- p1
            Object value0 = RandomUtil.randomValue(type);
            double p0 = null == value0 ? 0 : Math.random();
            // 2. 从相同类型的变量中随机的挑选一个 <- p2
            Object value1 = pool.getRandomVariableByType(type);
            double p1 = null == value1 ? 0 : Math.random();
            // 3. 看哪些方法能生成对应类型的值 <- p3
            Object value2 = pool.getRandomFunctionByType(type);
            double p2 = null == value2 ? 0 : Math.random();
            // 4. 看哪些基本操作能生成对应类型的值 <- p4
            Object value3 = pool.getRandomOperatorByType(type);
            double p3 = null == value3 ? 0 : Math.random();
            // 根据p1, p2, p3, p4的大小选择该节点最终进行哪个操作
            // 不知道这里会不会给我整一个空指针异常...直觉上不会
            Object value;
            Object[] objects = new Object[]{value0, value1, value2, value3};
            int indexMax = selectMax(p0, p1, p2, p3);
            value = new Object[]{value0, value1, value2, value3}[indexMax];

            //从相同类型的变量中随机的选取一个
            if (indexMax == 1) {
                Variable variable = (Variable) value;
                value = variable.name;
            }
            //根据基本操作来生成对应的值
            if (indexMax == 3) {
                Operator operator = (Operator) value;
                StringBuilder stringBuilder = new StringBuilder();
                Iterator<String> iterator1 = operator.getArgumentList().iterator();
                Iterator<String> iterator2 = operator.getOperatorList().iterator();
                while (iterator1.hasNext() || iterator2.hasNext()) {
                    if (iterator1.hasNext()) {
                        stringBuilder.append(" %s");
                        iterator1.next();
                    }
                    if (iterator2.hasNext()) {
                        stringBuilder.append(iterator2.next());
                    }
                }
                Rule rule = new Rule(operator.getArgumentList(), RandomUtil.randomName(), stringBuilder.toString());
                value = new Node(rule);
            }

            // 如果到这里value还是null, 表示目前没有一个合适的类型, 那么就随便来点变量的定义吧...
            // 这种情况通常会发生在type == "statement"的时候
            if (value == null) {
                type = RandomUtil.randomType();
                String name = RandomUtil.randomName();
                Node node = NodeUtil.newDeclareNode(this, type, name);
                pool.addVariable(type, name);
                value = node;
            }
            children[i] = value;
        }
    }

    public String toString() {
        return rule.format(children);
    }

    private int selectMax(double... doubles) {
        int index = 0;
        for (int i = 0; i < doubles.length; i++) {
            if (doubles[i] > doubles[index]) {
                index = i;
            }
        }
        return index;
    }

}
