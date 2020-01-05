package tree;


import initial.Initialize;
import org.junit.Before;
import org.junit.Test;
import struct.Operator;
import struct.Rule;
import utils.ConstantUtil;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

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

    @Test(timeout = 3000)
    public void testGenerateClass() throws IOException {

        List<Operator> operatorList = Initialize.operatorPool.getPoolList();
        String template = "public class Main{\n" +
                "\t\n" +
                "\tpublic static void main(String[] args) {\n" +
                "%s\n" +
                "\t}\n" +
                "\n" +
                "%s\n" +
                "\n" +
                "}";
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            s.append(JavaTreeParser.generateMethod(1)).append('\n');
        }


        StringBuilder finalS = new StringBuilder();
        operatorList.stream()
                .filter(o -> o.getFormat().contains("Method"))
                .forEach(o -> {
                    Rule rule = o.getRule();
                    List<String> typeList = rule.getTypelist();
                    Object[] args = new Object[typeList.size()];
                    for (int i = 0, size = args.length; i < size; i++) {
                        args[i] = ConstantUtil.randomConstantByType(typeList.get(i));
                    }
                    String use = String.format(o.getFormat(), args);
                    finalS.append("\t\t").append(use).append(";\n");
                });


        PrintWriter printWriter = new PrintWriter("Main.java");
        printWriter.println(String.format(template, finalS.toString(), s.toString()));
        printWriter.flush();
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int res = compiler.run(null, null, null, "Main.java");

        if (res == 0) {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec("java Main");
            s = new StringBuilder();
            Scanner in = new Scanner(p.getInputStream());
            while (in.hasNextLine()) s.append(in.nextLine()).append('\n');
            System.out.println(s.toString());
            System.out.println("process successfully");
        } else {
            System.err.println("compile error");
        }
    }

}
