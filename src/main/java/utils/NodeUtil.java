package utils;

import struct.Node;
import struct.Rule;

import java.util.ArrayList;

public class NodeUtil {

    public static Node newWhileNode(Node parent) {
        // TODO: 当前还不能避免while(false)情况的出现
        ArrayList<String> whileTypeList = new ArrayList<>();
        whileTypeList.add("boolean");
        // 这里的1, 10表示在这个while里面一共要生成多少条语句
        int statementNum = RandomUtil.randomInt(1, 10);
        String whileFormat = "while ( %s ) {%n";
        for (int i = 0; i < statementNum; i++) {
            whileTypeList.add("statement");
            whileFormat += "\t%s;%n";
        }
        whileFormat += "}";
        Rule whileRule = new Rule(whileTypeList, "while", whileFormat);
        return new Node(parent, whileRule);
    }

    public static Node newForNode(Node parent) {
        ArrayList<String> forTypeList = new ArrayList<>();
        forTypeList.add("statement");
        forTypeList.add("boolean");
        forTypeList.add("int");
        int statementNum = RandomUtil.randomInt(1, 10);
        String forFormat = "for( %s; %s; %s ){%n";
        for (int i = 0; i < statementNum; i++) {
            forTypeList.add("statement");
            forFormat += "\t%s;%n";
        }
        forFormat += "}";
        Rule forRule = new Rule(forTypeList, "for", forFormat);
        return new Node(parent, forRule);
    }

    public static Node newDoWhileNode(Node parent) {
        ArrayList<String> typeList = new ArrayList<>();
        int statementNum = RandomUtil.randomInt(1, 10);
        String format = "do{%n";
        for (int i = 0; i < statementNum; i++) {
            typeList.add("statement");
            format += "\t%s;%n";
        }
        format += "} while( %s );";
        typeList.add("boolean");
        Rule rule = new Rule(typeList, "doWhile", format);
        return new Node(parent, rule);
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
    public static Node newDeclareNode(Node parent, String type, String name) {
        ArrayList<String> declareTypeList = new ArrayList<>();
        declareTypeList.add(type);
        String declareFormat = type + " " + name + " = %s";
        Rule declareRule = new Rule(declareTypeList, "declare", declareFormat);
        return new Node(parent, declareRule);
    }


}
