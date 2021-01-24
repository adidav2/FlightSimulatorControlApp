package Expressions;

public class MinusExpression extends BinaryExpression {

    public MinusExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Double calculate() {
        return left.calculate() - right.calculate();
    }
}
