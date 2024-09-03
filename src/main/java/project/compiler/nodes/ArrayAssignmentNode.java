package project.compiler.nodes;

import java.util.List;
import java.util.stream.Collectors;

public class ArrayAssignmentNode extends ExpressionNode{

    private String arrayName;
    private final List<ExpressionNode>  elements;
    public ArrayAssignmentNode(String arrayName, List<ExpressionNode> elements) {
        this.arrayName = arrayName;
        this.elements = elements;
    }
    @Override
    public String getType() {
        return "Array";
    }

    public String getArrayName() {
        return arrayName;
    }

    public List<ExpressionNode> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return "ArrayAssignment : Array\n  |\n  +-- Array(" + arrayName + ")\n  +-- "+ " [" + elements.stream().map(ExpressionNode::toString).collect(Collectors.joining(", ")) + "]";
    }
}
