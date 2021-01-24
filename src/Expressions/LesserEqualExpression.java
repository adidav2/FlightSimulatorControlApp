package Expressions;

public class LesserEqualExpression extends BinaryExpression {

    public LesserEqualExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Double calculate() {
        return (left.calculate() <= right.calculate())? 1d : 0;
    }
}
