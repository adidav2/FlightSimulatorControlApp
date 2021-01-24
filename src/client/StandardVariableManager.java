package client;

import interpretationErrorsException.AccessToUninitializedVariableException;
import interpretationErrorsException.UndefinedVariableException;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class StandardVariableManager implements VariableManager {
    private ConcurrentMap<String,VariableProperty> localSymbolTable;
    private ConcurrentMap<String,VariableProperty> foreignSymbolTable;
    private BlockingQueue<Property> sendToServer;
    private static StandardVariableManager helper;
    private StandardVariableManager(){
        localSymbolTable=new ConcurrentHashMap<>();
        foreignSymbolTable= new ConcurrentHashMap<>();
        sendToServer=new ArrayBlockingQueue<>(128);
    }
    public static StandardVariableManager getManager()
    {
        if(helper==null)
            helper=new StandardVariableManager();
        return helper;
    }

    @Override
    public Double getLocalVariable(String name) throws UndefinedVariableException, AccessToUninitializedVariableException {
        if(localSymbolTable.containsKey(name)) {
            Double value = localSymbolTable.get(name).getValue();
            if(value == null){
                throw new AccessToUninitializedVariableException(name);
            }
            return value;
        }
        throw new UndefinedVariableException(name);
    }

    @Override
    public void addLocalVariable(String name, Double value) {
        if(localSymbolTable.containsKey(name)) {
            try {
                setLocalVariable(name,value);
            } catch (UndefinedVariableException e) {
                //never going to happen...
            }
        }
        else localSymbolTable.put(name, new VariableProperty.VariableBuilder(value,name).build());
    }

    @Override
    public void setLocalVariable(String name, Double value) throws UndefinedVariableException{
       if(localSymbolTable.containsKey(name))
       {
           if(localSymbolTable.get(name).getValue() == null || !localSymbolTable.get(name).getValue().equals(value))
           {
               Property p =localSymbolTable.get(name);
               p.setValue(value);
               for (Property property : p.getBind()){
                   if (localSymbolTable.containsKey(property.getName()))
                       setLocalVariable(property.getName(), value);
                   else setForeignVariable(property.getName(), value);
               }
           }
       }
       else throw new UndefinedVariableException(name);
    }

    @Override
    public Double getForeignVariable(String name) throws UndefinedVariableException, AccessToUninitializedVariableException {
        if(foreignSymbolTable.containsKey(name)){
            Double value = foreignSymbolTable.get(name).getValue();
            if(value == null){
                throw new AccessToUninitializedVariableException(name);
            }
            return value;
        }
        throw new UndefinedVariableException(name);
    }

    @Override
    public void addForeignVariable(String name, Double value) {
        if(foreignSymbolTable.containsKey(name)) {
            try {
                setForeignVariable(name,value);
            } catch (UndefinedVariableException e) {
                //never going to happen...
            }
        }
        else foreignSymbolTable.put(name, new VariableProperty.VariableBuilder(value,name).build());
    }

    @Override
    public void setForeignVariable(String name, Double value) throws UndefinedVariableException{
        if(foreignSymbolTable.containsKey(name))
        {
            if(foreignSymbolTable.get(name).getValue() == null || !foreignSymbolTable.get(name).getValue().equals(value))
            {
                Property p =foreignSymbolTable.get(name);
                p.setValue(value);
                sendToServer.add(p);
                for (Property property : p.getBind()){
                    if (localSymbolTable.containsKey(property.getName()))
                        setLocalVariable(property.getName(), value);
                    else setForeignVariable(property.getName(), value);
                }
            }
        }
        else throw new UndefinedVariableException(name);
    }

    @Override
    public void bind(String name, String name2) throws UndefinedVariableException {
        if(!(localSymbolTable.containsKey(name) || foreignSymbolTable.containsKey(name)))
            throw new UndefinedVariableException(name);
        if(!(localSymbolTable.containsKey(name2) || foreignSymbolTable.containsKey(name2)))
        throw new UndefinedVariableException(name2);
        Property p1,p2;
        if(localSymbolTable.containsKey(name))
            p1=localSymbolTable.get(name);
        else p1=foreignSymbolTable.get(name);
        if(localSymbolTable.containsKey(name2))
            p2=localSymbolTable.get(name2);
        else p2=foreignSymbolTable.get(name2);
        p1.addBind(p2);
        p2.addBind(p1);
    }

    @Override
    public BlockingQueue<Property> getSendToServer() {
        return sendToServer;
    }

    @Override
    public Double get(String name) throws UndefinedVariableException, AccessToUninitializedVariableException {
        if(localSymbolTable.containsKey(name)) {
            Double value = localSymbolTable.get(name).getValue();
            if(value == null)
                throw new AccessToUninitializedVariableException(name);
            return value;
        }
        if(foreignSymbolTable.containsKey(name)) {
            Double value = foreignSymbolTable.get(name).getValue();
            if(value == null)
                throw new AccessToUninitializedVariableException(name);
            return value;
        }
        throw new UndefinedVariableException(name);
    }

    @Override
    public void set(String name, Double value) throws UndefinedVariableException {
        if(localSymbolTable.containsKey(name))
            setLocalVariable(name,value);
        if(foreignSymbolTable.containsKey(name))
            setForeignVariable(name,value);

    }
	@Override
	public void clear() {
		localSymbolTable.clear();
		foreignSymbolTable.clear();
		sendToServer.clear();
	}

}

