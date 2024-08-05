package SemanticTests;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import project.compiler.nodes.*;
import project.compiler.nodes.binaryVarsEqual.AddVarEquals;
import project.compiler.nodes.binaryVarsEqual.DivVarEquals;
import project.compiler.nodes.binaryVarsEqual.MulVarEquals;
import project.compiler.nodes.binaryVarsEqual.SubVarEquals;
import project.compiler.nodes.extendedVarBinary.AddVarExtended;
import project.compiler.semantic.SemanticTree;

import project.compiler.nodes.binarynodes.AdditionNode;



public class SemanticTreeTest {


    @Test
    void negativeNodeAssignments() {
        VariableAssignmentNode varAssign = new VariableAssignmentNode("a", new LiteralNode("10"));
        PrintStatementNode printNode = new PrintStatementNode(new VariableNode("a"));
        assertDoesNotThrow(() -> SemanticTree.analyzeSemantic(varAssign));
        Exception exception = assertThrows(Exception.class, () -> SemanticTree.analyzeSemantic(printNode));
        assertTrue(exception.getMessage().contains("Undefined variable"));
    }




    @Test
    void testAnalyzeSimpleVariableAssignment() {
        VariableAssignmentNode varAssign = new VariableAssignmentNode("a", new LiteralNode("10"));
        assertDoesNotThrow(() -> SemanticTree.analyzeSemantic(varAssign));
    }

    @Test
    void testAnalyzePrintStatement() {
        PrintStatementNode printNode = new PrintStatementNode(new LiteralNode("hello"));
        assertDoesNotThrow(() -> SemanticTree.analyzeSemantic(printNode));
    }



    @Test
    void testAnalyzeUndefinedVariableUse() {
        BinaryOperationNode addNode = new AdditionNode(new VariableNode("a"), "+", new VariableNode("b"));
        VariableAssignmentNode varAssign = new VariableAssignmentNode("c", addNode);
        Exception exception = assertThrows(Exception.class, () -> SemanticTree.analyzeSemantic(varAssign));
        System.out.println(exception.getMessage());
    }






    @Test
    void testAnalyzeBinaryAssignNode() {
        VariableAssignmentNode varAssign1 = new VariableAssignmentNode("a", new LiteralNode("5"));
        VariableAssignmentNode varAssign2 = new VariableAssignmentNode("b", new LiteralNode("10"));
        BinaryAssignNode binaryAssignNode = new AddVarEquals("+", "c", varAssign1, varAssign2);
        assertDoesNotThrow(() -> SemanticTree.analyzeSemantic(binaryAssignNode));
    }

    @Test
    void testAnalyzeBinaryVarExtended() {
        BinaryVarExtended binaryVarExtended = new AddVarExtended("a", "b", new LiteralNode("2"), new LiteralNode("3"), "+");
        assertDoesNotThrow(() -> SemanticTree.analyzeSemantic(binaryVarExtended));
    }

    @Test
    void incompatibleNodes() {
        VariableAssignmentNode varAssign1 = new VariableAssignmentNode("a", new LiteralNode("5"));
        VariableAssignmentNode varAssign2 = new VariableAssignmentNode("b", new LiteralNode("3"));
        BinaryOperationNode addNode = new AdditionNode(new VariableNode("a"), "+", new VariableNode("b"));
        VariableAssignmentNode varAssign3 = new VariableAssignmentNode("c", addNode);
        PrintStatementNode printNode = new PrintStatementNode(new VariableNode("c"));
        Exception exception = assertThrows(Exception.class,() -> {
            SemanticTree.analyzeSemantic(varAssign1);
            SemanticTree.analyzeSemantic(varAssign2);
            SemanticTree.analyzeSemantic(varAssign3);
            SemanticTree.analyzeSemantic(printNode);
        });

        assertTrue(exception.getMessage().contains("Unrecognised expression"));
    }



    @Test
    void testAnalyzeSubVarEquals() {
        VariableAssignmentNode varAssign1 = new VariableAssignmentNode("a", new LiteralNode("3"));
        VariableAssignmentNode varAssign2 = new VariableAssignmentNode("b", new LiteralNode("1"));
        BinaryAssignNode subVarEquals = new SubVarEquals("-", "c", varAssign1, varAssign2);
        assertDoesNotThrow(() -> SemanticTree.analyzeSemantic(subVarEquals));
    }

    @Test
    void testAnalyzeMulVarEquals() {
        VariableAssignmentNode varAssign1 = new VariableAssignmentNode("a", new LiteralNode("2"));
        VariableAssignmentNode varAssign2 = new VariableAssignmentNode("b", new LiteralNode("4"));
        BinaryAssignNode mulVarEquals = new MulVarEquals("*", "c", varAssign1, varAssign2);
        assertDoesNotThrow(() -> SemanticTree.analyzeSemantic(mulVarEquals));
    }

    @Test
    void testAnalyzeDivVarEquals() {
        VariableAssignmentNode varAssign1 = new VariableAssignmentNode("a", new LiteralNode("8"));
        VariableAssignmentNode varAssign2 = new VariableAssignmentNode("b", new LiteralNode("2"));
        BinaryAssignNode divVarEquals = new DivVarEquals("/", "c", varAssign1, varAssign2);
        assertDoesNotThrow(() -> SemanticTree.analyzeSemantic(divVarEquals));
    }
    @Test
    void variableAssignmentTest() {
        VariableAssignmentNode varAssign = new VariableAssignmentNode("a", new LiteralNode("10"));
        assertDoesNotThrow(() -> SemanticTree.analyzeSemantic(varAssign));
    }

    @Test
    void undefinedVariableTest() {
        VariableAssignmentNode varAssign = new VariableAssignmentNode("a", new LiteralNode("10"));
        PrintStatementNode printNode = new PrintStatementNode(new VariableNode("b"));
        assertDoesNotThrow(() -> SemanticTree.analyzeSemantic(varAssign));
        Exception exception = assertThrows(Exception.class, () -> SemanticTree.analyzeSemantic(printNode));
        assertTrue(exception.getMessage().contains("Undefined variable"));
    }

    @Test
    void simpleVariableAssignment() {
        VariableAssignmentNode varAssign = new VariableAssignmentNode("a", new LiteralNode("12"));
        assertDoesNotThrow(() -> SemanticTree.analyzeSemantic(varAssign));
    }



}
