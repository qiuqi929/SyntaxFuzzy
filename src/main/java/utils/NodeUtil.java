package utils;

import struct.Node;
import struct.Pool;
import struct.Rule;

import java.util.ArrayList;

public class NodeUtil {

    public static Node newWhileNode() {
        ArrayList<String> whileTypeList = new ArrayList<>();
        whileTypeList.add("boolean");
        whileTypeList.add("object");
        String whileFormat = "while ( %s ) { %s }";
        Rule whileRule = new Rule(whileTypeList, "while", whileFormat);
        return new Node(whileRule);
    }

    public static Node newBoolNode() {
        ArrayList<String> boolTypeList = new ArrayList<>();
        boolTypeList.add("int");
        boolTypeList.add("int");
        String boolFormat = "%s < %s";
        Rule boolRule = new Rule(boolTypeList, "bool", boolFormat);
        return new Node(boolRule);
    }

    // 此处传入pool是用于从已有的变量池中选择一个相同类型的type进行初始化
    // 或者利用字面常量值进行初始化
    // 此处的结构设计很差, 期望不进行传参, 和其他的初始化节点保持一致
    // 但不要紧, 先实现了功能再说
    // 突然想到这里可以用parentNode的pool对这个进行初始化, 先记着
    public static Node newDeclareNode(Pool pool) {
        ArrayList<String> declareTypeList = new ArrayList<>();
        String type = RandomUtil.randomType();
        String name = RandomUtil.randomName();
        declareTypeList.add(type);
        String declareFormat = type + " " + name + " = %s";
        Rule declareRule = new Rule(declareTypeList, "declare", declareFormat);
        return new Node(declareRule);
    }


}
