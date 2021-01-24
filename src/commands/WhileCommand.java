package commands;

import client.Parser;
import interpretationErrorsException.MissingParenthesesException;

import java.util.ArrayList;
import java.util.List;

public class WhileCommand extends CommandWithParameters {

    public WhileCommand(Parser parser) {
        super(parser);
    }

    @Override
    public void execute() throws Exception {
        List<String> expression = parser.getUncalculatedParameter();
        Parser childParser = parser.clone();

        while(!parser.calculateUncalculatedParameter(expression).equals(0d) && childParser.isAlive()) {
            parser.setRetVal(childParser.parse(getBlockToExecute()));
        }
        if(childParser.isReturnActivated()){
            parser.setReturnActivated(true);
            parser.close();
        }
        // TO MAKE RETURN COMMAND WORK FROM INSIDE OF A WHILE:
        // save parser.clone(), put new data members in StandardParser one is a Boolean representing the reason this parser returned (either because
        // the while finished or because it activated a return command) and the other is an int representing the return value. Then look at the
        // parser the WhileCommand got in its constructor, now change both of the Boolean and int in that parser to the same values you got from
        // the parser.clone(). Then return from this method. Now at the main loop of parser.parse() insert additional condition: && isAlive()
        // and the return value of the method parse() should be the value in the data member which represents the return value.

        // TO TERMINATE THE INTERPRETER GRACEFULLY:
        // StandardParser should have a new data member from the type Parser and called ChildParser. Parser should also have a new method called
        // terminate which first calls the close method (at the main loop of the method parse() add a condition:: && isalive()) then it calls
        // childParser.terminate(). the model from milestone 5 should be able to call terminate on the main Parser. note that the interpreter should
        // be ran from model in a new thread.

        parser.jumpToMatchingClosingBracket();
    }

    private List<String> getBlockToExecute()throws MissingParenthesesException
    {
        List<String> toExecute =new ArrayList<>();
        List<String> tokens=parser.getTokenList();
        int counter=0,index=parser.getCurrentPosition() + 1;
        if(!tokens.get(index).equals("{"))
            throw new MissingParenthesesException("{");
        while (index<tokens.size()){

            if (tokens.get(index).equals("{"))
                counter++;
            if (tokens.get(index).equals("}")) {
                counter--;
                if(counter==0)
                    break;
            }
            toExecute.add(tokens.get(index));
            index++;
        }
        if(index==tokens.size())
            throw new MissingParenthesesException("}");
        toExecute.remove(0);
        return toExecute;
    }
}
