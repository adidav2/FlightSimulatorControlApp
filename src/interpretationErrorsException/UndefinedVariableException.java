package interpretationErrorsException;

public class UndefinedVariableException extends SyntaxErrorException{
    public UndefinedVariableException(String message) {
        super("Undefined Variable: \""+message+"\"");
    }
}
