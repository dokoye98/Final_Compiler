package SyntaxTest;


import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import project.compiler.lexer.Lexicon;
import project.compiler.nodes.*;
import project.compiler.nodes.binarynodes.AdditionNode;
import project.compiler.nodes.binarynodes.DivideNode;
import project.compiler.nodes.binarynodes.MultiplicationNode;
import project.compiler.nodes.binarynodes.SubtractionNode;
import project.compiler.syntaxtree.Parser;
import project.compiler.tokens.Token;

import java.util.List;


public class ParserTest {

    @Test
   public void validPrintTest1() {
        String code = "print \"hello world\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(PrintStatementNode.class, ast);
        PrintStatementNode printNode = (PrintStatementNode) ast;
        assertEquals("Literal(\"hello world\") : String", printNode.getExpression().toString());
    }

    @Test
    public void validPrintTest2() {
        String code = "print\"hello world\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(PrintStatementNode.class, ast);
        PrintStatementNode printNode = (PrintStatementNode) ast;
        assertEquals("Literal(\"hello world\") : String", printNode.getExpression().toString());
    }

    @Test
    public void caseSensitivePrint() {
        String code = "PRINT\"hello world\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(PrintStatementNode.class, ast);
        PrintStatementNode printNode = (PrintStatementNode) ast;
        assertEquals("Literal(\"hello world\") : String", printNode.getExpression().toString());
    }

    @Test
    public void stringSpace() {
        String code = "print\"hello                       world\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(PrintStatementNode.class, ast);
        PrintStatementNode printNode = (PrintStatementNode) ast;
        assertEquals("Literal(\"hello                       world\") : String", printNode.getExpression().toString());
    }

    @Test
    public void emptyStringLiteral() {
        String code = "print \"\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(PrintStatementNode.class, ast);
        PrintStatementNode printNode = (PrintStatementNode) ast;
        assertEquals("Literal(\"\") : String", printNode.getExpression().toString());
    }

    @Test
    public void specialCharacterStringLiteral() {
        String code = "print \"hello\\world\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(PrintStatementNode.class, ast);
        PrintStatementNode printNode = (PrintStatementNode) ast;
        assertEquals("Literal(\"hello\\world\") : String", printNode.getExpression().toString());
    }

    @Test
    public void whitespaceHandling() {
        String code = "    print \"hello world\"   ";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(PrintStatementNode.class, ast);
        PrintStatementNode printNode = (PrintStatementNode) ast;
        assertEquals("Literal(\"hello world\") : String", printNode.getExpression().toString());
    }

    @Test
    public void additionTest() {
        String code = "5 + 5";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(BinaryOperationNode.class, ast);
        BinaryOperationNode binaryNode = (AdditionNode) ast;
        assertInstanceOf(LiteralNode.class, binaryNode.getLeft());
        assertInstanceOf(LiteralNode.class, binaryNode.getRight());
        assertEquals("5", ((LiteralNode) binaryNode.getLeft()).getValue());
        assertEquals("5", ((LiteralNode) binaryNode.getRight()).getValue());
    }

    @Test
    public void binaryAssignTest() {
        String code = "a = 5; b = 4; c = a + b; print c";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(BinaryAssignNode.class, ast);
        BinaryAssignNode binaryNode = (BinaryAssignNode) ast;
        LiteralNode literalNode = new LiteralNode("5");
        LiteralNode literalNode1 = new LiteralNode("4");
        assertInstanceOf(LiteralNode.class, binaryNode.getVariableOne().getExpression());
        assertInstanceOf(LiteralNode.class, binaryNode.getVariableTwo().getExpression());
        assertEquals(literalNode.getValue(), ((LiteralNode) binaryNode.getVariableOne().getExpression()).getValue());
        assertEquals(literalNode1.getValue(), ((LiteralNode) binaryNode.getVariableTwo().getExpression()).getValue());
    }

