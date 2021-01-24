package expressionizedParameterHandling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StandardHierarchy extends Hierarchy{

    public String getOperatorListRegex() {
        return operatorListRegex;
    }

    public StandardHierarchy() {
        super(null);
        HashMap<String,Integer>map= new HashMap<>();
        List<String> unaryList=new ArrayList<>();
        operatorListRegex = "OR|And|==|!=|>=|<=|[{}()<>=+\\-*/]";

        map.put("<", 1000);
        map.put(">", 1000);
        map.put("<=", 1000);
        map.put(">=", 1000);
        map.put("==", 1000);
        map.put("!=", 1000);
        map.put("AND", 999);
        map.put("OR", 998);

        map.put("-",1001);
        map.put("+",1001);
        map.put("*",1002);
        map.put("/",1002);

        map.put("\f",1003); // placeholder for unary minus

        map.put("(",1004);
        map.put(")",1004);


        unaryList.add("\f");

        super.priority=map;
        super.unaryOperators=unaryList;
    }
}
