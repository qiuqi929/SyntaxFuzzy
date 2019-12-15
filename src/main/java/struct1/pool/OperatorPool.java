package pool;

import initial.Initialize;
import struct.Operator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OperatorPool implements Pool<Operator>{

    private List<Operator> operatorPool;

    private Random random = Initialize.random;

    public OperatorPool() {
        operatorPool = new ArrayList<>();
    }

    public List<Operator> getPoolList() {
        return operatorPool;
    }

    public void addElement (Operator operator) {
        operatorPool.add(operator);
    }

    // random an operator except special block
    public Operator randomElement() {
        int randomInt = random.nextInt(operatorPool.size()-1) + 1;
        return operatorPool.get(randomInt);
    }


    public Operator getSpecialBlock () {
        return operatorPool.get(0);
    }

}
