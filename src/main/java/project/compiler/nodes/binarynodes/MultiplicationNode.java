package project.compiler.nodes.binarynodes;

import project.compiler.nodes.BinaryOperationNode;
import project.compiler.nodes.ExpressionNode;

public class MultiplicationNode extends BinaryOperationNode {


    public MultiplicationNode(ExpressionNode left, String operator, ExpressionNode right) {
        super(left, operator, right);
    }


}
