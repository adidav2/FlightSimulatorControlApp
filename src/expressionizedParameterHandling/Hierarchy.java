package expressionizedParameterHandling;

import Expressions.Expression;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Hierarchy implements Comparator<String>{
    protected Map<String,Integer> priority;
    protected Map<String, Expression> stringOperatorToExpressionOperator;
    protected List<String> unaryOperators;
    protected String operatorListRegex;


    public abstract String getOperatorListRegex();

    public Hierarchy(HashMap<String, Integer> priority) {
        this.priority = priority;
    }

    @Override
    public int compare(String str1, String str2) {
        Integer tmp1=priority.get(str1);
        if(tmp1==null)
            tmp1=0;
        Integer tmp2=priority.get(str2);
        if(tmp2==null)
            tmp2=0;
        return tmp1-tmp2;
    }

    public Map<String,Integer> getPriority(){
        return priority;
    }

    public Boolean isOperator(String str)
    {
        return priority.containsKey(str);
    }
    public Boolean isUnaryOperator(String str)
    {
        return unaryOperators.contains(str);
    }
}
