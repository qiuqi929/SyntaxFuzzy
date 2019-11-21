package enums;

import lombok.Getter;

@Getter
public enum PrimitiveMethodOperEnum {

    /**
     * Why we need this Method Operator Enum?
     *
     * We need to initialize some methods such as + - * / % < > = ..
     * And We may add some methods that java lib has.
     *
     * We use this Enum to initialize MethodOperatorPool.
     * So, if we add some new operators, it will initialize it when running the program.
     *
     *
     * Why we use the String?
     *
     * We don't care what the operator does. We only need to record it in the node and finally print it.
     */

    ADD("+");

    private String operator;

    PrimitiveMethodOperEnum (String operator) {
        this.operator = operator;
    }
}
