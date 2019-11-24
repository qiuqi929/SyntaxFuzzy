package struct;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Rule {

    private ArrayList<String> typeList;
    private String format;

    public Rule(ArrayList<String> typeList, String format) {
        this.typeList = typeList;
        this.format = format;
    }

    public String format(Object... objects) {
        return String.format(format, objects);
    }

}
