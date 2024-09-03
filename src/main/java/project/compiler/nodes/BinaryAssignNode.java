package project.compiler.nodes;

public class BinaryAssignNode extends  ExpressionNode{

    private String operation;
    private String variableName;


    private final VariableAssignmentNode variableOne;
    private final VariableAssignmentNode variableTwo;

    public BinaryAssignNode(String operation, String variableName, VariableAssignmentNode variableOne, VariableAssignmentNode variableTwo) {
        this.operation = operation;
        this.variableName = variableName;
        this.variableOne = variableOne;
        this.variableTwo = variableTwo;
    }

    public String getOperation() {
        return operation;
    }

    public String getVariableName() {
        return variableName;
    }

    public VariableAssignmentNode getVariableOne() {
        return variableOne;
    }

    public VariableAssignmentNode getVariableTwo() {
        return variableTwo;
    }
    @Override
    public String getType() {
        return "Integer";
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (" + getOperation() + "): " + getType() + "\n  |\n  +-- Variable(" + getVariableName() + ")\n  +-- " + getVariableOne() + "\n  +-- " + getVariableTwo();
    }
}
