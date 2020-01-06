package pool;

import initial.Initialize;
import struct.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VariablePool implements Pool<Variable> {

    private List<Variable> variablePool;

    private Random random = Initialize.random;

    public VariablePool() {
        variablePool = new ArrayList<>();
    }

    public VariablePool(List<Variable> arrayList) {
        variablePool = new ArrayList<>(arrayList);
    }

    public VariablePool(VariablePool pool) {
        variablePool = new ArrayList<>(pool.getPoolList());
    }

    public List<Variable> getPoolList() {
        return this.variablePool;
    }

    public void addElement(Variable variable) {
        variablePool.add(variable);
    }

    public Variable randomElement() {
        int randomInt = random.nextInt(variablePool.size());
        return variablePool.get(randomInt);
    }

}
