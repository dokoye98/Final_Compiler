package project.compiler.semantic;

import project.compiler.nodes.*;

import java.util.HashSet;
import java.util.Set;



public class SemanticTree {

    private static final Set<String> definedVariables = new HashSet<>();

    public static void analyzeSemantic(Node ast) throws Exception {
        System.out.println("Analyzing semantics for AST: " + ast.getClass().getSimpleName());
        if (ast instanceof BinaryAssignNode) {
            BinaryAssignNode binaryAssignNode = (BinaryAssignNode) ast;
            VariableAssignmentNode variableOne = binaryAssignNode.getVariableOne();
            VariableAssignmentNode variableTwo = binaryAssignNode.getVariableTwo();
            System.out.println("BinaryAssignNode: " + variableOne + ", " + variableTwo);
            analyzeSemantic(variableOne);
            analyzeSemantic(variableTwo);
        } else if (ast instanceof VariableAssignmentNode) {
            VariableAssignmentNode variableAssignmentNode = (VariableAssignmentNode) ast;
            String variableName = variableAssignmentNode.getVariableName();
            ExpressionNode expression = variableAssignmentNode.getExpression();
            System.out.println("VariableAssignmentNode: " + variableName + ", " + expression);
            checkExpression(expression);
        } else if (ast instanceof PrintStatementNode) {
            PrintStatementNode printStatementNode = (PrintStatementNode) ast;
            ExpressionNode expression = printStatementNode.getExpression();
            System.out.println("PrintStatementNode: " + expression);
            checkExpression(expression);
        } else if (ast instanceof BinaryOperationNode) {
            BinaryOperationNode binaryOperationNode = (BinaryOperationNode) ast;
            ExpressionNode left = binaryOperationNode.getLeft();
            ExpressionNode right = binaryOperationNode.getRight();
            checkExpression(left);
            checkExpression(right);
            System.out.println("BinaryOperationNode: " + left + ", " + right);
            if (!(left instanceof LiteralNode && right instanceof LiteralNode)) {
                throw new Exception("Must be Literal Nodes");
            }
        } else if (ast instanceof BinaryAssignmentNode) {
            BinaryAssignmentNode binaryAssignmentNode = (BinaryAssignmentNode) ast;
            String varName = binaryAssignmentNode.getVariableName();
            ExpressionNode value1 = binaryAssignmentNode.getValue();
            ExpressionNode value2 = binaryAssignmentNode.getSecondValue();
            VariableAssignmentNode variableAssignmentNode = new VariableAssignmentNode(varName, value1);
            System.out.println("BinaryAssignmentNode: " + value1 + ", " + value2);
            checkExpression(value1);
            checkExpression(value2);
            analyzeSemantic(variableAssignmentNode);

            if (!(value1 instanceof LiteralNode && value2 instanceof LiteralNode)) {
                throw new Exception("Must be Literal Nodes");
            }

        } else if (ast instanceof PrintVariableNode) {
            PrintVariableNode printVariableNode = (PrintVariableNode) ast;
            System.out.println("PrintVariableNode: " + printVariableNode);
            //no additional checks as Print only takes String
        } else if (ast instanceof LiteralNode) {
            //no additional checks as Literal Node - check expression not needed
            LiteralNode literalNode = (LiteralNode) ast;
            System.out.println("LiteralNode: " + literalNode);

        } else if(ast instanceof BinaryVarExtended){
            BinaryVarExtended binaryVarExtended = (BinaryVarExtended) ast;
            ExpressionNode value = binaryVarExtended.getExpression();
            ExpressionNode value2 = binaryVarExtended.getValue2();

            System.out.println("BinaryVarExtended: "+ binaryVarExtended);
            checkExpression(value);
            checkExpression(value2);

        }else if (ast instanceof UnknownNode) {
            UnknownNode unknownNode = (UnknownNode) ast;
            System.out.println("UnknownNode: " + unknownNode.getErrorMessage());

        } else {
            System.out.println("Unrecognised AST Node: " + ast.getClass().getSimpleName());//like getClass
            throw new Exception("Unrecognised AST Node");
        }
    }

    private static void checkExpression(ExpressionNode expression) throws Exception {
        System.out.println("Checking expression: " + expression);
        if (expression instanceof LiteralNode) {
            System.out.println("Expression is a LiteralNode");
            // nothing needed as literal doesn't need process
        } else if (expression instanceof VariableNode) {
            String variableName = ((VariableNode) expression).getName();
            System.out.println("Expression is a VariableNode: " + variableName);
            if (!definedVariables.contains(variableName)) {
                throw new Exception("Undefined variable: " + variableName);
            }
        } else {
            System.out.println("Unrecognised expression: " + expression);
            throw new Exception("Unrecognised expression");
        }
    }
}
