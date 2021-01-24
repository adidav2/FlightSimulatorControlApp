package Expressions;

public class AndExpression extends BinaryExpression {

    AndExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Double calculate() {
        if(left.calculate() != 0 && right.calculate() != 0)
            return 1d;
        else
            return 0d;
    }
}
