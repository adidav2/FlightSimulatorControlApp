package Expressions;

import expressionizedParameterHandling.Hierarchy;

import java.util.HashMap;
import java.util.Map;

public class BinaryExpressionFactory {

    private interface Creator{
        public Expression create(Expression left, Expression right);
    }

    Map<String, Creator> creatorMap;

    public BinaryExpressionFactory(){
        creatorMap = new HashMap<String, Creator>();

        creatorMap.put("AND", AndExpression::new);
        creatorMap.put("/", DivExpression::new);
        creatorMap.put("==", EqualExpression::new);
        creatorMap.put(">=", GreaterEqualExpression::new);
        creatorMap.put(">", GreaterExpression::new);
        creatorMap.put("<=", LesserEqualExpression::new);
        creatorMap.put("<", LesserExpression::new);
        creatorMap.put("-", MinusExpression::new);
        creatorMap.put("*", MulExpression::new);
        creatorMap.put("!=", NotEqualExpression::new);
        creatorMap.put("OR", OrExpression::new);
        creatorMap.put("+", PlusExpression::new);
        creatorMap.put("\f", UnaryMinusPlaceHolderExpression::new);
    }

    public Expression create(String identifier, Expression left, Expression right){
        Creator creator = creatorMap.get(identifier);
        if(creator != null)
            return creator.create(left, right);
        else
            return null;
    }
}