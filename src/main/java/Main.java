import struct.Node;
import struct.Operator;
import utils.NodeUtil;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Node whileNode = NodeUtil.newWhileNode();
        System.out.println(whileNode);

        ArrayList<Operator> operators = Operator.initOperators();
        System.out.println(operators);
    }

}
