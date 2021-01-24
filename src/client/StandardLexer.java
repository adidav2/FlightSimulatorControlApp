package client;

import expressionizedParameterHandling.Hierarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StandardLexer implements Lexer {

    private Hierarchy operatorCollection;

    public StandardLexer(Hierarchy operatorCollection){
        this.operatorCollection = operatorCollection;
    }

    @Override
    public List<String> lex(String str) {
        Scanner s=new Scanner(insertElementsIntoTokens(str));
        List<String> arr=new ArrayList<>();
        while(s.hasNext())
            arr.add(s.next());
        s.close();
        return arr;
    }

    public String insertElementsIntoTokens(String str){
        return str.replaceAll(operatorCollection.getOperatorListRegex(), " $0 ");
    }
}
