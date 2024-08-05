package LexerTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import project.compiler.lexer.Lexicon;
import project.compiler.tokens.Token;
import project.compiler.tokens.TokenCheck;

import java.util.List;
import static org.junit.Assert.*;

public class TokenTest {

    @Test
     void testPrintStatementWithoutSpace() {
        Lexicon lexer = new Lexicon("print\"hello world\"");
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();

        Assertions.assertEquals(2, tokens.size());
        Assertions.assertEquals(TokenCheck.PRINT, tokens.get(0).getKey());
        Assertions.assertEquals("print", tokens.get(0).getValue());
        Assertions.assertEquals(TokenCheck.LITERAL, tokens.get(1).getKey());
        Assertions.assertEquals("hello world", tokens.get(1).getValue());
    }

    @Test
     void testPrintStatementWithSpace() {
        Lexicon lexer = new Lexicon("print \"hello world\"");
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();

        Assertions.assertEquals(2, tokens.size());
        Assertions.assertEquals(TokenCheck.PRINT, tokens.get(0).getKey());
        Assertions.assertEquals("print", tokens.get(0).getValue());
        Assertions.assertEquals(TokenCheck.LITERAL, tokens.get(1).getKey());
        Assertions.assertEquals("hello world", tokens.get(1).getValue());
    }

    @Test
     void testInvalidStatement() {
        Lexicon lexer = new Lexicon("invalid \"hello world\"");
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();

        Assertions.assertEquals(1, tokens.size());
        Assertions.assertEquals(TokenCheck.UNKNOWN, tokens.get(0).getKey());

       Assertions.assertTrue(tokens.get(0).getValue().startsWith("invalid \"hello world\""));
    }

    @Test
     void nullEntryStringTest() {

        Lexicon lexer = new Lexicon("null");
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();

        Assertions.assertEquals(1, tokens.size());
        Assertions.assertEquals(TokenCheck.UNKNOWN, tokens.get(0).getKey());
        //assertTrue(tokens.get(0).getValue().startsWith("Failed to compile due to code: "));
    }
    @Test

     void variablesTest() {
        String code = "a = \"hello\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Assertions.assertEquals("a", tokens.get(0).getValue());
        Assertions.assertEquals("=", tokens.get(1).getValue());
        Assertions.assertEquals("hello", tokens.get(2).getValue());

    }
    @Test
     void variablesSpacingTest() {
        String code = "x23= \"    hello\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Assertions.assertEquals("x23", tokens.get(0).getValue());
        Assertions.assertEquals("=", tokens.get(1).getValue());
        Assertions.assertEquals("    hello", tokens.get(2).getValue());
    }
    /*
    @Test - this code for previous method that was in compiler
    public void lineCheckTest() {
        String code = "wolves = \"big dogs\"; print wolves";
        List<String> lines = lineChecker(code);

        for (String line : lines) {
            System.out.println(line);
        }
    }*/
    @Test
     void additionTest() {
        String code = "2 + 4";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Assertions.assertEquals(3, tokens.size());
        Assertions.assertEquals("+", tokens.get(1).getValue());
        Assertions.assertEquals("2", tokens.get(0).getValue());
        Assertions.assertEquals("4", tokens.get(2).getValue());
    }

    @Test
     void subtractionTest() {
        String code = "2 - 4";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Assertions.assertEquals(3, tokens.size());
        Assertions.assertEquals("-", tokens.get(1).getValue());
        Assertions.assertEquals("2", tokens.get(0).getValue());
        Assertions.assertEquals("4", tokens.get(2).getValue());
    }
    @Test
     void additionSyntaxTest() {
        String code = "5       +            5";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Assertions.assertEquals(3, tokens.size());
        Assertions.assertEquals("+", tokens.get(1).getValue());
        Assertions.assertEquals("5", tokens.get(0).getValue());
        Assertions.assertEquals("5", tokens.get(2).getValue());
    }

