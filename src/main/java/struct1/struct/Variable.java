package struct;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class Variable {

    String type;
    String name;

    public Variable(String type, String name) {
        this.type = type;
        this.name = name;
    }

}
