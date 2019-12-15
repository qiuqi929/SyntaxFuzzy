package config;

import struct.Operator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Initializer {

    public static ArrayList<Operator> initOperators() {
        InputStream inputStream = Initializer.class.getClassLoader().getResourceAsStream("operator.init");
        assert inputStream != null;
        Scanner scanner = new Scanner(inputStream);
        ArrayList<Operator> operators = new ArrayList<>();
        while (scanner.hasNext()) {
            String returnType = nextLine(scanner);
            String format = nextLine(scanner);
            String args = nextLine(scanner);
            ArrayList<String> argTypeList = new ArrayList<>();
            Collections.addAll(argTypeList, args.split(" "));
            operators.add(new Operator(returnType, format, argTypeList));
        }
        return operators;
    }

    private static String nextLine(Scanner scanner) {
        String line;
        while ((line = scanner.nextLine()) == null || line.length() == 0 || line.startsWith("#")) ;
        return line;
    }

}
