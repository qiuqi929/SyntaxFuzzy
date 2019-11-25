package struct;

import lombok.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

@Data
public class Operator {

    private String returnType;
    private ArrayList<String> operatorList;
    private ArrayList<String> argumentList;
    // 这里用ArrayList<String>不是很妥当, 应该用ArrayList<List<String>>
    // 因为每个operator的参数都是一组类型

    public Operator(String returnType, ArrayList<String> operatorList, ArrayList<String> argumentList) {
        this.operatorList = operatorList;
        this.argumentList = argumentList;
        this.returnType = returnType;
    }

    /**
     * 返回类型几乎都是基本数据类型, 除了字符串的拼接
     * 定义好这个方法应该是个很类的活...
     * 后期可以把这个方法改成从文件进行初始化
     *
     * @return 预定义好的各种operator
     * +, -, *, /, +=, -=, *=, /=,
     * &, |, !, ^, &=, |=, ^=,
     * >, >=, <, <=, ==, !=
     */
    public static ArrayList<Operator> initOperators() {
        String line;
        Scanner scanner = null;
        ArrayList<String> operatorList;
        ArrayList<String> argumentList;
        ArrayList<Operator> operators = new ArrayList<>();
        try {
            scanner = new Scanner(new FileInputStream("operator.init"));
        } catch (FileNotFoundException e) {
            System.err.println("init operators failed");
            System.exit(-1);
        }
        while (scanner.hasNext()) {
            line = nextLine(scanner);
            String returnType = line.trim();
            line = nextLine(scanner);
            String[] ops = line.split(" ");
            line = nextLine(scanner);
            String[] args = line.split(" ");
            operatorList = new ArrayList<>();
            argumentList = new ArrayList<>();
            Collections.addAll(operatorList, ops);
            Collections.addAll(argumentList, args);
            operators.add(new Operator(returnType, operatorList, argumentList));
        }
        return operators;
    }

    private static String nextLine(Scanner scanner) {
        String line;
        while ((line = scanner.nextLine()) == null || line.length() == 0 || line.startsWith("#")) ;
        return line;
    }

}
