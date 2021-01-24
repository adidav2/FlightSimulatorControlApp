package client;

import java.util.HashSet;
import java.util.Set;

public class VariableProperty implements Property{
    private Double value;
    private Set<Property> bindSet;
    private String name;
    private VariableProperty(Double d,Set<Property> bindSet, String name)
    {
        value=d;
        if(bindSet==null)
             this.bindSet=new HashSet<>();
        else this.bindSet=bindSet;
        this.name=name;
    }

    @Override
    public void addBind(Property property) {
        bindSet.add(property);
    }

    @Override
    public void removeBind(Property property) {
        bindSet.remove(property);
    }

    @Override
    public Set<Property> getBind() {
        return bindSet;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Double d) {
            value=d;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public String getName() {
        return name;
    }

    public static class VariableBuilder{
        private  Double tmpValue;
        private  Set<Property> tmpBind;
        private String tmpName;
        public VariableBuilder (Double value,String name)
        {
            tmpValue=value;
            tmpName=name;
        }
        public  VariableBuilder setBind(Set<Property> bindSet)
        {
            this.tmpBind=bindSet;
            return this;
        }
        public VariableProperty build()
        {
            return new VariableProperty(tmpValue,tmpBind,tmpName);
        }
    }
}
