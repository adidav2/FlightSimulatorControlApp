package Expressions;

public class NotEqualExpression extends BinaryExpression{
    public NotEqualExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Double calculate() {
        return (!left.calculate().equals(right.calculate())) ? 1d : 0;
    }

}
