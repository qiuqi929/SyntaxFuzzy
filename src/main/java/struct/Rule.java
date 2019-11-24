package struct;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Rule {

    private ArrayList<String> typeList;
    private String name;
    private String format;

    public Rule(ArrayList<String> typeList, String name, String format) {
        this.typeList = typeList;
        this.format = format;
        this.name = name;
    }

    public String format(Object... objects) {
        return String.format(format, objects);
    }

}
