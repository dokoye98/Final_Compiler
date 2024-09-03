package project.compiler.nodes.binarynodes;

import project.compiler.nodes.BinaryOperationNode;
import project.compiler.nodes.ExpressionNode;

public class AdditionNode extends BinaryOperationNode {


    public AdditionNode(ExpressionNode left, String operator, ExpressionNode right) {
        super(left, operator, right);
    }


}
