package initial;

import pool.OperatorPool;
import pool.TypePool;
import struct.Operator;
import struct.Rule;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


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
        String filename = "C:\\Users\\qiuqi\\OneDrive\\Java\\SyntaxFuzzy\\src\\main\\java\\struct1\\initial\\operatorInit.txt";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line[] = new String[4];
        int readLineNum = 0;

        while((line[readLineNum] = br.readLine()) != null){
            if (readLineNum == 3) {
                readLineNum = 0;
                String returnType = line[0];
                String[] types = line[1].split(" ");
                List<String> typelist = new ArrayList<>();
                for (int i = 0; i < types.length; i++) {
                    typelist.add(types[i]);
                }
                Rule rule = new Rule(typelist);
                String format = line[2];
                if(format.equals("block")){
                    rule = null;
                }
                Operator operator = new Operator(returnType,rule,format);
                operatorPool.addElement(operator);
            }else {
                readLineNum++;
            }
        }

    }




}
