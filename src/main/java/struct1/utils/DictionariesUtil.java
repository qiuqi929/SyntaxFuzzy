package utils;

import initial.Initialize;
import pool.OperatorPool;
import pool.VariablePool;
import struct.Operator;
import struct.Rule;
import struct.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DictionariesUtil {

    private static Random random = Initialize.random;

    /**
     * Get the operator's Rule by operator
     * @param operator
     * @return
     */
    public static Rule getRuleByOperator(Operator operator) {
        return operator.getRule();
    }

    /**
     * Get the typelist by Rule
     * @param rule
     * @return
     */
    public static List<String> getTypesByRule (Rule rule) {
        return rule.getTypelist();
    }

    /**
     * Random a variable by giving type.
     * (The type of variable is giving type)
     * @param variablePool
     * @param type
     * @return
     */
    public static Variable findVariableByType (VariablePool variablePool, String type) {
        List<Variable> variableList = findVariablesByType(variablePool, type);
        Variable variable = randomVariable(variableList);
        return variable;
    }

    private static List<Variable> findVariablesByType (VariablePool variablePool, String type) {
        List<Variable> validVariables = new ArrayList<>();
        List<Variable> variableList = variablePool.getPoolList();
        for(Variable variable: variableList) {
            if (variable.getType().equals(type)) {
                validVariables.add(variable);
            }
        }
        return validVariables;
    }

    private static Variable randomVariable (List<Variable> variableList) {
        int randomInt = random.nextInt(variableList.size());
        return variableList.get(randomInt);
    }



    private static OperatorPool operatorPool = Initialize.OperatorPool;

    /**
     * Random a operator by giving type.
     * (The return type of operator is giving type)
     * Consider: the operator has the return type. It must not have block. (such as while/for/if)
     * @param type
     * @return
     */

    public static Operator findOperatorByType(String type) {
        List<Operator> operatorList = findOperatorsByType(type);
        Operator operator = randomOperator(operatorList);
        return operator;
    }

    private static List<Operator> findOperatorsByType (String type) {
        List<Operator> validOperators = new ArrayList<>();
        List<Operator> operatorList = operatorPool.getPoolList();
        for(Operator operator: operatorList) {
            if (operator.getReturnType().equals(type)) {
                validOperators.add(operator);
            }
        }
        return validOperators;
    }

    private static Operator randomOperator (List<Operator> operatorList) {
        int randomInt = random.nextInt(operatorList.size());
        return operatorList.get(randomInt);
    }


}
