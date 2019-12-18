package struct;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Rule {

    List<String> typelist;

    public Rule() {
        typelist = new ArrayList<>();
    }

    public void addElement(String type) {
        typelist.add(type);
    }

    public Rule(List<String> typelist) {
        this.typelist = typelist;
    }

}
