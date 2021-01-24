package commands;

import client.Parser;

public abstract class CommandWithParameters implements Command {
    protected Parser parser;
    public CommandWithParameters(Parser parser) {
        this.parser = parser;
    }
}