    @Test
    public void subtractionTest() {
        String code = "10 - 3";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(BinaryOperationNode.class, ast);
        BinaryOperationNode binaryNode = (SubtractionNode) ast;
        assertEquals("10", ((LiteralNode) binaryNode.getLeft()).getValue());
        assertEquals("3", ((LiteralNode) binaryNode.getRight()).getValue());
    }

    @Test
    public void multiplicationTest() {
        String code = "4 * 2";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(BinaryOperationNode.class, ast);
        BinaryOperationNode binaryNode = (MultiplicationNode) ast;
        assertEquals("4", ((LiteralNode) binaryNode.getLeft()).getValue());
        assertEquals("2", ((LiteralNode) binaryNode.getRight()).getValue());
    }

    @Test
    public void divisionTest() {
        String code = "8 / 2";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(BinaryOperationNode.class, ast);
        BinaryOperationNode binaryNode = (DivideNode) ast;
        assertEquals("8", ((LiteralNode) binaryNode.getLeft()).getValue());
        assertEquals("2", ((LiteralNode) binaryNode.getRight()).getValue());
    }

    @Test
    public void invalidSyntaxTest() {
        String code = "a = 5 b = 4";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertEquals(UnknownNode.class, ast.getClass());
    }

    @Test
    public void incompleteVarTest() {
        String code = "a = 12";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(UnknownNode.class, ast);
    }

    @Test
    public void binaryParamsTest() {
        String code = "a + 10";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(BinaryOperationNode.class, ast);
        BinaryOperationNode binaryNode = (BinaryOperationNode) ast;
        assertInstanceOf(LiteralNode.class, binaryNode.getLeft());
        assertInstanceOf(LiteralNode.class, binaryNode.getRight());
    }

    @Test
    public void invalidVarTest() {
        String code = "a = a + 10";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(UnknownNode.class, ast);
    }


    @Test
    public void loopTest() {
        String code = "a=[10,1,2,3,5]; for i in a; print i";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(LoopArrNode.class, ast);
        LoopArrNode loopArrNode = (LoopArrNode) ast;
        List<ExpressionNode> elements = loopArrNode.getArrayAssignmentNode().getElements();
      elements.forEach( e->
              assertInstanceOf(LiteralNode.class,e));

    }
    @Test
    public void loopEdgeCaseTest() {
        String code = "a=[1,2,3,4,5,67,2,1]; for i in a; print i";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(LoopArrNode.class, ast);
        LoopArrNode loopArrNode = (LoopArrNode) ast;
        List<ExpressionNode> elements = loopArrNode.getArrayAssignmentNode().getElements();
        elements.forEach( e->
                assertInstanceOf(LiteralNode.class,e));

    }
    @Test
    public void invalidLoopTest() {
        String code = "a=[1,2,3,4,5,67,2,1]; for i in a; print i";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(LoopArrNode.class, ast);
        LoopArrNode loopArrNode = (LoopArrNode) ast;
        List<ExpressionNode> elements = loopArrNode.getArrayAssignmentNode().getElements();
        elements.forEach( e->
                assertInstanceOf(LiteralNode.class,e));

    }
    @Test
    public void arrayAssignTest() {
        String code = "a=[1,2,3,4,5,67,2,1]; c=a[1]; b=[4]; e = c + b; print c";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(AssignArrNode.class, ast);
        AssignArrNode assignArrNode = (AssignArrNode) ast;
        assertInstanceOf(ArrayAssignmentNode.class,assignArrNode.getArrayAssignmentNode());
        List<ExpressionNode> elements = assignArrNode.getArrayAssignmentNode().getElements();
        elements.forEach( e->
                assertInstanceOf(LiteralNode.class,e));

    }
    @Test
    public void invalidArrayAssignTest() {
        String code = "$=[1,2,3,4,5,67,2,1]; c=a[1]; b=[4]; e = c * b;";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(UnknownNode.class, ast);
    }



}