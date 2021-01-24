package expressionizedParameterHandling;

import interpretationErrorsException.InterpretationErrorException;
import interpretationErrorsException.MissingParenthesesException;

import java.util.*;


public class ShuntingYardAlgorithm implements ExpressionRearranger{
    private Stack<String> stack;
    private Queue<String> queue;
    public ShuntingYardAlgorithm()
    {
        stack=new Stack<>();
        queue=new LinkedList<>();
    }

    @Override
    public List<String> rearrange(List<String> tokens, Hierarchy order) throws InterpretationErrorException {
        stack.clear();
        queue.clear();
        for (String str:tokens) {
            switch (str)
            {
                case "(":
                    stack.push(str);
                    break;
                case ")":
                    while(!stack.empty())
                    {
                        if(!stack.peek().equals("("))
                            queue.add(stack.pop());
                        else break;
                    }
                    if(stack.empty())
                        throw new MissingParenthesesException("(");
                    stack.pop();
                    break;
                default: // both for operators and numbers/variables
                    if(order.isOperator(str)) // for operators
                    {
                        while(!stack.empty())
                        {
                            if((order.compare(stack.peek(),str)>0) && !stack.peek().equals("(") && !stack.peek().equals(")"))
                                queue.add(stack.pop());
                            else break;
                        }
                        stack.push(str);
                    }
                    else // for numbers/variables
                    {
                        queue.add(str);
                    }
            }
        }
        while(!stack.isEmpty()){
            queue.add(stack.pop());
        }

        // if the stack has a "(" then a ")" is missing in the input
        for (String str : stack) {
            if(str.equals("(")) {
                throw new MissingParenthesesException(")");
            }
        }

        return new ArrayList<>(queue);
    }
}
