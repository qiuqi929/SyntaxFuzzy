package struct;

import lombok.Data;

@Data
public class Node<T> {

    /**
     * isFunc = True -> please look at the tree structure, we have Empty Node but it has child node !!
     *      type = null, value = null. chileNodes = .....;
     * isFunc = False
     *      type = statement type for variable/ return type for method , value = the value of double/int/String or Method name
     *
     * Or if there has other variables to describe a node? Consider it should be easy to  print it when analysing the tree.
     *
     */

    private boolean isFunc;

    private String type;

    private T value;

    private Node[] childNodes;
}
