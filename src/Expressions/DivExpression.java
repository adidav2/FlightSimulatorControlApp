package Expressions;

public class DivExpression extends BinaryExpression {

    public DivExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Double calculate() {
        return left.calculate() / right.calculate();
    }
}
