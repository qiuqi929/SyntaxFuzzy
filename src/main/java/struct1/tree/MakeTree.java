package tree;

import initial.InitPool;
import struct.Node;
import utils.RandomName;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MakeTree {

    private static Random random = new Random();

    private static final int parameterNumber = 6;

    public static Node blockContent(Pool variablePool, Node parent) {
        Node HeadNode = new Node(parent);
        // the first child node is special block.
        HeadNode.addChild(new Node(InitPool.blockOperator, HeadNode));


        return HeadNode;
    }


    public static void buildMethod () {
        // random method name
        String methodName = RandomName.randomMethodName();
        // random return type
//        String returnType = pool.

        // random parameter
        int parameterNum = random.nextInt(parameterNumber+1);
        List<String> parameterList = new ArrayList<>();
//        for (int i = 0; i < ; i++) {
//
//        }

    }

}
