package project.compiler.nodes.varBinaryNode;

import project.compiler.nodes.BinaryAssignmentNode;
import project.compiler.nodes.ExpressionNode;

public class AddVariableNode extends BinaryAssignmentNode {


    public AddVariableNode(String variableName, ExpressionNode value, String operation, ExpressionNode secondValue, String variableNameRepeat) {
        super(variableName, value, operation, secondValue, variableNameRepeat);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"(" + getOperation() + "): " + getType() + "\n  |\n  +-- Variable(" + getVariableName() + ")\n  +-- " + getValue() + "\n  +-- " + getSecondValue();
    }
}
