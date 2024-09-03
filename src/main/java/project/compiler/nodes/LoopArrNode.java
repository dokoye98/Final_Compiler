package project.compiler.nodes;

public class LoopArrNode extends ExpressionNode{

    private final ArrayAssignmentNode arrayAssignmentNode;
    private final ExpressionNode printer;
    private final String initialVariable;

    public LoopArrNode(ArrayAssignmentNode arrayAssignmentNode, ExpressionNode printer, String initialVariable) {
        this.arrayAssignmentNode = arrayAssignmentNode;
        this.printer = printer;
        this.initialVariable = initialVariable;
    }

    public ArrayAssignmentNode getArrayAssignmentNode() {
        return arrayAssignmentNode;
    }

    public ExpressionNode getPrinter() {
        return printer;
    }

    public String getInitialVariable() {
        return initialVariable;
    }

    @Override
    public String getType() {
        return "Loop";
    }

    @Override
    public String toString() {
        return "LoopArray : void\n  |\n  +-- LoopVariable(" + getInitialVariable() + ")\n  +-- Array("
                + getArrayAssignmentNode().getArrayName() + ")"
                + "\n  |\n  +-- Length(" + getArrayAssignmentNode().getArrayName() + ")";
    }

}
