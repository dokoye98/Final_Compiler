package LexerTest;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import project.compiler.lexer.Lexicon;
import project.compiler.tokens.Token;
import project.compiler.tokens.TokenCheck;

import java.util.List;

public class LexiconTest {

    @Test
    public void generatePrintTest(){
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
    public void testPrintStatementWithSpace() {
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
    public  void testInvalidStatement() {
        Lexicon lexer = new Lexicon("invalid \"hello world\"");
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();

        Assertions.assertEquals(1, tokens.size());
        Assertions.assertEquals(TokenCheck.UNKNOWN, tokens.get(0).getKey());

        Assertions.assertTrue(tokens.get(0).getValue().startsWith("invalid \"hello world\""));
    }
    @Test
    public   void nullEntryStringTest() {

        Lexicon lexer = new Lexicon("null");
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();

        Assertions.assertEquals(1, tokens.size());
        Assertions.assertEquals(TokenCheck.UNKNOWN, tokens.getFirst().getKey());
        //assertTrue(tokens.get(0).getValue().startsWith("Failed to compile due to code: "));
    }
    @Test
    public  void variablesTest() {
        String code = "a = \"hello\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Assertions.assertEquals("a", tokens.get(0).getValue());
        Assertions.assertEquals("=", tokens.get(1).getValue());
        Assertions.assertEquals("hello", tokens.get(2).getValue());

    }
    @Test
    public  void variablesSpacingTest() {
        String code = "x23= \"    hello\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Assertions.assertEquals("x23", tokens.get(0).getValue());
        Assertions.assertEquals("=", tokens.get(1).getValue());
        Assertions.assertEquals("    hello", tokens.get(2).getValue());
    }
    @Test
    public void additionTest() {
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
    public void subtractionTest() {
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
    public void additionSyntaxTest() {
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
    public void multiplyTest() {
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
    public void multiplySyntaxTest() {
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
    public void variableBinaryTest() {
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
    public void varAddVarTest() {
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
    public void whiteSpaceStringTest() {
        String code = "   ";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Assertions.assertTrue(tokens.isEmpty());
    }

    @Test
    public void specialCharactersInStringLiteralTest() {
        String code = "print \"hello \\\"world\\\"\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Assertions.assertEquals(1, tokens.size());
        Assertions.assertEquals("UNKNOWN", tokens.get(0).getKey().name());
    }

    @Test
    public void mixedTokensTest() {
        String code = "print \"hello\" + a";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Assertions.assertEquals(1, tokens.size());
        Assertions.assertNotEquals(TokenCheck.PRINT, tokens.get(0).getKey());
        Assertions.assertNotEquals("print", tokens.get(0).getValue());
    }

    @Test
    public void simpleVariableAssignment() {
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
    public void variableAddition() {
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
    @Test
    public void testForLoopWithArray() {
        String code = "a = [1,2,3]; for i in a; print i";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Assertions.assertEquals(12, tokens.size());
        Assertions.assertEquals(TokenCheck.VARIABLE, tokens.get(0).getKey());
        Assertions.assertEquals("a", tokens.get(0).getValue());
        Assertions.assertEquals(TokenCheck.VAR_ASSIGN, tokens.get(1).getKey());
        Assertions.assertEquals("=", tokens.get(1).getValue());
        Assertions.assertEquals(TokenCheck.ARRAY_LITERAL, tokens.get(2).getKey());
        Assertions.assertEquals("1,2,3", tokens.get(2).getValue());
        Assertions.assertEquals(TokenCheck.ARRAY_ELEMENT, tokens.get(3).getKey());
        Assertions.assertEquals("1", tokens.get(3).getValue());
        Assertions.assertEquals(TokenCheck.ARRAY_ELEMENT, tokens.get(4).getKey());
        Assertions.assertEquals("2", tokens.get(4).getValue());
        Assertions.assertEquals(TokenCheck.ARRAY_ELEMENT, tokens.get(5).getKey());
        Assertions.assertEquals("3", tokens.get(5).getValue());
        Assertions.assertEquals(TokenCheck.FOR_LOOP, tokens.get(6).getKey());
        Assertions.assertEquals("for", tokens.get(6).getValue());
        Assertions.assertEquals(TokenCheck.VARIABLE, tokens.get(7).getKey());
        Assertions.assertEquals("i", tokens.get(7).getValue());
        Assertions.assertEquals(TokenCheck.IN_KEYWORD, tokens.get(8).getKey());
        Assertions.assertEquals("in", tokens.get(8).getValue());
        Assertions.assertEquals(TokenCheck.ARRAY_ASSIGN, tokens.get(9).getKey());
        Assertions.assertEquals("a", tokens.get(9).getValue());
        Assertions.assertEquals(TokenCheck.PRINT, tokens.get(10).getKey());
        Assertions.assertEquals("print", tokens.get(10).getValue());
        Assertions.assertEquals(TokenCheck.LITERAL, tokens.get(11).getKey());
        Assertions.assertEquals("i", tokens.get(11).getValue());

    }


}
