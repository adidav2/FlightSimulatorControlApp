package Expressions;

public class EqualExpression extends BinaryExpression {
    public EqualExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Double calculate() {
         return (left.calculate().equals(right.calculate()))? 1d : 0;
    }
}
