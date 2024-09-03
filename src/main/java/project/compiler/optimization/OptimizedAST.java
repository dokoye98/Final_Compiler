package project.compiler.optimization;

import project.compiler.nodes.*;
import project.compiler.nodes.binarynodes.AdditionNode;
import project.compiler.nodes.binarynodes.DivideNode;
import project.compiler.nodes.binarynodes.MultiplicationNode;
import project.compiler.nodes.binarynodes.SubtractionNode;

public class OptimizedAST {


    public static Node optimizeBinary(Node ast) {
        if (ast instanceof BinaryOperationNode binaryOpNode) {
            ExpressionNode left = binaryOpNode.getLeft();
            ExpressionNode right = binaryOpNode.getRight();
            //System.out.println("left and right work");
            if (left instanceof LiteralNode && right instanceof LiteralNode) {
                //System.out.println("check works");
                int leftValue = Integer.parseInt(((LiteralNode) left).getValue());
                int rightValue = Integer.parseInt(((LiteralNode) right).getValue());
                //System.out.println("conversion works");
                switch (binaryOpNode) {
                    case AdditionNode additionNode -> {
                        // System.out.println("Addition check works");
                        return new LiteralNode(String.valueOf(leftValue + rightValue));
                    }
                    case SubtractionNode subtractionNode -> {
                        return new LiteralNode(String.valueOf(leftValue - rightValue));
                    }
                    case MultiplicationNode multiplicationNode -> {
                        // System.out.println("Multi check");
                        return new LiteralNode(String.valueOf(leftValue * rightValue));
                    }
                    case DivideNode divideNode -> {
                        //   System.out.println("div check");
                        return new LiteralNode(String.valueOf(leftValue / rightValue));
                    }
                    default -> {
                        return new UnknownNode("Unknown operator");
                    }
                }
            }

        }
        return ast;
    }
}
