package struct;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Function {

    String name;
    String type;
    ArrayList<String> args; // 这里的args表示需要哪些类型的参数
    ArrayList<String> modifiers;

    public Function(String name, String type, ArrayList<String> args, ArrayList<String> modifiers) {
        this.name = name;
        this.type = type;
        this.args = args;
        this.modifiers = modifiers;
    }

}
