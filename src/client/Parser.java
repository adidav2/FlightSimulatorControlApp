package client;

import commands.Command;
import interpretationErrorsException.InterpretationErrorException;
import interpretationErrorsException.MissingParenthesesException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public interface Parser  {
    public int parse(List<String> tokens)throws Exception;
    public String getParameter()throws InterpretationErrorException;
    public String getStringLiteral()throws InterpretationErrorException;
    public VariableManager getVariableManager();
    public Boolean isAlive();
    public void close();
    public void printErrorAndExit(String str);
    public List<String> getUncalculatedParameter() throws InterpretationErrorException;
    public Double calculateUncalculatedParameter(List<String> uncalculatedParameter) throws InterpretationErrorException;
    public Parser clone();
    public List<String> getTokenList();
    public int getCurrentPosition();
    public void jumpToMatchingClosingBracket() throws MissingParenthesesException;
    public void closeParent();
    public int getRetVal();
    public void setRetVal(int val);
    public boolean isReturnActivated();
    public void setReturnActivated(boolean val);
}
