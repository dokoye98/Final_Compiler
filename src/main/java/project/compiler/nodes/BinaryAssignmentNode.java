package project.compiler.nodes;

public class BinaryAssignmentNode extends ExpressionNode{

   private String operation;
    private String variableName;
    private final ExpressionNode value;
    private final ExpressionNode secondValue;

    private String variableNameRepeat;
    public BinaryAssignmentNode(String variableName, ExpressionNode value, String operation, ExpressionNode secondValue,String variableNameRepeat) {
        this.value = value;
        this.operation = operation;
        this.variableName = variableName;
        this.secondValue = secondValue;
        this.variableNameRepeat = variableNameRepeat;
    }

    public String getOperation() {
        return operation;
    }

    public String getVariableName() {
        return variableName;
    }

    public ExpressionNode getValue() {
        return value;
    }

    public ExpressionNode getSecondValue() {
        return secondValue;
    }
    @Override
    public String getType() {
        return "Integer";
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"(" + getOperation() + "): " + getType() + "\n  |\n  +-- Variable(" + getVariableName() + ")\n  +-- " + getValue() + "\n  +-- " + getSecondValue();
    }
}
