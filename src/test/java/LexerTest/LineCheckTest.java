package LexerTest;

import org.junit.Test;
import project.compiler.lexer.LineCheck;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LineCheckTest {

    @Test
    public void emptyStringTest() {
        String input = "";
        List<String> result = LineCheck.lineChecker(input);
        assertTrue(result.isEmpty());
    }

    @Test
    public void singleLineTest() {
        String input = " fluffy dogs are happy";
        List<String> result = LineCheck.lineChecker(input);
        //System.out.println(result);
        assertEquals(1, result.size());
        assertEquals("fluffy dogs are happy", result.get(0));
    }

    @Test
    public void lineLengthTest() {
        String input = "Batman beats captain america;who knows";
        List<String> result = LineCheck.lineChecker(input);
        //System.out.println(result);
        assertEquals(2, result.size());
        assertEquals("Batman beats captain america", result.get(0));
        assertEquals("who knows", result.get(1));
    }

    @Test
    public void whitespaceTest() {
        String input = "  first line  ;  second line  ";
        List<String> result = LineCheck.lineChecker(input);
        //System.out.println(result);
        assertEquals(2, result.size());
        assertEquals("first line", result.get(0));
        assertEquals("second line", result.get(1));
    }

    @Test
    public void onlySemiColonTest() {
        String input = ";;;   ;;;";
        List<String> result = LineCheck.lineChecker(input);
        //System.out.println(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void doubleSemiColonTest() {
        String input = "First line;;Second line";
        List<String> result = LineCheck.lineChecker(input);
        //System.out.println(result);
        assertEquals(2, result.size());
        assertEquals("First line", result.get(0));
        assertEquals("Second line", result.get(1));
    }
}
