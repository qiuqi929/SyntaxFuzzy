package struct;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Operator {

    private int operandNum;
    private String returnType;
    private ArrayList<String> operatorList;
    private ArrayList<String> argumentList;
    // 这里用ArrayList<String>不是很妥当, 应该用ArrayList<List<String>>
    // 因为每个operator的参数都是一组类型

    public Operator(int operandNum, String returnType, ArrayList<String> operatorList, ArrayList<String> argumentList) {
        this.operandNum = operandNum;
        this.operatorList = operatorList;
        this.argumentList = argumentList;
        this.returnType = returnType;
    }

    /**
     * 返回类型几乎都是基本数据类型, 除了字符串的拼接
     * 定义好这个方法应该是个很类的活...
     *
     * @return 预定义好的各种operator
     * +, -, *, /, +=, -=, *=, /=,
     * &, |, !, ^, &=, |=, ^=,
     * >, >=, <, <=, ==, !=
     */
    public static ArrayList<Operator> operators() {
        ArrayList<Operator> operators = new ArrayList<>();
        int operandNum;
        String returnType;
        ArrayList<String> operatorList;
        ArrayList<String> argumentList;

        // +
        operandNum = 2;
        returnType = "int";
        operatorList = new ArrayList<>();
        argumentList = new ArrayList<>();
        operatorList.add("+");
        argumentList.add("int");
        argumentList.add("int");
        operators.add(new Operator(operandNum, returnType, operatorList, argumentList));

        return operators;
    }

}