    @Test
     void multiplyTest() {
        String code = "2 * 4";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Assertions.assertEquals(3, tokens.size());
        Assertions.assertEquals("2", tokens.get(0).getValue());
        Assertions.assertEquals("*", tokens.get(1).getValue());
        Assertions.assertEquals("4", tokens.get(2).getValue());
    }
    @Test
     void multiplySyntaxTest() {
        String code = "2          *           4";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Assertions.assertEquals(3, tokens.size());
        Assertions.assertEquals("2", tokens.get(0).getValue());
        Assertions.assertEquals("*", tokens.get(1).getValue());
        Assertions.assertEquals("4", tokens.get(2).getValue());
    }


@Test
     void variableBinaryTest() {
        String code = "a =12; b = 22; a + b";
    Lexicon lexer = new Lexicon(code);
    lexer.splitter();
    List<Token> tokens = lexer.getTokens();
    Assertions.assertEquals(9, tokens.size());
    Assertions.assertEquals("NUM_VAR", tokens.get(0).getKey().name());
    Assertions.assertEquals("VAR_ASSIGN", tokens.get(1).getKey().name());
    Assertions.assertEquals("LITERAL", tokens.get(2).getKey().name());
    Assertions.assertEquals("NUM_VAR", tokens.get(3).getKey().name());
    Assertions.assertEquals("VAR_ASSIGN", tokens.get(4).getKey().name());
    Assertions.assertEquals("LITERAL", tokens.get(5).getKey().name());
    Assertions.assertEquals("VAR_ADD_VAR", tokens.get(6).getKey().name());
    Assertions.assertEquals("ADDITION", tokens.get(7).getKey().name());
    Assertions.assertEquals("VAR_ADD_VAR", tokens.get(8).getKey().name());

}
@Test
    public void varAddVarTest(){
    String code = "a = 12; b = 88; c = a + b; print c";
    Lexicon lexer = new Lexicon(code);
    lexer.splitter();
    List<Token> tokens = lexer.getTokens();
    Assertions.assertEquals(13, tokens.size());
    Assertions.assertEquals("NUM_VAR", tokens.get(0).getKey().name());
    Assertions.assertEquals("VAR_ASSIGN", tokens.get(1).getKey().name());
    Assertions.assertEquals("LITERAL", tokens.get(2).getKey().name());
    Assertions.assertEquals("NUM_VAR", tokens.get(3).getKey().name());
    Assertions.assertEquals("VAR_ASSIGN", tokens.get(4).getKey().name());
    Assertions.assertEquals("LITERAL", tokens.get(5).getKey().name());
    Assertions.assertEquals("NUM_VAR", tokens.get(6).getKey().name());
    Assertions.assertEquals("VAR_ASSIGN", tokens.get(7).getKey().name());
    Assertions.assertEquals("NUM_VAR", tokens.get(8).getKey().name());
    Assertions.assertEquals("ADDITION", tokens.get(9).getKey().name());
    Assertions.assertEquals("NUM_VAR", tokens.get(10).getKey().name());
    Assertions.assertEquals("PRINT", tokens.get(11).getKey().name());
    Assertions.assertEquals("LITERAL", tokens.get(12).getKey().name());
}


    @Test
    void whiteSpaceStringTest() {
        String code = "   ";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Assertions.assertTrue(tokens.isEmpty());
    }



    @Test
    void specialCharactersInStringLiteralTest() {
        String code = "print \"hello \\\"world\\\"\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Assertions.assertEquals(1, tokens.size());
        Assertions.assertEquals("UNKNOWN", tokens.get(0).getKey().name());

    }
    @Test
    void mixedTokensTest() {
        String code = "print \"hello\" + a";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Assertions.assertEquals(1, tokens.size());
        Assertions.assertNotEquals(TokenCheck.PRINT, tokens.get(0).getKey());
        Assertions.assertNotEquals("print", tokens.get(0).getValue());

    }
    @Test
    void simpleVariableAssignment() {
        String code = "a = 12";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Assertions.assertEquals(3, tokens.size());
        Assertions.assertEquals("NUM_VAR", tokens.get(0).getKey().name());
        Assertions.assertEquals("VAR_ASSIGN", tokens.get(1).getKey().name());
        Assertions.assertEquals("LITERAL", tokens.get(2).getKey().name());
        Assertions.assertEquals("a", tokens.get(0).getValue());
        Assertions.assertEquals("=", tokens.get(1).getValue());
        Assertions.assertEquals("12", tokens.get(2).getValue());
    }

    @Test
    void variableAddition() {
        String code = "a + 10";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Assertions.assertEquals(3, tokens.size());
        Assertions.assertEquals("VAR_ADD", tokens.get(0).getKey().name());
        Assertions.assertEquals("ADDITION", tokens.get(1).getKey().name());
        Assertions.assertEquals("LITERAL", tokens.get(2).getKey().name());
        Assertions.assertEquals("a", tokens.get(0).getValue());
        Assertions.assertEquals("+", tokens.get(1).getValue());
        Assertions.assertEquals("10", tokens.get(2).getValue());
    }

}
