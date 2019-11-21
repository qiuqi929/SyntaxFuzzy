package struct;

import lombok.Data;

import java.util.List;

@Data
public class Node {

    /**
     * isFunc = True -> please look at the tree structure, we have Empty Node but it has child node !!
     *      type = null, value = null. chileNodes = .....;
     * isFunc = False
     *      type = statement type for variable/ return type for method , value = the value of double/int/String or Method name
     *
     * Or if there has other variables to describe a node? Consider it should be easy to  print it when analysing the tree.
     *
     */


    private String type;

    private String value;

    private List<Node> childNodes;

    public Node(String type, String value){
        this.type = type;
        this.value = value;
    }
}
