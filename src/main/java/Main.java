import struct.Node;
import utils.NodeUtil;

public class Main {

    public static void main(String[] args) {
        Node whileNode = NodeUtil.newWhileNode(null);
        Node forNode = NodeUtil.newForNode(null);
        Node doWhileNode = NodeUtil.newDoWhileNode(null);
        System.out.println(whileNode);
        System.out.println(forNode);
        System.out.println(doWhileNode);
    }

}
