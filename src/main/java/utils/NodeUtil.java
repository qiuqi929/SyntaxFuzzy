package utils;

import struct.Node;
import struct.Rule;

import java.util.ArrayList;

public class NodeUtil {

    public static Node newWhileNode() {
        ArrayList<String> whileTypeList = new ArrayList<>();
        whileTypeList.add("boolean");
        whileTypeList.add("object");
        String whileFormat = "while ( %s ) { %s }";
        Rule whileRule = new Rule(whileTypeList, whileFormat);
        Node node = new Node("void", whileRule);
        return node;
    }

    public static Node newBoolNode() {
        ArrayList<String> boolTypeList = new ArrayList<>();
        boolTypeList.add("int");
        boolTypeList.add("int");
        String boolFormat = "%s < %s";
        Rule boolRule = new Rule(boolTypeList, boolFormat);
        Node node = new Node("boolean", boolRule);
        return node;
    }


}
