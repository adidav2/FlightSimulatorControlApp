package Expressions;

public class MulExpression extends BinaryExpression {

    public MulExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Double calculate() {
        return left.calculate() * right.calculate();
    }
}
