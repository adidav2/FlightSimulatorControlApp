package interpretationErrorsException;

public abstract class SyntaxErrorException extends InterpretationErrorException{
    public SyntaxErrorException(String message) {
        super(message);
    }
}
