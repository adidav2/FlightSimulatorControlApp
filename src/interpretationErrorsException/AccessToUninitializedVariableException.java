package interpretationErrorsException;

public class AccessToUninitializedVariableException extends InterpretationErrorException {
    public AccessToUninitializedVariableException(String message) {
        super("Access to uninitialized variable: "+ message);
    }
}
