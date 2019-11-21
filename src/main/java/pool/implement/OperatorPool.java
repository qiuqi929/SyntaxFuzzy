package pool.implement;

import pool.Pool;
import struct.Operator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class OperatorPool implements Pool<Operator> {

    // We set the special Block "{}" as the first one.
    private List<Operator> operatorPool = new ArrayList<Operator>();

    private Random random = new Random();

    public void addElement(Operator operator){
        operatorPool.add(operator);
    }

    public List<Operator> getPoolList() {
        return operatorPool;
    }

    /**
     * Random a block operator except a special
     * @return
     */
    public Operator randomElement() {
        int randomInt = random.nextInt(operatorPool.size()-1) + 1;
        return operatorPool.get(randomInt);
    }

    public Operator getSpecialBlock() {
        return operatorPool.get(0);
    }


}
