package struct;

import lombok.Data;
import utils.NodeUtil;
import utils.RandomUtil;

import java.util.ArrayList;

@Data
public class Node {

    private Rule rule;
    private Object[] children;
    private ElementPool pool;

    public Node(Rule rule) {
        this(null, rule);
    }

    public Node(Node parent, Rule rule) {
        this.rule = rule;
        this.pool = new ElementPool();
        // 深拷贝父类的变量池来构建自身节点的变量池
        if (null != parent)
            pool.getTypeToVariables().putAll(parent.getPool().getTypeToVariables());
        init();
    }

    private void init() {
        ArrayList<String> typeList = rule.getTypeList();
        int size = typeList.size();
        children = new Object[size];
        for (int i = 0; i < size; i++) {
            String type = typeList.get(i);
            // TODO: 除了直接获取字面常量的类型, 都应修改成对应类型的Node
            // TODO: 把所有的Type改成枚举类型
            // TODO: 增添自定类
            if ("boolean".equals(type)) {
                children[i] = NodeUtil.newBoolNode();
            } else if ("object".equals(type)) {
                children[i] = "System.out.println(\"Hello World\");";
            } else if ("int".equals(type)) {
                // 此处应不仅有字面常量值, 还可以从已有的int类型变量中选取一个
                children[i] = RandomUtil.randomInt();
            }
        }
    }

    public String toString() {
        return rule.format(children);
    }

}
