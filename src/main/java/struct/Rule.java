package struct;

import lombok.Data;

@Data
public class Rule {


    /**
     * Len(typeList) = 0   - > no parameter
     * Len(typeList) = null  - > no limit.
     */

    String[] typeList;

    public Rule(String[] typeList){
        this.typeList = typeList;
    }
}
