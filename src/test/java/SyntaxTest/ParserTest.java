package SyntaxTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
    void validPrintTest1() {
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
    void validPrintTest2() {
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
    void caseSensitivePrint() {
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
    void stringSpace() {
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
    void emptyStringLiteral() {
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
    void specialCharacterStringLiteral() {
        String code = "print \"hello\\nworld\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(PrintStatementNode.class, ast);
        PrintStatementNode printNode = (PrintStatementNode) ast;
        assertEquals("Literal(\"hello\\nworld\") : String", printNode.getExpression().toString());
    }

    @Test
    void whitespaceHandling() {
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
    void additionTest() {
        String code = "5 + 5";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(BinaryOperationNode.class, ast);
        BinaryOperationNode binaryNode = (AdditionNode) ast;


    }
    @Test
    void binaryAssignTest() {
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
    void subtractionTest() {
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
    void multiplicationTest() {
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
    void divisionTest() {
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
    void invalidSyntaxTest() {
        String code = "a = 5 b = 4";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
       Node ast = parser.parse();
        Assertions.assertEquals(UnknownNode.class,ast.getClass());
    }


    @Test
    void incompleteVarTest() {
        String code = "a = 12";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(UnknownNode.class, ast);

    }

    @Test
    void binaryParamsTest() {
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
    void invalidVarTest() {
        String code = "a = a + 10";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(UnknownNode.class, ast);

    }




}