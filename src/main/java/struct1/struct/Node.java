package struct;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class Node {
    String value;
    Variable variable;
    Operator operator;
    List<Node> childNode;
    Node parent;

    /**
     * Constant value Node
     * @param value
     * @param parent
     */
    public Node (String value, Node parent) {
        this.value = value;
        this.parent = parent;
    }

    /**
     * Operator Node
     * @param operator
     * @param parent
     */
    public Node (Operator operator, Node parent) {
        this.operator = operator;
        this.parent = parent;
    }

    /**
     * Variable Node
     * @param variable
     * @param parent
     */
    public Node (Variable variable, Node parent) {
        this.variable = variable;
        this.parent = parent;
    }

    /**
     * NULL Node
     */
    public Node(Node parent) {
        childNode = new ArrayList<>();
        this.parent = parent;
    }

    public void addChild (Node child) {
        childNode.add(child);
    }

}
