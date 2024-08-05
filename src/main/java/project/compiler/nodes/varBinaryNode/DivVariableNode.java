package project.compiler.nodes.varBinaryNode;

import project.compiler.nodes.BinaryAssignmentNode;
import project.compiler.nodes.ExpressionNode;

public class DivVariableNode extends BinaryAssignmentNode {


    public DivVariableNode(String variableName, ExpressionNode value, String operation, ExpressionNode secondValue, String variableNameRepeat) {
        super(variableName, value, operation, secondValue, variableNameRepeat);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"(" + getOperation() + "): " + getType() + "\n  |\n  +-- Variable(" + getVariableName() + ")\n  +-- " + getValue() + "\n  +-- " + getSecondValue();
    }
}
