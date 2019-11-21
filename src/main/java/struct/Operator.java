package struct;

import lombok.Data;

@Data
public class Operator {

    /**
     * Why we need type? Find type by Operator, and find operator by type.
     *
     * Why we need rule? the rule of this operator.
     *
     * why we need value? what the operator is? primitive operators such as +-*%/ or method num?
     */

    private String type;

    private Rule rule;

    private String value;

}
