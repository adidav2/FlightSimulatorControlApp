package client;



import expressionizedParameterHandling.*;
import interpretationErrorsException.InterpretationErrorException;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class main {
    /*"(5555 +6)*7/ (9723-2 ) + 4086"*/
    public static void main(String[] args) throws InterpretationErrorException, IOException {
        ExpressionParameterHandler expressionHandler = new PostfixExpressionParameterHandler();
        Lexer lex = new StandardLexer(new StandardHierarchy());
        //new ArithmeticPrearranger(lex.lex(toPrint), 0, new ArithmeticHierarchy()).rearrange();
        //List<String> tmp = calc(toPrint);

        VariableManager symbolTable = StandardVariableManager.getManager();
        //symbolTable.addLocalVariable("X", 2.0);
        //symbolTable.addLocalVariable("pakuki", 5.0);
        //symbolTable.addLocalVariable("hello", 10.0);
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String toPrint2 = "var x = 0 var y = 0 while x<123456 { x=x+1 while y<123456 { print y y=y+1 return y+18} } print x";
        //String toPrint = "  print X X=X+1 print X ";
        Parser parser = new StandardParser.StandardParserBuilder(expressionHandler,symbolTable,null).build();
        System.out.println("before: " + toPrint2);
        int xxx;
        try {
             xxx = parser.parse(lex.lex(toPrint2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(parser.getParameter());
        /*List<String> tmp = test.rearrange(lex.lex(toPrint), new StandardHierarchy());
        System.out.println("after: ");
        for (String s : tmp) {
            System.out.print(s + " ");
        }*/
    }
    public static void func(int[] i)
    {
        i[0]++;
    }
}
