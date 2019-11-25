package struct;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Operator {

    private int operandNum;
    private ArrayList<String> operatorList;
    private ArrayList<String> argumentList;
    private String returnType;

    public Operator(int operandNum, String returnType, ArrayList<String> operatorList, ArrayList<String> argumentList) {
        this.operandNum = operandNum;
        this.operatorList = operatorList;
        this.argumentList = argumentList;
        this.returnType = returnType;
    }

}
