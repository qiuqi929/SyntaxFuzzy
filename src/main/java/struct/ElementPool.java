package struct;

import lombok.Data;
import utils.RandomUtil;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class ElementPool {

    private HashMap<String, ArrayList<String>> typeToVariables;

    public ElementPool() {
        this.typeToVariables = new HashMap<>();
    }

    public ArrayList<String> getVariableByType(String type) {
        if (typeToVariables.containsKey(type))
            return typeToVariables.get(type);
        return new ArrayList<>();
    }

    public String getRandomVariableByType(String type) {
        if (!typeToVariables.containsKey(type))
            return null;
        ArrayList<String> variableList = typeToVariables.get(type);
        return variableList.get(RandomUtil.randomInt(0, variableList.size()));
    }

    public void addVariableByType(String type, String variable) {
        if (!typeToVariables.containsKey(type))
            typeToVariables.put(type, new ArrayList<>());
        ArrayList<String> list = typeToVariables.get(type);
        // 此处通过list来判断是否已经存在variable是一种很低效的方式
        // 但是可以较方便地随机获取一个该类型的变量
        if (!list.contains(variable))
            list.add(variable);
    }

}
