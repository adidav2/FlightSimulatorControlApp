package interpretationErrorsException;

public class MissingParenthesesException extends SyntaxErrorException{
    public MissingParenthesesException(String message) {
        super("Missing Parentheses: "+message);
    }
}
