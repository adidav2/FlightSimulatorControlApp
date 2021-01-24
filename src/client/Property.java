package client;

import java.util.Set;

public interface Property {
    public void addBind(Property property);
    public void removeBind(Property property);
    public Set<Property> getBind();
    public Double getValue();
    public void setValue(Double d);
    public void setName(String name);
    public String getName();

}
