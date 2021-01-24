package client;
import commands.Command;
import expressionizedParameterHandling.ExpressionParameterHandler;
import interpretationErrorsException.InterpretationErrorException;
import interpretationErrorsException.InvalidParameterException;
import interpretationErrorsException.MissingParameterException;
import interpretationErrorsException.MissingParenthesesException;
import interpretationErrorsException.UnkownKeywordException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class StandardParser implements Parser {
    private Map<String, Command> commandTable;
    private VariableManager variableManager;
    private ExpressionParameterHandler expressionParameterHandler;
    private int index;
    private List<String> tokens;
    private volatile Boolean alive;
    private Keywords keys;
    private Parser child;
    private Parser parent;
    private int retVal;
    private boolean returnActivated;


    private StandardParser(VariableManager variableManager, ExpressionParameterHandler expressionParameterHandler, List<String> tokens, Keywords keys) {
        if (variableManager == null) {
            this.variableManager = StandardVariableManager.getManager();
        } else {
            this.variableManager = variableManager;
        }
        this.expressionParameterHandler = expressionParameterHandler;
        this.tokens = tokens;
        index = 0;
        alive = true;
        this.keys=keys;
        retVal = 0;
        returnActivated = false;
    }


    public static class StandardParserBuilder {
        private Keywords tmpKeys;
        private ExpressionParameterHandler expressionParameterHandler;
        private VariableManager variableManager;
        private List<String> tempTokens;

        public StandardParserBuilder(ExpressionParameterHandler expressionParameterHandler, VariableManager variableManager,Keywords keys) {
            this.expressionParameterHandler = expressionParameterHandler;
            this.variableManager = variableManager;
            this.tmpKeys=keys;
        }

        public StandardParserBuilder setTokens(List<String> tokens) {
            tempTokens = tokens;
            return this;
        }
        public StandardParser build() {
            StandardParser parser = new StandardParser(variableManager, expressionParameterHandler, tempTokens,tmpKeys);
            if (tmpKeys == null)
                parser.commandTable = new DefualtKeywords().generate(parser);
            else
                parser.commandTable = tmpKeys.generate(parser);

            return parser;
        }
    }


    @Override
    public int parse(List<String> tokenList) throws Exception {
        tokens = tokenList;
        index = 0;
        while (index < tokens.size() && isAlive()) {
            String current = tokens.get(index);
            Command c = commandTable.get(current);
            if(current.equals("return")) {
                returnActivated = true;
            	int valueToReturn = -1;
        		try {
        			valueToReturn = (int)Double.parseDouble(getParameter());
        		}
        		catch(NumberFormatException exception) {
        			throw new InvalidParameterException("Invalid parameter for command return");
        		}
        		this.close();
        		Thread.sleep(150);
        		return valueToReturn;
            }
            if (c == null)
            {
                String str=null;
                try{
                     str=getStringLiteral();
                }catch (MissingParameterException e)
                {
                    printErrorAndExit(new UnkownKeywordException(current).getMessage());
                }
                if(str.equals("="))
                    continue;
                 printErrorAndExit(new UnkownKeywordException(current).getMessage());
            }
            try{
                c.execute();
            }catch (Exception e){
            	e.printStackTrace();
                printErrorAndExit(e.getMessage());
            }
            index++;
            Thread.sleep(1);
        }
        
        return retVal; // default return value is 0
    }


    @Override
    public String getParameter() throws InterpretationErrorException {
        index++;
        ParsingInfo parsingInfo = expressionParameterHandler.reformatParameter(new ParsingInfo(tokens, index));
        index = parsingInfo.getIndex();
        Double result = expressionParameterHandler.calculateReformattedParameter(parsingInfo.getTokens(), variableManager);
        return result.toString();
    }

    @Override
    public String getStringLiteral() throws InterpretationErrorException {
       if(tokens.size()-1<=index)
           throw new MissingParameterException();
       return tokens.get(++index);
    }

    @Override
    public VariableManager getVariableManager() {
        return this.variableManager;
    }

    @Override
    public Boolean isAlive() {
        return alive;
    }

    @Override
    public void close() {
        alive = false;
        if(child != null) {
            child.close();
        }
    }

    @Override
    public void printErrorAndExit(String str) {
        System.out.println(str);
        System.exit(-1);
    }

    @Override
    public List<String> getUncalculatedParameter() throws InterpretationErrorException {
        index++;
        ParsingInfo parsingInfo = expressionParameterHandler.reformatParameter(new ParsingInfo(tokens, index));
        index = parsingInfo.getIndex();
        if(parsingInfo.getTokens().size()==0)
            throw new MissingParameterException();
        return parsingInfo.getTokens();
    }

    @Override
    public Double calculateUncalculatedParameter(List<String> uncalculatedParameter) throws InterpretationErrorException {
        return expressionParameterHandler.calculateReformattedParameter(uncalculatedParameter, variableManager);
    }
    @Override
    public Parser clone() {
        StandardParser p = new StandardParserBuilder(expressionParameterHandler, variableManager,keys).build();
        this.child = p;
        p.parent = this;

        return p;
    }

    @Override
    public List<String> getTokenList() {
        return tokens;
    }

    @Override
    public int getCurrentPosition() {
        return index;
    }

    @Override
    public void jumpToMatchingClosingBracket() throws MissingParenthesesException {
        int jump = 0;
        int counter = 0;
        int index = this.index + 1;
        if (!tokens.get(index).equals("{"))
            return;
        while (index < tokens.size()) {
            if (tokens.get(index).equals("{"))
                counter++;
            if (tokens.get(index).equals("}"))
                counter--;
            jump++;
            if (counter == 0)
                break;
            index++;
        }
        if (index == tokens.size())
            throw new MissingParenthesesException("}");
        this.index+=jump;
    }

    @Override
    public void closeParent() {
        this.close();
        if(parent != null) {parent.closeParent();}
    }

    @Override
    public int getRetVal() {
        return this.retVal;
    }

    @Override
    public void setRetVal(int val) {
        this.retVal = val;
    }

    public boolean isReturnActivated(){
        return this.returnActivated;
    }

    public void setReturnActivated(boolean val) {
        this.returnActivated = val;
    }
}
