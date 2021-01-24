package client;

import interpretationErrorsException.AccessToUninitializedVariableException;
import interpretationErrorsException.UndefinedVariableException;

import java.util.concurrent.BlockingQueue;

public interface VariableManager {
    public Double getLocalVariable(String name) throws UndefinedVariableException, AccessToUninitializedVariableException;
    public void addLocalVariable(String name,Double value);
    public void setLocalVariable(String name,Double value)throws UndefinedVariableException;
    public Double getForeignVariable(String name) throws UndefinedVariableException, AccessToUninitializedVariableException;
    public void addForeignVariable(String name,Double value);
    public void setForeignVariable(String name,Double value)throws UndefinedVariableException;
    public void bind(String name,String name2)throws UndefinedVariableException;
    public BlockingQueue<Property> getSendToServer();
    public Double get(String name) throws UndefinedVariableException, AccessToUninitializedVariableException;
    public void set(String name,Double value)throws UndefinedVariableException;
    public void clear();


}
