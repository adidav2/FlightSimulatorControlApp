package Expressions;

public class UnaryMinusPlaceHolderExpression extends BinaryExpression {

    public UnaryMinusPlaceHolderExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Double calculate() {
        return -(left.calculate() * right.calculate());
    }
}
