package initial;

import pool.OperatorPool;
import pool.TypePool;
import struct.Operator;
import struct.Rule;

import java.io.*;
import java.util.*;


public class Initialize {

    public static Random random = new Random();

    public static TypePool typePool = new TypePool();

    public static OperatorPool operatorPool = new OperatorPool();

    public static void initialTypePool() {
        typePool.addElement("void");
        typePool.addElement("byte");
        typePool.addElement("char");
        typePool.addElement("short");
        typePool.addElement("int");
        typePool.addElement("long");
        typePool.addElement("float");
        typePool.addElement("double");
        typePool.addElement("boolean");
        typePool.addElement("String");
    }

    public static void initialOperatorPool() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Initialize.class.getClassLoader().getResourceAsStream("operatorInit.txt"))));
        String[] line = new String[4];
        int readLineNum = 0;

        while ((line[readLineNum] = br.readLine()) != null) {
            if (readLineNum == 3) {
                readLineNum = 0;
                String returnType = line[0];
                String[] types = line[1].split(" ");
                List<String> typeList = new ArrayList<>(Arrays.asList(types));
                Rule rule = new Rule(typeList);
                String format = line[2];
                if (format.equals("block")) {
                    rule = null;
                }
                Operator operator = new Operator(returnType, rule, format);
                operatorPool.addElement(operator);
            } else {
                readLineNum++;
            }
        }

    }


}
