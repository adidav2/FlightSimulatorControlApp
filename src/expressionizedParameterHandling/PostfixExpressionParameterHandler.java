package expressionizedParameterHandling;

import client.ParsingInfo;
import client.VariableManager;
import interpretationErrorsException.AccessToUninitializedVariableException;
import interpretationErrorsException.InterpretationErrorException;
import interpretationErrorsException.MissingParameterException;
import interpretationErrorsException.UndefinedVariableException;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PostfixExpressionParameterHandler implements ExpressionParameterHandler {

    private Hierarchy hierarchy;

    public PostfixExpressionParameterHandler() {
        hierarchy = new StandardHierarchy();
    }

    // receives a list of expression in infix form, returns it in postfix form and replaces unary minus operator like so: "-" "X" -> "1" "\f" "X"
    @Override
    public ParsingInfo reformatParameter(ParsingInfo parsingInfo) throws InterpretationErrorException {
        parsingInfo = new StandardPrearranger().prearrange(parsingInfo, hierarchy);
        List<String> rearrangedExpression = new ShuntingYardAlgorithm().rearrange(parsingInfo.getTokens(), hierarchy);
        return new ParsingInfo(rearrangedExpression, parsingInfo.getIndex());
    }

    @Override
    public Double calculateReformattedParameter(List<String> tokens, VariableManager symbolTable) throws UndefinedVariableException, AccessToUninitializedVariableException, MissingParameterException {
        return new StandardPostfixExpressionEvaluator().evaluate(tokens, hierarchy, symbolTable);
    }
}
