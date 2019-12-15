package struct;

import lombok.Data;

@Data
public class Operator {

    String returnType;
    Rule rule;
    String name;
    String format;

    public Operator (String returnType, Rule rule, String name, String format) {
        this.returnType = returnType;
        this.rule = rule;
        this.name = name;
        this.format = format;
    }

    public Operator (String returnType, Rule rule, String name) {
        this.returnType = returnType;
        this.rule = rule;
        this.name = name;
    }
}
