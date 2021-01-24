package Expressions;

public class GreaterEqualExpression extends BinaryExpression {
    public GreaterEqualExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Double calculate() {
        return (left.calculate()>=right.calculate())? 1d : 0;
    }
}
