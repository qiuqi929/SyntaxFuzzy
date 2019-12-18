package struct;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Operator {

    String returnType;
    Rule rule;
    String format; // primitive operator has format

    public Operator (String returnType, Rule rule, String format) {
        this.returnType = returnType;
        this.rule = rule;
        this.format = format;
    }

}
