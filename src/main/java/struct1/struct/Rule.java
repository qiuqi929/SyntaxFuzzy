package struct;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Rule {

    List<String> typelist;

    public Rule() {
        typelist = new ArrayList<>();
    }


    public void addElement(String type) {
        typelist.add(type);
    }

    public Rule(ArrayList<String> typelist) {
        this.typelist = typelist;
    }

}
