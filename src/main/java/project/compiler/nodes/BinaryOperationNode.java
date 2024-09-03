package project.compiler.nodes;

public class BinaryOperationNode extends ExpressionNode{


    private final ExpressionNode left;

    public String getOperator() {
        return operator;
    }

    private final String operator;

    public ExpressionNode getLeft() {
        return left;
    }

    public ExpressionNode getRight() {
        return right;
    }

    private final ExpressionNode right;

    public BinaryOperationNode(ExpressionNode left, String operator, ExpressionNode right) {

        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public String getType() {
        return "Integer";
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"(" + getOperator() + "): "
                + getType() + "\n  |\n  --- " + getLeft() + "\n  --- " + getRight();
    }
}
