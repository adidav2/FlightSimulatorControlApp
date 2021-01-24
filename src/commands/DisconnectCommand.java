package commands;

import client.Parser;
import client.VariableProperty;

public class DisconnectCommand extends CommandWithParameters {

	public DisconnectCommand(Parser parser) {
		super(parser);
	}

	@Override
	public void execute() throws Exception {
		parser.getVariableManager().getSendToServer().add(new VariableProperty.VariableBuilder(null, "bye").build());
	}

}
