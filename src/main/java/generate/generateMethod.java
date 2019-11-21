package generate;

import struct.Node;
import utils.NameRandomUtil;

public class generateMethod {

    public static void generateMethod () {

    }


    public static Node generateTree() {
        Node node = new Node(true, "void", NameRandomUtil.randomMethodName());


        return node;
    }

}
