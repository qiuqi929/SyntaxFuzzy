package struct;

import lombok.Data;

@Data
public class Variable {

    String type;
    String name;
    boolean init;

    public Variable(String type, String name) {
        this(type, name, false);
    }

    public Variable(String type, String name, boolean init) {
        this.type = type;
        this.name = name;
        this.init = init;
    }

}
