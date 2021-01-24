package Expressions;

public class NumberExpression implements Expression {

    private Double number;

    public NumberExpression(Double number){ this.number = number; }

    @Override
    public Double calculate() {
        return number;
    }
}
