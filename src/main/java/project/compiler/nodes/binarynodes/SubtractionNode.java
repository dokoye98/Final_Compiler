package project.compiler.nodes.binarynodes;

import project.compiler.nodes.BinaryOperationNode;
import project.compiler.nodes.ExpressionNode;

public class SubtractionNode extends BinaryOperationNode {


    public SubtractionNode(ExpressionNode left, String operator, ExpressionNode right) {
        super(left, operator, right);
    }

    @Override
    public String getType() {
        return "Integer";
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"(" + getOperator() + "): " + getType() + "\n  |\n  --- " + getLeft() + "\n  --- " + getRight();
    }
}
