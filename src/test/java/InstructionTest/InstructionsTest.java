package InstructionTest;

import org.junit.Test;
import project.compiler.intercode.TACgenerator;
import project.compiler.nodes.*;
import project.vm.codegen.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InstructionsTest {

    @Test
    public void printStatementInstructionTest() {
        PrintStatementNode printNode = new PrintStatementNode(new LiteralNode("hello"));
        TACgenerator generator = new TACgenerator();
        List<Instruction> instructions = generator.generateCode(printNode);
        assertEquals(2, instructions.size());
        assertInstanceOf(LoadStringInstruction.class, instructions.get(0));
        assertInstanceOf(CallInstruction.class, instructions.get(1));
    }

    @Test
    public void binaryOperationNodeWithLiteralsTest() {
        BinaryOperationNode binaryOperationNode = new BinaryOperationNode(
                new LiteralNode("2"),"+", new LiteralNode("3")
        );
        TACgenerator generator = new TACgenerator();
        List<Instruction> instructions = generator.generateCode(binaryOperationNode);
        assertEquals(5, instructions.size());
        assertInstanceOf(LoadStringInstruction.class, instructions.get(0));
        assertInstanceOf(LoadStringInstruction.class, instructions.get(1));
        assertInstanceOf(AddInstruction.class, instructions.get(2));
        assertInstanceOf(MovInstruction.class, instructions.get(3));
        assertInstanceOf(CallInstruction.class, instructions.get(4));
    }


    @Test
    public void arrayAssignmentNodeTest() {
        List<ExpressionNode> elements = Arrays.asList(
                new LiteralNode("1"), new LiteralNode("2"), new LiteralNode("3")
        );
        ArrayAssignmentNode arrayAssignmentNode = new ArrayAssignmentNode("arr",elements );
        TACgenerator generator = new TACgenerator();
        List<Instruction> instructions = generator.generateCode(arrayAssignmentNode);
        assertEquals(6, instructions.size());
        assertInstanceOf(LoadInstruction.class, instructions.get(0));
        assertInstanceOf(LoadInstruction.class, instructions.get(1));
        assertInstanceOf(LoadInstruction.class, instructions.get(2));
        assertInstanceOf(StoreInstruction.class, instructions.get(3));
        assertInstanceOf(StoreInstruction.class, instructions.get(4));
        assertInstanceOf(StoreInstruction.class, instructions.get(5));
    }





    @Test
    public void literalNodeTest() {
        LiteralNode literalNode = new LiteralNode("42");
        TACgenerator generator = new TACgenerator();
        List<Instruction> instructions = generator.generateCode(literalNode);
        assertEquals(1, instructions.size());
        assertInstanceOf(LoadStringInstruction.class, instructions.get(0));
    }

    @Test
    public void printVariableNodeTest() {
        PrintVariableNode printNode = new PrintVariableNode("variable", new LiteralNode("42"),"print");
        TACgenerator generator = new TACgenerator();
        List<Instruction> instructions = generator.generateCode(printNode);
        assertEquals(4, instructions.size());
        assertInstanceOf(LoadStringInstruction.class, instructions.get(0));
        assertInstanceOf(StoreInstruction.class, instructions.get(1));
        assertInstanceOf(MovInstruction.class, instructions.get(2));
        assertInstanceOf(CallInstruction.class, instructions.get(3));
    }

    @Test
    public void variableAssignmentNodeTest() {
        VariableAssignmentNode variableAssignmentNode = new VariableAssignmentNode("variable", new LiteralNode("42"));
        TACgenerator generator = new TACgenerator();
        List<Instruction> instructions = generator.generateCode(variableAssignmentNode);
        assertEquals(2, instructions.size());
        assertInstanceOf(LoadStringInstruction.class, instructions.get(0));
        assertInstanceOf(StoreInstruction.class, instructions.get(1));
    }


}
