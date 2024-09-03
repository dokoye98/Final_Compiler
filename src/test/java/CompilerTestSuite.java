import AssemblyTest.AssemblyCodeTests;
import InstructionTest.InstructionsTest;
import LexerTest.LexiconTest;
import LexerTest.LineCheckTest;
import SemanticTests.SemanticTreeTest;
import SyntaxTest.ParserTest;
import TACTest.TACGenTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
     InstructionsTest.class,
        LineCheckTest.class,
        LexiconTest.class,
        SemanticTreeTest.class,
        TACGenTest.class,
        ParserTest.class,
        AssemblyCodeTests.class
})
public class CompilerTestSuite {
}
