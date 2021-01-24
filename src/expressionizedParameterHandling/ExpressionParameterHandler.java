package expressionizedParameterHandling;

import client.ParsingInfo;
import client.VariableManager;
import interpretationErrorsException.AccessToUninitializedVariableException;
import interpretationErrorsException.InterpretationErrorException;
import interpretationErrorsException.MissingParameterException;
import interpretationErrorsException.UndefinedVariableException;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface ExpressionParameterHandler {

    public ParsingInfo reformatParameter(ParsingInfo parsingInfo) throws InterpretationErrorException;
    public Double calculateReformattedParameter(List<String> tokens, VariableManager symbolTable) throws UndefinedVariableException, AccessToUninitializedVariableException, MissingParameterException;
}
