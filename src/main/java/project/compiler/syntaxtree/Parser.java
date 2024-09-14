package project.compiler.syntaxtree;

import project.compiler.arrFunctionality.ArrSplitter;
import project.compiler.lexer.OperatorCheck;
import project.compiler.nodes.*;
import project.compiler.nodes.AssignArrNode;
import project.compiler.nodes.arrayNodes.AddArray;
import project.compiler.nodes.arrayNodes.DivArray;
import project.compiler.nodes.arrayNodes.MulArray;
import project.compiler.nodes.arrayNodes.SubArray;
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
import project.compiler.syntaxfunctions.StringChecker;
import project.compiler.tokens.Token;
import project.compiler.tokens.TokenCheck;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Parser {
    public List<Token> tokens;


    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Node parse() {
        Node ast;
        boolean indexPresent = tokens.stream()
                .anyMatch(token -> token.getKey().equals(TokenCheck.ARRAY_INDEX));
        boolean forLoopPresent = tokens.stream()
                .anyMatch(token -> token.getKey().equals(TokenCheck.FOR_LOOP));
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
            }
            else if(tokens.get(2).getKey() == TokenCheck.ARRAY_LITERAL && forLoopPresent){
                ExpressionNode value = new LiteralNode(
                        tokens.stream()
                                .filter(token -> token.getKey().equals(TokenCheck.LITERAL))
                                .map(Token::getValue)
                                .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException("No matching literal token found"))
                );


                List<String> variables = tokens.stream()
                        .filter(token -> token.getKey() == TokenCheck.VARIABLE)
                        .map(Token::getValue)
                        .toList();
                String variable = variables.get(1);
                String variableName = tokens.getFirst().getValue();
                List<ExpressionNode> elements = tokens.subList(3, tokens.size()).stream()
                        .filter(token -> token.getKey() == TokenCheck.ARRAY_ELEMENT)
                        .map(token -> new LiteralNode(token.getValue()))
                        .collect(Collectors.toList());
                ArrayAssignmentNode arrayAssignmentNode = new ArrayAssignmentNode(variableName,elements);
                System.out.println(arrayAssignmentNode);
                ast = new LoopArrNode(arrayAssignmentNode,value,variable);

            }
            else if (tokens.get(2).getKey() == TokenCheck.ARRAY_LITERAL && indexPresent ){
                String variableName = tokens.getFirst().getValue();
                Optional<String> tempVar = (tokens.stream()
                        .filter(token -> token.getKey().equals(TokenCheck.NUM_VAR))
                        .map(Token::getValue)
                        .findFirst());
                String totalVar = tempVar.orElseGet( () -> "null value");
                List<ExpressionNode> elements = tokens.subList(3, tokens.size()).stream()
                        .filter(token -> token.getKey() == TokenCheck.ARRAY_ELEMENT)
                        .map(token -> new LiteralNode(token.getValue()))
                        .collect(Collectors.toList());
                List<ExpressionNode> indexes = tokens.stream()
                        .filter(token -> token.getKey() == TokenCheck.ARRAY_INDEX)
                        .map(token -> new LiteralNode(token.getValue()))
                        .collect(Collectors.toList());
                List<String> variables = tokens.stream()
                        .filter(token -> token.getKey() == TokenCheck.VARIABLE)
                        .map(Token::getValue)
                        .toList();
                ArrayAssignmentNode arrayAssignmentNode = new ArrayAssignmentNode(variableName,elements);
                System.out.println(arrayAssignmentNode);
                String operator = ArrSplitter.findOperatorSymbol(tokens);
                ast = switch (operator) {
                    case "+" -> new AddArray(variables, arrayAssignmentNode, "+", indexes, totalVar);
                    case "-" -> new SubArray(variables, arrayAssignmentNode, "-", indexes, totalVar);
                    case "/" -> new DivArray(variables, arrayAssignmentNode, "/", indexes, totalVar);
                    case "*" -> new MulArray(variables, arrayAssignmentNode, "*", indexes, totalVar);
                    default -> new UnknownNode("Unknown symbol");
                };


            }

           else if (tokens.get(2).getKey() == TokenCheck.ARRAY_LITERAL && !indexPresent){
                String variableName = tokens.getFirst().getValue();
                List<ExpressionNode> elements = tokens.subList(3, tokens.size()).stream()
                        .filter(token -> token.getKey() == TokenCheck.ARRAY_ELEMENT)
                        .map(token -> new LiteralNode(token.getValue()))
                        .collect(Collectors.toList());
                ast = new ArrayAssignmentNode(variableName,elements);
            }else if (tokens.size() == 16) {
                String var1 = tokens.get(1).getValue();
                String var2 = tokens.get(5).getValue();
                String var1Repeat = tokens.get(8).getValue();
                String varTotal = tokens.get(9).getValue();
                String var2Repeat = tokens.get(10).getValue();
                ExpressionNode value1 = new LiteralNode(tokens.get(3).getValue());
                ExpressionNode value2 = new LiteralNode(tokens.get(7).getValue());
                VariableAssignmentNode assignmentNode = new VariableAssignmentNode(var1, value1);
                VariableAssignmentNode assignmentNode2 = new VariableAssignmentNode(var2, value2);
               // System.out.println("Here is the end goal:   "+varTotal); due to tac generation
                    ast = switch (tokens.get(12).getValue()) {
                        case "+" -> new AddVarEquals("+", varTotal, assignmentNode, assignmentNode2);
                        //System.out.println("works");
                        case "/" -> new DivVarEquals("/", varTotal, assignmentNode, assignmentNode2);
                        case "*" -> new MulVarEquals("*", varTotal, assignmentNode, assignmentNode2);
                        case "-" -> new SubVarEquals("-", varTotal, assignmentNode, assignmentNode2);
                        // System.out.println("check here please");
                        default -> new UnknownNode("Unknown operator");
                        //System.out.println("There is an error here");
                    };

            }else if (tokens.get(1).getKey() == TokenCheck.VAR_ASSIGN && tokens.get(4).getKey() == TokenCheck.VAR_ASSIGN) {
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
                    ast = switch (tokens.get(7).getValue()) {
                        case "+" -> new AddVarExtended(variableName1, variableName2, value1, value2, "+");
                        case "/" -> new DivVarExtended(variableName1, variableName2, value1, value2, "/");
                        case "*" -> new MulVarExtended(variableName1, variableName2, value1, value2, "*");
                        case "-" -> new SubVarExtended(variableName1, variableName2, value1, value2, "-");
                        default -> new UnknownNode("Unknown operation");
                    };
                } else {
                    ast = new UnknownNode("Invalid variable used");

                }
            } else if (tokens.get(0).getKey() == TokenCheck.INTEGER_KEYWORD && tokens.size() == 11) {
                String variableName = tokens.get(1).getValue();
                ExpressionNode value = new LiteralNode(tokens.get(3).getValue());
                String variableName2 = tokens.get(5).getValue();
                ExpressionNode secondValue = new LiteralNode(tokens.get(7).getValue());
                VariableAssignmentNode variableNode = new VariableAssignmentNode(variableName,value);
                VariableAssignmentNode variableNodeTwo = new VariableAssignmentNode(variableName2,secondValue);
                System.out.println(variableNode);
                System.out.println(variableNodeTwo);

                    ast = switch (tokens.get(9).getValue()) {
                        case "+" -> new AddVariableNode(variableName, value, "+", secondValue, variableName2);
                        case "-" -> new SubVariableNode(variableName, value, "-", secondValue, variableName2);
                        case "*" -> new MulVariableNode(variableName, value, "*", secondValue, variableName2);
                        case "/" -> new DivVariableNode(variableName, value, "/", secondValue, variableName2);
                        default -> new UnknownNode("Invalid operation");
                    };


            } else if (OperatorCheck.isBasicBinaryList(tokens) && tokens.size() == 3) {
                ExpressionNode left = new LiteralNode(tokens.get(0).getValue());
                ExpressionNode right = new LiteralNode(tokens.get(2).getValue());

                ast = switch (tokens.get(1).getValue()) {
                    case "-" -> new SubtractionNode(left, "-", right);
                    case "+" -> new AdditionNode(left, "+", right);
                    case "*" -> new MultiplicationNode(left, "*", right);
                    case "/" -> new DivideNode(left, "/", right);
                    default -> new UnknownNode("Unknown operation");
                };
            } else if (tokens.get(1).getKey() == TokenCheck.VARIABLE && tokens.size() == 6) {
                String variableName = tokens.get(1).getValue();
                ExpressionNode value = new LiteralNode(tokens.get(2).getValue());
                String var = tokens.get(5).getValue();
                String functionCall = tokens.get(4).getValue() + " " + var;
                System.out.println(var + " "+ variableName);
                if (!Objects.equals(var, variableName)) {
                    ast = new UnknownNode("Unknown Token: incorrect variable");
                } else {
                    VariableAssignmentNode variableNode = new VariableAssignmentNode(variableName,value);
                    ast = new PrintVariableNode(variableName, value, functionCall);
                    System.out.println(variableNode);
                }
            } else {
                ast = new UnknownNode("Unknown Token: " + tokens.getFirst().getValue());
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