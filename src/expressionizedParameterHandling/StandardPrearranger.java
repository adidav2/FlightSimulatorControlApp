package expressionizedParameterHandling;

import client.ParsingInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StandardPrearranger extends Prearranger {

    public StandardPrearranger() {

    }


    // returns a list with exactly the tokens needed for the next parameter, and each unary minus replaced with a placeholder
    @Override
    public ParsingInfo prearrange(ParsingInfo parsingInfo, Hierarchy hierarchy) {
        this.parsingInfo = parsingInfo;
        this.hierarchy = hierarchy;

        return replaceUnaryMinusWithTemporaryToken(getLimits());
    }

    // finds the first and last tokens by comparing each token and the following token
    private ParsingInfo getLimits() {

       List<String> expressionList=new ArrayList<>();
       String currentToken = null;
       String nextToken = null;
       int index;

        List<String> ls = parsingInfo.getTokens();
       for(index = parsingInfo.getIndex(); index + 1 < parsingInfo.getTokens().size(); index++) {

           currentToken = parsingInfo.getTokens().get(index);
           nextToken = parsingInfo.getTokens().get(index + 1);
           expressionList.add(currentToken);

           if (currentToken.equals(")")) {
               if (nextToken.equals("(") || !hierarchy.isOperator(nextToken))
                   break;
           }
           else if(!hierarchy.isOperator(currentToken)){ // statement is true for numbers and variables
                if(!hierarchy.isOperator(nextToken) || nextToken.equals("("))
                    break;
           }
       }
        if(index + 1 == parsingInfo.getTokens().size()) // the for loop stops before last index without adding it to the list
            expressionList.add(parsingInfo.getTokens().get(index));

       //this.index[0]+=expressionList.size()-1;// this.index is used to return by reference the index from which parsing should continue
       return new ParsingInfo(expressionList, parsingInfo.getIndex()+expressionList.size()-1);
    }



    private ParsingInfo replaceUnaryMinusWithTemporaryToken(ParsingInfo parsingInfo){

        List<String> newExpressionList = new ArrayList<>();
        String currentToken = null;
        String formerToken = null;

        for(int index = 0; index < parsingInfo.getTokens().size(); index++){

            currentToken = parsingInfo.getTokens().get(index);

            if(index == 0) {
                if(currentToken.equals("-")) {
                    newExpressionList.add("1");
                    newExpressionList.add("\f");
                }
                else{
                    newExpressionList.add(parsingInfo.getTokens().get(index));
                }
            }
            else {
                formerToken = parsingInfo.getTokens().get(index - 1);

                // if currentToken is an unary minus
                if(currentToken.equals("-") && hierarchy.isOperator(formerToken) && !formerToken.equals(")")){
                    newExpressionList.add("1");
                    newExpressionList.add("\f");
                }
                else{
                    newExpressionList.add(parsingInfo.getTokens().get(index));
                }
            }
        }

        return new ParsingInfo(newExpressionList, parsingInfo.getIndex());
    }
}