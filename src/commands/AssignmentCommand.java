package commands;

import client.Parser;
import interpretationErrorsException.InvalidParameterException;

import java.util.List;

public class AssignmentCommand extends CommandWithParameters {
    public AssignmentCommand(Parser parser) {
        super(parser);
    }

    @Override
    public void execute() throws Exception {
        String left;
        List<String> right;
        try {
            left = parser.getTokenList().get(parser.getCurrentPosition() - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidParameterException("Missing Recipient for =");
        }
        right=parser.getUncalculatedParameter();
       if(right.get(0).equals("bind"))
       {
           String s=parser.getStringLiteral();
           parser.getVariableManager().bind(left,s);
           parser.getVariableManager().set(left,parser.getVariableManager().get(s));
       }
       else
       {
           Double value=parser.calculateUncalculatedParameter(right);
           parser.getVariableManager().set(left,value);
       }
    }
}
