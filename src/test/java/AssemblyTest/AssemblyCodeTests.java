package AssemblyTest;

import org.junit.Test;
import project.compiler.assemblyInstructions.AssemblyGenerator;
import project.compiler.lexer.Lexicon;
import project.compiler.nodes.Node;
import project.compiler.syntaxtree.Parser;
import project.compiler.tokens.Token;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AssemblyCodeTests {

    @Test
    public void invalidPrintTest() {
        String code = "a = 5; print a";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        String generatedAssembly = AssemblyGenerator.assemble(ast);
        assertNull(generatedAssembly);

    }

    @Test
    public void printNodeAssembleTestTwo() {
        String code = "print \"Hello, World!\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        String generatedAssembly = AssemblyGenerator.assemble(ast);
        assertNotNull(generatedAssembly);
        assertTrue(generatedAssembly.contains("message db  'Hello, World!', 0;"));
        assertTrue(generatedAssembly.contains(" mov eax, message"));
    }
    @Test
    public void validBinaryAssignAssemblyTest() {
        String code = "x = 5; y = 10; z = x * y; print z";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        String generatedAssembly = AssemblyGenerator.assemble(ast);
        assertNotNull(generatedAssembly);
        assertTrue(generatedAssembly.contains("a dd 5"));
        assertTrue(generatedAssembly.contains("b dd 10"));
        assertTrue(generatedAssembly.contains("imul eax, ebx"));
    }
    @Test
    public void validBinaryAssignAssemblyTestTwo() {
        String code = "x = 33   ; y = 111  ; z = x / y; print z";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        String generatedAssembly = AssemblyGenerator.assemble(ast);
        assertNotNull(generatedAssembly);
        assertTrue(generatedAssembly.contains("a dd 33"));
        assertTrue(generatedAssembly.contains("b dd 111"));
        assertTrue(generatedAssembly.contains("xor edx, edx"));
        assertTrue(generatedAssembly.contains("div ebx"));
    }

    @Test
    public void invalidBinaryOperationTest() {
        String code = "print 5 + +";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        String generatedAssembly = AssemblyGenerator.assemble(ast);
        assertNull(generatedAssembly);
    }
    @Test
    public void emptyInputTest() {
        String code = "";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        String generatedAssembly = AssemblyGenerator.assemble(ast);
        assertNull(generatedAssembly);
    }

    @Test
    public void invalidSpecialCharactersTest() {
        String code = "print $hello";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        String generatedAssembly = AssemblyGenerator.assemble(ast);
        assertNull(generatedAssembly);
    }
    @Test
    public void validSpecialCharactersTest() {
        String code = "print \"$hello\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        String generatedAssembly = AssemblyGenerator.assemble(ast);
        assertNotNull(generatedAssembly);
        assertTrue(generatedAssembly.contains("message db  '$hello', 0;"));
        assertTrue(generatedAssembly.contains(" mov eax, message"));
    }

    @Test
    public void unsupportedOperationTest() {
        String code = "x = 5; x % 2";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        String generatedAssembly = AssemblyGenerator.assemble(ast);
        assertNull(generatedAssembly);
    }



}
