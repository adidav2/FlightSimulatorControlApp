package expressionizedParameterHandling;

import Expressions.BinaryExpressionFactory;
import Expressions.Expression;
import Expressions.NumberExpression;
import client.VariableManager;
import interpretationErrorsException.AccessToUninitializedVariableException;
import interpretationErrorsException.MissingParameterException;
import interpretationErrorsException.UndefinedVariableException;

import java.security.InvalidParameterException;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class StandardPostfixExpressionEvaluator implements ExpressionEvaluator {

    @Override
    public Double evaluate(List<String> expressionList, Hierarchy operatorContainer, VariableManager symbolTable) throws UndefinedVariableException, AccessToUninitializedVariableException, MissingParameterException {
        if(expressionList.size()==0)
           throw new MissingParameterException();
        BinaryExpressionFactory operatorFactory = new BinaryExpressionFactory();
        Expression expression;
        Expression left, right;
        Stack<Expression> expressionStack = new Stack<>();


        for(String str : expressionList) {
            if(!operatorContainer.isOperator(str)){
                expressionStack.push(new NumberExpression(this.calculate(str, symbolTable)));
            }
            else{

                try {
                    right = expressionStack.pop();
                    left = expressionStack.pop();
                } catch(EmptyStackException exception){
                    throw new InvalidParameterException("Missing a parameter for operator the " + "\"" + str + "\"");
                }

                expressionStack.push(operatorFactory.create(str, left, right));
            }
        }
        return expressionStack.pop().calculate();
    }

    private Double calculate(String str, VariableManager symbolTable) throws UndefinedVariableException, AccessToUninitializedVariableException {
        try{
            return Double.parseDouble(str);
        }
        catch(NumberFormatException exception){
            return symbolTable.get(str);
        }
    }
}
