package client;

import expressionizedParameterHandling.ExpressionParameterHandler;
import expressionizedParameterHandling.PostfixExpressionParameterHandler;
import expressionizedParameterHandling.StandardHierarchy;

public class Interpreter {

    private Parser parser;

    public int interpret(String[] lines){
        ExpressionParameterHandler expressionHandler = new PostfixExpressionParameterHandler();
        VariableManager variableManager = StandardVariableManager.getManager();
        variableManager.clear();
        Parser parser = new StandardParser.StandardParserBuilder(expressionHandler,variableManager,null).build();
        Lexer lexer = new StandardLexer(new StandardHierarchy());

        StringBuilder strBuilder = new StringBuilder();
        for(String str : lines) {
            strBuilder.append(str);
            strBuilder.append(" ");
        }
        int retVal = -1;
        try {
            retVal = parser.parse(lexer.lex(strBuilder.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception got to interpret function");
        }

        return retVal;
    }



    public void terminate(){
        if(parser != null) { parser.close(); }
    }
}
