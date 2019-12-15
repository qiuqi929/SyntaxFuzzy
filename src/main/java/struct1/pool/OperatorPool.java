package pool;

import struct.Operator;

import java.util.List;
import java.util.Random;

public class OperatorPool {

    List<Operator> operatorPool;

    private Random random = new Random();

    public void addElement (Operator operator) {
        operatorPool.add(operator);
    }

    // random operator except special block
    public Operator randomElement() {
        int randomInt = random.nextInt(operatorPool.size()-1) + 1;
        return operatorPool.get(randomInt);
    }

    public List<Operator> getPoolList() {
        return operatorPool;
    }

    public Operator getSpecialBlock () {
        return operatorPool.get(0);
    }

}
