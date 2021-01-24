package expressionizedParameterHandling;

import interpretationErrorsException.InterpretationErrorException;

import java.util.List;

public interface ExpressionRearranger {
    public List<String> rearrange(List<String> tokens, Hierarchy order)throws InterpretationErrorException;
}
