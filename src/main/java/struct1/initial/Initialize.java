package initial;

import pool.OperatorPool;
import pool.Pool;
import pool.TypePool;
import struct.Operator;
import struct.Rule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Initialize {

    public static Random random = new Random();

    public static TypePool typePool = new TypePool();

    public static OperatorPool OperatorPool = new OperatorPool();

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
    }

    public static void initialOperatorPool() throws IOException {
        String file = "operatorInit.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line[] = new String[4];
        int readLineNum = 0;

        while((line[readLineNum] = reader.readLine()) != null){
            System.out.println("line: " + line);
            if (readLineNum == 4) {
                readLineNum = 0;
                String returnType = line[0];
                String[] types = line[1].split(" ");
                System.out.println(Arrays.toString(types));
                List<String> typelist = new ArrayList<>();
                for (int i = 0; i < types.length; i++) {
                    typelist.add(types[i]);
                }
                Rule rule = new Rule(typelist);
                String format = line[2];
                Operator operator = new Operator(returnType,rule,format);
            }else {
                readLineNum++;
            }
        }

    }




}
