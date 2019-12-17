package pool;

import initial.Initialize;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TypePool implements Pool<String>{

    private List<String> typePool;

    private Random random = Initialize.random;

    public TypePool(){
        typePool = new ArrayList<>();
    }

    public List<String> getPoolList(){
        return this.typePool;
    }

    public void addElement(String value){
        typePool.add(value);
    }

    // random an element
    public String randomElement(){
        int randomInt = random.nextInt(typePool.size());
        return typePool.get(randomInt);
    }


    // random an element except void. The first element is void
    public String randomElementExceptVoid() {
        int randomInt = random.nextInt(typePool.size() - 1) + 1;
        return typePool.get(randomInt);
    }


}
