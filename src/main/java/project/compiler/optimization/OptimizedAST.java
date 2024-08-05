package project.compiler.optimization;

import project.compiler.nodes.*;
import project.compiler.nodes.binarynodes.AdditionNode;
import project.compiler.nodes.binarynodes.DivideNode;
import project.compiler.nodes.binarynodes.MultiplicationNode;
import project.compiler.nodes.binarynodes.SubtractionNode;

public class OptimizedAST {


    public static Node optimize(Node ast) {
        if (ast instanceof BinaryOperationNode) {
            BinaryOperationNode binaryOpNode = (BinaryOperationNode) ast;

            ExpressionNode left = binaryOpNode.getLeft();
            ExpressionNode right = binaryOpNode.getRight();
            //System.out.println("left and right work");
            if (left instanceof LiteralNode && right instanceof LiteralNode) {
                //System.out.println("check works");
                int leftValue = Integer.parseInt(((LiteralNode) left).getValue());
                int rightValue = Integer.parseInt(((LiteralNode) right).getValue());
                //System.out.println("conversion works");
                if (binaryOpNode instanceof AdditionNode) {
                   // System.out.println("Addition check works");
                    return new LiteralNode(String.valueOf(leftValue + rightValue));
                } else if (binaryOpNode instanceof SubtractionNode) {
                    return new LiteralNode(String.valueOf(leftValue - rightValue));
                }else if(binaryOpNode instanceof MultiplicationNode){
                   // System.out.println("Multicheck");
                    return new LiteralNode(String.valueOf(leftValue * rightValue));
                }else if(binaryOpNode instanceof DivideNode){
                 //   System.out.println("div check");
                    return new LiteralNode(String.valueOf(leftValue / rightValue));
                }
            }

        }
        return ast;
    }
}
