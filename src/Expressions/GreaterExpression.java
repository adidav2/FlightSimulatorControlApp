package Expressions;

public class GreaterExpression extends BinaryExpression {
    public GreaterExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Double calculate() {
        return (left.calculate()>right.calculate())? 1d : 0;
    }
}
