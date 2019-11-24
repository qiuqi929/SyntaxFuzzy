import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Node whileNode = initWhileNode();
        System.out.println(whileNode);

    }

    public static Node initWhileNode() {

        ArrayList<String> whileTypeList = new ArrayList<>();
        whileTypeList.add("boolean");
        whileTypeList.add("object");
        String whileFormat = "while ( %s ) { %s }";
        Rule whileRule = new Rule(whileTypeList, whileFormat);
        Node node = new Node("void", whileRule);
        return node;

    }

    public static Node initBooleanNode() {

        ArrayList<String> boolTypeList = new ArrayList<>();
        boolTypeList.add("int");
        boolTypeList.add("int");
        return null;

    }

}
