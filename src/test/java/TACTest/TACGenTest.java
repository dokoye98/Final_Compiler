package TACTest;

import org.junit.Test;
import project.compiler.intercode.TACgenerator;
import project.compiler.lexer.Lexicon;
import project.compiler.nodes.LiteralNode;
import project.compiler.nodes.Node;
import project.compiler.nodes.VariableAssignmentNode;
import project.compiler.nodes.binaryVarsEqual.AddVarEquals;
import project.compiler.syntaxtree.Parser;
import project.compiler.tokens.Token;
import project.vm.codegen.Instruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TACGenTest {
    @Test
    public void simpleAdditionTest() {
        String code = "5 + 5";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        TACgenerator tacGenerator = new TACgenerator();
        List<Instruction> instructions = tacGenerator.generateCode(ast);
        assertNotNull(instructions);
        assertEquals(5, instructions.size());
        assertEquals("LOAD R0, \"101\"", instructions.get(0).toString());
        assertEquals("LOAD R1, \"101\"", instructions.get(1).toString());
        assertEquals("ADD R2, R0 , R1", instructions.get(2).toString());
        assertEquals("MOV result, R2", instructions.get(3).toString());
        assertEquals("CALL print, R2", instructions.get(4).toString());
    }

    @Test
    public void variableAdditionTest() {
        String code = "a = 5; b = 10; c = a + b; print c";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        TACgenerator tacGenerator = new TACgenerator();
        List<Instruction> instructions = tacGenerator.generateCode(ast);
        assertNotNull(instructions);
        assertEquals(9, instructions.size());
        assertEquals("LOAD R0, \"101\"", instructions.get(0).toString());
        assertEquals("STORE a , R0", instructions.get(1).toString());
        assertEquals("LOAD R1, \"1010\"", instructions.get(2).toString());
        assertEquals("STORE b , R1", instructions.get(3).toString());
        assertEquals("MOV R2, a", instructions.get(4).toString());
        assertEquals("MOV R3, b", instructions.get(5).toString());
        assertEquals("ADD R4, R2 , R3", instructions.get(6).toString());
        assertEquals("MOV c, R4", instructions.get(7).toString());
        assertEquals("CALL print, c", instructions.get(8).toString());
    }

    @Test
    public void printStatementTest() {
        String code = "print \"hello\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        TACgenerator tacGenerator = new TACgenerator();
        List<Instruction> instructions = tacGenerator.generateCode(ast);
        assertNotNull(instructions);
        assertEquals(2, instructions.size());
        assertEquals("LOAD R0, \"hello\"", instructions.get(0).toString());
        assertEquals("CALL print, R0", instructions.get(1).toString());
    }

    @Test
    public void differentSyntaxVarTest() {
        VariableAssignmentNode varAssign1 = new VariableAssignmentNode("a", new LiteralNode("2"));
        VariableAssignmentNode varAssign2 = new VariableAssignmentNode("b", new LiteralNode("3"));
        AddVarEquals addVarEquals = new AddVarEquals("+", "c", varAssign1, varAssign2);
        TACgenerator tacGenerator = new TACgenerator();
        List<Instruction> instructions = tacGenerator.generateCode(addVarEquals);
        assertNotNull(instructions);
        assertEquals(9, instructions.size());
        assertEquals("LOAD R0, \"10\"", instructions.get(0).toString());
        assertEquals("STORE a , R0", instructions.get(1).toString());
        assertEquals("LOAD R1, \"11\"", instructions.get(2).toString());
        assertEquals("STORE b , R1", instructions.get(3).toString());
        assertEquals("MOV R2, a", instructions.get(4).toString());
        assertEquals("MOV R3, b", instructions.get(5).toString());
        assertEquals("ADD R4, R2 , R3", instructions.get(6).toString());
        assertEquals("MOV c, R4", instructions.get(7).toString());
        assertEquals("CALL print, c", instructions.get(8).toString());
    }

    @Test
    public void stringTest() {
        String code = " print \"wolf\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        TACgenerator tacGenerator = new TACgenerator();
        List<Instruction> instructions = tacGenerator.generateCode(ast);
        assertNotNull(instructions);
        assertEquals(2, instructions.size());
        assertEquals("LOAD R0, \"wolf\"", instructions.get(0).toString());
        assertEquals("CALL print, R0", instructions.get(1).toString());
    }

    @Test
    public void invalidCodeTest() {
        String code = "invalid syntax";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        TACgenerator tacGenerator = new TACgenerator();
        List<Instruction> instructions = tacGenerator.generateCode(ast);
        assertEquals(0, instructions.size());
    }

    @Test
    public void stringAssignmentAndPrintTest() {
        String code = "wolf = \"fluffy\"; print wolf";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        TACgenerator tacGenerator = new TACgenerator();
        List<Instruction> instructions = tacGenerator.generateCode(ast);
        assertNotNull(instructions);
        assertEquals(4, instructions.size());
        assertEquals("LOAD R0, \"fluffy\"", instructions.get(0).toString());
        assertEquals("STORE wolf , R0", instructions.get(1).toString());
        assertEquals("MOV R1, wolf", instructions.get(2).toString());
        assertEquals("CALL print, R1", instructions.get(3).toString());
    }

    @Test
    public void emptyCodeTest() {
        String code = "";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        TACgenerator tacGenerator = new TACgenerator();
        List<Instruction> instructions = tacGenerator.generateCode(ast);
        assertNotNull(instructions);
        assertEquals(0, instructions.size());
    }

    @Test
    public void variableAssignmentAndAdditionTest() {
        String code = "a = 5; b = 10; c = a + b; print c";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        TACgenerator tacGenerator = new TACgenerator();
        List<Instruction> instructions = tacGenerator.generateCode(ast);
        assertNotNull(instructions);
        assertEquals(9, instructions.size());
        assertEquals("LOAD R0, \"101\"", instructions.get(0).toString());
        assertEquals("STORE a , R0", instructions.get(1).toString());
        assertEquals("LOAD R1, \"1010\"", instructions.get(2).toString());
        assertEquals("STORE b , R1", instructions.get(3).toString());
        assertEquals("MOV R2, a", instructions.get(4).toString());
        assertEquals("MOV R3, b", instructions.get(5).toString());
        assertEquals("ADD R4, R2 , R3", instructions.get(6).toString());
        assertEquals("MOV c, R4", instructions.get(7).toString());
        assertEquals("CALL print, c", instructions.get(8).toString());
    }

    @Test
    public void incompleteAssignmentTest() {
        String code = "a = 12";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        TACgenerator tacGenerator = new TACgenerator();
        List<Instruction> instructions = tacGenerator.generateCode(ast);
        assertEquals(0, instructions.size());
    }

    @Test
    public void variableAdditionWithAssignment() {
        String code = "a = 12;a + 10; ";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        TACgenerator tacGenerator = new TACgenerator();
        List<Instruction> instructions = tacGenerator.generateCode(ast);
        assertNotNull(instructions);
        assertEquals(6, instructions.size());
        assertEquals("LOAD R0, \"1100\"", instructions.get(0).toString());
        assertEquals("STORE a , R0", instructions.get(1).toString());
        assertEquals("LOAD R1, \"1010\"", instructions.get(2).toString());
        assertEquals("ADD R2, R0 , R1", instructions.get(3).toString());
    }

    @Test
    public void multiplyTest() {
        String code = "5 * 76";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        TACgenerator tacGenerator = new TACgenerator();
        List<Instruction> instructions = tacGenerator.generateCode(ast);
        assertNotNull(instructions);
        assertEquals(5, instructions.size());
        assertEquals("LOAD R0, \"101\"", instructions.get(0).toString());
        assertEquals("LOAD R1, \"1001100\"", instructions.get(1).toString());
        assertEquals("MUL R2, R0 , R1", instructions.get(2).toString());
        assertEquals("MOV result, R2", instructions.get(3).toString());
        assertEquals("CALL print, R2", instructions.get(4).toString());
    }
}
