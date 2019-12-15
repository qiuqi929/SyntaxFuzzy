package struct;

import config.Initializer;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

@Data
public class Pool {

    private HashMap<String, ArrayList<Variable>> variables;
    private HashMap<String, ArrayList<Function>> functions;
    private HashMap<String, ArrayList<Operator>> operators;

    public Pool() {
        initVariables();
        initFunctions();
        initOperators();
    }

    private void initVariables() {
        this.variables = new HashMap<>();
    }

    private void initFunctions() {
        this.functions = new HashMap<>();
    }

    private void initOperators() {
        this.operators = new HashMap<>();
        ArrayList<Operator> ops = Initializer.initOperators();
        ops.forEach(operator -> {
            String type = operator.getReturnType();
            if (!operators.containsKey(type))
                operators.put(type, new ArrayList<>());
            operators.get(type).add(operator);
        });
    }

    public void addAll(Pool pool) {
        variables.putAll(pool.getVariables());
        functions.putAll(pool.getFunctions());
        operators.putAll(pool.getOperators());
    }

    public Variable getVariableByType(String type) {
        if (!variables.containsKey(type))
            return null;
        ArrayList<Variable> vars = variables.get(type);
        return vars.get(new Random().nextInt(vars.size()));
    }

    public Operator getOperatorByType(String type) {
        if (!operators.containsKey(type))
            return null;
        ArrayList<Operator> ops = operators.get(type);
        ArrayList<Operator> tmp = new ArrayList<>();
        if (!variables.containsKey(type)) {
            ops.forEach(operator -> {
                if (!operator.getArgTypeList().contains("variable"))
                    tmp.add(operator);
            });
        }
        return tmp.get(new Random().nextInt(tmp.size()));
    }

}
