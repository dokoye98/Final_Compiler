package project.compiler.syntaxtree;

import project.compiler.lexer.OperatorCheck;
import project.compiler.nodes.*;
import project.compiler.nodes.binaryVarsEqual.AddVarEquals;
import project.compiler.nodes.binaryVarsEqual.DivVarEquals;
import project.compiler.nodes.binaryVarsEqual.MulVarEquals;
import project.compiler.nodes.binaryVarsEqual.SubVarEquals;
import project.compiler.nodes.binarynodes.AdditionNode;
import project.compiler.nodes.binarynodes.DivideNode;
import project.compiler.nodes.binarynodes.MultiplicationNode;
import project.compiler.nodes.binarynodes.SubtractionNode;
import project.compiler.nodes.extendedVarBinary.AddVarExtended;
import project.compiler.nodes.extendedVarBinary.DivVarExtended;
import project.compiler.nodes.extendedVarBinary.MulVarExtended;
import project.compiler.nodes.extendedVarBinary.SubVarExtended;
import project.compiler.nodes.varBinaryNode.AddVariableNode;
import project.compiler.nodes.varBinaryNode.DivVariableNode;
import project.compiler.nodes.varBinaryNode.MulVariableNode;
import project.compiler.nodes.varBinaryNode.SubVariableNode;
import project.compiler.tokens.Token;
import project.compiler.tokens.TokenCheck;

import java.util.List;
import java.util.Objects;

