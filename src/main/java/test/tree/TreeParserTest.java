package tree;


import initial.Initialize;
import org.junit.Before;
import org.junit.Test;
import struct.Operator;

import java.io.IOException;
import java.util.List;

public class TreeParserTest {

    @Before
    public void before() {
        Initialize.initialTypePool();
        try {
            Initialize.initialOperatorPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGenerateMethod() {
        System.out.println(TreeParser.generateMethod());
    }

    @Test
    public void testAddNewMethod() {
        List<Operator> operatorList = Initialize.operatorPool.getPoolList();
        System.out.println(operatorList);
        TreeParser.generateMethod();
        System.out.println(operatorList);
        TreeParser.generateMethod();
        System.out.println(operatorList);
    }

    @Test
    public void testUseMethod() {
        for (int i = 0; i < 100; i++) TreeParser.generateMethod();
        System.out.println(TreeParser.generateMethod() );
    }

}
