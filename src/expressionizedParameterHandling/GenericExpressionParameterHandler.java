package expressionizedParameterHandling;

import client.ParsingInfo;
import client.VariableManager;
import interpretationErrorsException.AccessToUninitializedVariableException;
import interpretationErrorsException.InterpretationErrorException;
import interpretationErrorsException.MissingParameterException;
import interpretationErrorsException.UndefinedVariableException;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GenericExpressionParameterHandler /*implements ExpressionParameterHandler*/ {

    /*private Prearranger prearranger;
    private ExpressionRearranger expressionRearranger;
    private Hierarchy order;
    private ExpressionEvaluator expressionEvaluator;

    public GenericExpressionParameterHandler(Hierarchy order, Prearranger prearranger, ExpressionRearranger expressionRearranger, ExpressionEvaluator expressionEvaluator){
        this.prearranger = prearranger;
        this.expressionRearranger = expressionRearranger;
        this.order = order;
        this.expressionEvaluator = expressionEvaluator;
    }

    // receives a list of expression in infix form, returns it in postfix form and replaces unary minus operator like so: "-" "X" -> "1" "\f" "X"
    @Override
    public ParsingInfo reformatParameter(ParsingInfo parsingInfo) throws InterpretationErrorException {
        parsingInfo = expressionRearranger.rearrange(parsingInfo.getTokens(), order);
        List<String> rearrangedExpression = new ShuntingYardAlgorithm().rearrange(parsingInfo.getTokens(), hierarchy);
        return new ParsingInfo(rearrangedExpression, parsingInfo.getIndex());
    }

    @Override
    public Double calculateReformattedParameter(List<String> tokens, VariableManager symbolTable) throws UndefinedVariableException, AccessToUninitializedVariableException, MissingParameterException {
        return expressionEvaluator.evaluate(tokens, order, symbolTable);
    }*/
}
