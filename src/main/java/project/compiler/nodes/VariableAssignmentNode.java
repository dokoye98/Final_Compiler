package project.compiler.nodes;

public class VariableAssignmentNode extends Node{

    private final String variableName;
    private final ExpressionNode expression;

    public VariableAssignmentNode(String variableName, ExpressionNode expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    public String getVariableName() {
        return variableName;
    }

    public ExpressionNode getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "VariableAssignment : void\n  |\n  +-- Variable(" + variableName + ")\n  +-- " + expression;
    }

}
