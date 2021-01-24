package commands;

import client.Parser;

public class VarCommand extends CommandWithParameters{


    public VarCommand(Parser parser) {
        super(parser);
    }

    @Override
    public void execute() throws Exception {
        parser.getVariableManager().addLocalVariable(parser.getStringLiteral(), null);
    }
}
