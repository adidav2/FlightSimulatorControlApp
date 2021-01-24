package expressionizedParameterHandling;

import client.VariableManager;
import interpretationErrorsException.AccessToUninitializedVariableException;
import interpretationErrorsException.MissingParameterException;
import interpretationErrorsException.UndefinedVariableException;

import java.util.List;

public interface ExpressionEvaluator {

    public Double evaluate(List<String> expressionList, Hierarchy operatorContainer, VariableManager symbolTable) throws UndefinedVariableException, AccessToUninitializedVariableException, MissingParameterException;
}
