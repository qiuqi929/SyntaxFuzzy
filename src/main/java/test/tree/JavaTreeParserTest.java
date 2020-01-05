package tree;


import initial.Initialize;
import org.junit.Before;
import org.junit.Test;
import struct.Operator;

import java.io.IOException;
import java.io.PrintWriter;
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
        System.out.println(JavaTreeParser.generateMethod(0));
    }

    @Test
    public void testAddNewMethod() {
        List<Operator> operatorList = Initialize.operatorPool.getPoolList();
        System.out.println(operatorList);
        JavaTreeParser.generateMethod(0);
        System.out.println(operatorList);
        JavaTreeParser.generateMethod(0);
        System.out.println(operatorList);
    }

    @Test
    public void testUseMethod() {
        for (int i = 0; i < 100; i++) JavaTreeParser.generateMethod(0);
        System.out.println(JavaTreeParser.generateMethod(0));
    }

    @Test
    public void testGenerateClass() throws IOException {

        String template = "public class Main{\n" +
                "\t\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\t\n" +
                "\t}\n" +
                "\n" +
                "%s\n" +
                "\n" +
                "}";
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            s.append(JavaTreeParser.generateMethod(1)).append('\n');
        }
        PrintWriter printWriter = new PrintWriter("Main.java");
        printWriter.println(String.format(template, s.toString()));
        printWriter.flush();
    }

}
