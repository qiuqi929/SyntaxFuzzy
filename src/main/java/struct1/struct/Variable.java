package struct;

import lombok.Data;

@Data
public class Variable {

    String type;
    String name;
//    String value;

    public Variable (String type, String name) {
        this.type = type;
        this.name = name;
//        this.value = value;
    }

}
