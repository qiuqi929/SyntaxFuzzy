package tree;


import initial.Initialize;
import org.junit.Before;
import org.junit.Test;
import struct.Operator;

import java.io.IOException;
import java.util.List;

public class JavaTreeParserTest {

    @Before
    public void before() {
        Initialize.initialTypePool();
        try {
            Initialize.initialOperatorPool("java");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGenerateMethod() {
        System.out.println(JavaTreeParser.generateMethod());
    }

    @Test
    public void testAddNewMethod() {
        List<Operator> operatorList = Initialize.operatorPool.getPoolList();
        System.out.println(operatorList);
        JavaTreeParser.generateMethod();
        System.out.println(operatorList);
        JavaTreeParser.generateMethod();
        System.out.println(operatorList);
    }

    @Test
    public void testUseMethod() {
        for (int i = 0; i < 100; i++) JavaTreeParser.generateMethod();
        System.out.println(JavaTreeParser.generateMethod());
    }

}
