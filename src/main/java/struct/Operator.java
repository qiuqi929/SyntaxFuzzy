package struct;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Operator {

    private String returnType;
    private String format;
    private ArrayList<String> argTypeList;

}
