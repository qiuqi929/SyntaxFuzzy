package struct;

import lombok.Data;
import utils.RandomUtil;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class Pool {

    // 这三个后期应该能整合到一起, 不然会有大量重复的代码
    private HashMap<String, ArrayList<Variable>> variables;
    private HashMap<String, ArrayList<Function>> functions;
    private HashMap<String, ArrayList<Operator>> operators;

    public Pool() {
        this.variables = new HashMap<>();
        this.functions = new HashMap<>();
        this.operators = new HashMap<>();

        //TODO: 从文件中初始化变量
        //TODO: 从文件中初始化方法
        ArrayList<Operator> ops = Operator.initOperators();
        for (Operator operator : ops) {
            String returnType = operator.getReturnType();
            if (!operators.containsKey(returnType)) {
                operators.put(returnType, new ArrayList<>());
            }
            operators.get(returnType).add(operator);
        }

    }

    public ArrayList<Variable> getVariablesByType(String type) {
        if (variables.containsKey(type))
            return variables.get(type);
        return new ArrayList<>();
    }

    public ArrayList<Function> getFunctionsByType(String type) {
        if (functions.containsKey(type))
            return functions.get(type);
        return new ArrayList<>();
    }

    public ArrayList<Operator> getOperatorByType(String type) {
        if (operators.containsKey(type)) {
            return operators.get(type);
        }
        return new ArrayList<>();
    }

    /**
     * @param type 所需求的变量类型
     * @return 如果pool中已经有了同种类型的变量, 则直接随机返回一个变量名, 否则将随机地返回一个字面常量值
     */
    public Variable getRandomVariableByType(String type) {
        if (!variables.containsKey(type))
            return null;
        ArrayList<Variable> variableList = variables.get(type);
        return variableList.get(RandomUtil.randomInt(0, variableList.size()));
    }

    /**
     * 几乎和楼上一模一样的操作, 仅仅只是把名字换了一遍, 如果这里能有JPA就能方便很多,
     * 但是这样的话在运行时进行读写就会进行文件IO,效率巨慢。
     * 或许这里可以考虑一下redis, 或者H2 等等一些nosql
     */
    public Function getRandomFunctionByType(String type) {
        if (!functions.containsKey(type))
            return null;
        ArrayList<Function> functionList = functions.get(type);
        return functionList.get(RandomUtil.randomInt(0, functionList.size()));
    }

    public Operator getRandomOperatorByType(String type) {
        if (!operators.containsKey(type))
            return null;
        ArrayList<Operator> operatorList = operators.get(type);
        return operatorList.get(RandomUtil.randomInt(0, operatorList.size()));
    }

    public void addVariable(String type, String name) {
        addVariable(type, new Variable(type, name));
    }

    public void addVariable(String type, Variable variable) {
        if (!variables.containsKey(type))
            variables.put(type, new ArrayList<>());
        ArrayList<Variable> list = variables.get(type);
        // 此处通过list来判断是否已经存在variable是一种很低效的方式
        // 但是可以较方便地随机获取一个该类型的变量
        if (!list.contains(variable))
            list.add(variable);
    }

    public void addFunction(String name, String type, ArrayList<String> args, ArrayList<String> modifiers) {
        addFunction(type, new Function(name, type, args, modifiers));
    }

    public void addFunction(String type, Function function) {
        // 这里假设不会加入已经加入过的function, 但似乎就算已经加入了也无所谓?
        if (!functions.containsKey(type))
            functions.put(type, new ArrayList<>());
        functions.get(type).add(function);
    }

    // operators应该是预定义好的, 因此这里没有addOperator的操作

}
