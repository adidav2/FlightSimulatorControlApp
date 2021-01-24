package commands;

import client.Parser;
import interpretationErrorsException.InvalidParameterException;

public class ReturnCommand extends CommandWithParameters {

	public ReturnCommand(Parser parser) {
		super(parser);
	}

	@Override
	public void execute() throws Exception {
		int valueToReturn = -1;
		try {
			valueToReturn = Integer.parseInt(parser.getParameter());
		}
		catch(NumberFormatException exception) {
			throw new InvalidParameterException("Invalid parameter for command return");
		}
		System.exit(valueToReturn);

	}

}
