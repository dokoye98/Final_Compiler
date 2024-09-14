package project.compiler.semantic;

import project.compiler.nodes.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class SemanticTree {

    private static final Set<String> definedVariables = new HashSet<>();

    public static void analyzeSemantic(Node ast) throws Exception {
        System.out.println("Analyzing semantics for AST: " + ast.getClass().getSimpleName());
        switch (ast) {
            case BinaryAssignNode binaryAssignNode -> {
                VariableAssignmentNode variableOne = binaryAssignNode.getVariableOne();
                VariableAssignmentNode variableTwo = binaryAssignNode.getVariableTwo();
                System.out.println("BinaryAssignNode: " + variableOne + ", " + variableTwo);
                analyzeSemantic(variableOne);
                analyzeSemantic(variableTwo);
            }
            case VariableAssignmentNode variableAssignmentNode -> {
                String variableName = variableAssignmentNode.getVariableName();
                ExpressionNode expression = variableAssignmentNode.getExpression();
                System.out.println("VariableAssignmentNode: " + variableName + ", " + expression);
                checkExpression(expression);
            }
            case PrintStatementNode printStatementNode -> {
                ExpressionNode expression = printStatementNode.getExpression();
                System.out.println("PrintStatementNode: " + expression);
                checkExpression(expression);
            }
            case BinaryOperationNode binaryOperationNode -> {
                ExpressionNode left = binaryOperationNode.getLeft();
                ExpressionNode right = binaryOperationNode.getRight();
                checkExpression(left);
                checkExpression(right);
                System.out.println("BinaryOperationNode: " + left + ", " + right);
                if (!(left instanceof LiteralNode && right instanceof LiteralNode)) {
                    throw new Exception("Must be Literal Nodes");
                }
            }
            case BinaryAssignmentNode binaryAssignmentNode -> {
                String varName = binaryAssignmentNode.getVariableName();
                ExpressionNode value1 = binaryAssignmentNode.getValue();
                ExpressionNode value2 = binaryAssignmentNode.getSecondValue();
                VariableAssignmentNode variableAssignmentNode = new VariableAssignmentNode(varName, value1);
                System.out.println("BinaryAssignmentNode: " + value1 + ", " + value2);
                checkExpression(value1);
                checkExpression(value2);
                analyzeSemantic(variableAssignmentNode);

                if (!(value1 instanceof LiteralNode && value2 instanceof LiteralNode)) {
                    new UnknownNode("Must be Literal Nodes");
                }

            }
            case AssignArrNode assignArrNode -> {
                System.out.println("AssignArrNode = " + assignArrNode);
                List<ExpressionNode> elements = assignArrNode.getIndexes();
                ArrayAssignmentNode assignmentNode = assignArrNode.getArrayAssignmentNode();
                boolean literalChecker = elements.stream()
                        .anyMatch(e -> e instanceof LiteralNode);
                boolean elementChecker = assignmentNode.getElements().stream().anyMatch(
                        e -> e instanceof LiteralNode
                );
                if (!literalChecker && !elementChecker) {
                    new UnknownNode("Must be Literal Nodes");
                } else {
                    elements.forEach(
                            e -> {
                                try {
                                    System.out.println("Index expression");
                                    checkExpression(e);
                                } catch (Exception ex) {
                                    new UnknownNode("Error will patch soon");
                                }
                            }
                    );

                }

                if (!literalChecker) {
                    new UnknownNode("Must be Literal Nodes");
                }

            }
            case LoopArrNode loopArrNode -> {
                System.out.println("AssignArrNode = " + loopArrNode);

                ArrayAssignmentNode assignmentNode = loopArrNode.getArrayAssignmentNode();

                boolean elementChecker = assignmentNode.getElements().stream().anyMatch(
                        e -> e instanceof LiteralNode
                );
                if (!(loopArrNode.getPrinter() instanceof LiteralNode) && !elementChecker) {
                    new UnknownNode("Must be Literal Nodes");
                } else {
                    assignmentNode.getElements().forEach(
                            e -> {
                                try {
                                    System.out.println("element expression");
                                    checkExpression(e);
                                } catch (Exception ex) {
                                    new UnknownNode("Error will patch soon");
                                }
                            }
                    );
                    System.out.println("Value expression");
                    checkExpression(loopArrNode.getPrinter());

                }

            }
            case ArrayAssignmentNode arrayAssignmentNode -> {
                String arrVar = arrayAssignmentNode.getArrayName();
                System.out.println("ArrayAssignmentNode = " + arrayAssignmentNode);
                arrayAssignmentNode.getElements().forEach(e -> {
                    try {
                        System.out.println("Element expression");
                        checkExpression(e);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                });
            }
            case PrintVariableNode printVariableNode -> {
                System.out.println("PrintVariableNode: " + printVariableNode);
                String varOne = printVariableNode.getVariableName();
                String confirm = printVariableNode.getPrinter();
            }
            case LiteralNode literalNode ->
                //no additional checks as Literal Node - check expression not needed
                    System.out.println("LiteralNode: " + literalNode);
            case BinaryVarExtended binaryVarExtended -> {
                ExpressionNode value = binaryVarExtended.getExpression();
                ExpressionNode value2 = binaryVarExtended.getValue2();

                System.out.println("BinaryVarExtended: " + binaryVarExtended);
                checkExpression(value);
                checkExpression(value2);

            }
            default -> {
                System.out.println("Unrecognised AST Node: " + ast.getClass().getSimpleName());//like getClass

                UnknownNode unknownNode = (UnknownNode) ast;
            }
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
             new UnknownNode("Unrecognised expression");
        }
    }




}
