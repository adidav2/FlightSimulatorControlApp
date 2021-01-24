package interpretationErrorsException;

public class MissingParameterException extends SyntaxErrorException{
    public MissingParameterException() {
        super("Missing Parameter");
    }
}