public class Parser {
    public List<Token> tokens;


    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Node parse() {
        Node ast;
        try {
            System.out.println("Starting parse process with tokens:");
            // tokens.forEach(System.out::println);
            if (tokens.get(0).getKey() == TokenCheck.PRINT && tokens.size() == 2) {
                ExpressionNode expression = new LiteralNode(tokens.get(1).getValue());
                ast = new PrintStatementNode(expression);
            } else if (tokens.get(0).getKey() == TokenCheck.VARIABLE && tokens.size() == 3) {
                String variableName = tokens.get(0).getValue();
                ExpressionNode expression = new LiteralNode(tokens.get(2).getValue());
                ast = new VariableAssignmentNode(variableName, expression);
            } else if (tokens.size() == 13) {
                String var1 = tokens.get(0).getValue();
                String var2 = tokens.get(3).getValue();
                String var4 = tokens.get(8).getValue();
                String varTotal = tokens.get(6).getValue();
                String var5 = tokens.get(10).getValue();
                ExpressionNode value1 = new LiteralNode(tokens.get(2).getValue());
                ExpressionNode value2 = new LiteralNode(tokens.get(5).getValue());
                VariableAssignmentNode assignmentNode = new VariableAssignmentNode(var1, value1);
                VariableAssignmentNode assignmentNode2 = new VariableAssignmentNode(var2, value2);
               // System.out.println("Here is the end goal:   "+varTotal); due to tac generation
                if (Objects.equals(var1, var4) && Objects.equals(var2, var5)) {
                    switch (tokens.get(9).getValue()) {
                        case "+":
                            ast = new AddVarEquals("+", varTotal, assignmentNode, assignmentNode2);
                            //System.out.println("works");
                            break;
                        case "/":
                            ast = new DivVarEquals("/", varTotal, assignmentNode, assignmentNode2);
                            break;
                        case "*":
                            ast = new MulVarEquals("*", varTotal, assignmentNode, assignmentNode2);
                            break;
                        case "-":
                            ast = new SubVarEquals("-", varTotal, assignmentNode, assignmentNode2);
                            System.out.println("check here please");
                            break;
                        default:
                            ast = new UnknownNode("Unknown operator");
                            //System.out.println("There is an error here");
                    }
                } else {
                    ast = new UnknownNode("Invalid variable used");
                    // System.out.println("Invalid variable HERE");
                }
            } else if (tokens.get(1).getKey() == TokenCheck.VAR_ASSIGN && tokens.get(4).getKey() == TokenCheck.VAR_ASSIGN) {
                String variableName1 = tokens.get(0).getValue();
                ExpressionNode value1 = new LiteralNode(tokens.get(2).getValue());
                VariableAssignmentNode varAssign1 = new VariableAssignmentNode(variableName1, value1);
                String variableName2 = tokens.get(3).getValue();
                ExpressionNode value2 = new LiteralNode(tokens.get(5).getValue());
                VariableAssignmentNode varAssign2 = new VariableAssignmentNode(variableName2, value2);
                System.out.println(varAssign1);
                System.out.println(varAssign2);
                String variableNameRepeat = tokens.get(6).getValue();
                String variableName2Repeat = tokens.get(8).getValue();

                if (Objects.equals(variableName1, variableNameRepeat) && Objects.equals(variableName2, variableName2Repeat)) {
                    switch (tokens.get(7).getValue()) {
                        case "+":
                            ast = new AddVarExtended(variableName1, variableName2, value1, value2, "+");
                            break;
                        case "/":
                            ast = new DivVarExtended(variableName1, variableName2, value1, value2, "/");
                            break;
                        case "*":
                            ast = new MulVarExtended(variableName1, variableName2, value1, value2, "*");
                            break;
                        case "-":
                            ast = new SubVarExtended(variableName1, variableName2, value1, value2, "-");
                            break;
                        default:
                            ast = new UnknownNode("Unknown operation");
                    }
                } else {
                    ast = new UnknownNode("Invalid variable used");

                }
            } else if (tokens.get(0).getKey() == TokenCheck.NUM_VAR && tokens.size() == 6) {
                String variableName = tokens.get(0).getValue();
                ExpressionNode value = new LiteralNode(tokens.get(2).getValue());
                String variableName2 = tokens.get(3).getValue();
                ExpressionNode secondValue = new LiteralNode(tokens.get(5).getValue());
                if (Objects.equals(variableName, variableName2)) {

                    switch (tokens.get(4).getValue()) {
                        case "+":
                            ast = new AddVariableNode(variableName, value, "+", secondValue, variableName2);
                            break;
                        case "-":
                            ast = new SubVariableNode(variableName, value, "-", secondValue, variableName2);
                            break;
                        case "*":
                            ast = new MulVariableNode(variableName, value, "*", secondValue, variableName2);
                            break;
                        case "/":
                            ast = new DivVariableNode(variableName, value, "/", secondValue, variableName2);
                            break;
                        default:
                            ast = new UnknownNode("Invalid operation");
                    }

                } else {
                    ast = new UnknownNode("Invalid variable");
                }
            } else if (OperatorCheck.isBasicBinaryList(tokens) && tokens.size() == 3) {
                ExpressionNode left = new LiteralNode(tokens.get(0).getValue());
                ExpressionNode right = new LiteralNode(tokens.get(2).getValue());

                switch (tokens.get(1).getValue()) {

                    case "-":
                        ast = new SubtractionNode(left, "-", right);
                        break;
                    case "+":
                        ast = new AdditionNode(left, "+", right);
                        break;
                    case "*":
                        ast = new MultiplicationNode(left, "*", right);
                        break;
                    case "/":
                        ast = new DivideNode(left, "/", right);
                        break;
                    default:
                        ast = new UnknownNode("Unknown operation");
                }
            } else if (tokens.get(0).getKey() == TokenCheck.VARIABLE && tokens.size() == 5) {
                System.out.println("check");
                String variableName = tokens.get(0).getValue();
                ExpressionNode value = new LiteralNode(tokens.get(2).getValue());
                String var = tokens.get(4).getValue();
                String functionCall = tokens.get(3).getValue() + " " + var;

                if (!Objects.equals(var, variableName)) {
                    ast = new UnknownNode("Unknown Token: incorrect variable");
                } else {
                    ast = new PrintVariableNode(variableName, value, functionCall);
                }
            } else {
                ast = new UnknownNode("Unknown Token: " + tokens.get(0).getValue());
            }
        } catch (Exception e) {
            ast = new UnknownNode(e.getMessage());
        }
        //System.out.println("AST before optimization: " + ast);->Not needed
        //ast = OptimizedAST.optimize(ast); -> this removes the teaching property
        //analyzeSemantic(ast); - removed due to repetition
        return ast;
    }

    public void printAST(Node ast) {

        System.out.println(ast);

    }



}