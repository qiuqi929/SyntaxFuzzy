package struct;

import lombok.Data;
import utils.NodeUtil;
import utils.RandomUtil;

import java.util.ArrayList;

@Data
public class Node {

    private String returnType;
    private String value;
    private Object[] children;
    private Rule rule;

    public Node(String returnType, Rule rule) {
        this.returnType = returnType;
        this.rule = rule;
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
                children[i] = RandomUtil.randomInt();
            }
        }
    }

    public String toString() {
        return rule.format(children);
    }

}
