package Expressions;

public class LesserExpression extends BinaryExpression {

    public LesserExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Double calculate() {
        return (left.calculate()<right.calculate())? 1d : 0;
    }
}
