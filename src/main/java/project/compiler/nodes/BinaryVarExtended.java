package project.compiler.nodes;

public class BinaryVarExtended extends ExpressionNode {

    private final String variableName;



    private final String variableName2;
    private final ExpressionNode expression;
    private final ExpressionNode value2;
    private final String operation;

    public BinaryVarExtended(String variableName, String variableName2, ExpressionNode expression, ExpressionNode value2, String operation) {
        this.variableName = variableName;
        this.variableName2 = variableName2;
        this.expression = expression;
        this.value2 = value2;
        this.operation = operation;
    }

    public String getVariableName() {
        return variableName;
    }

    public ExpressionNode getExpression() {
        return expression;
    }
    public String getVariableName2() {
        return variableName2;
    }

    public ExpressionNode getValue2() {
        return value2;
    }

    public String getOperation() {
        return operation;
    }



    @Override
    public String getType() {
        return "Integer";
    }

    @Override
    public String toString() {
        return "VariableAssignment : void\n  |\n  +-- Variable(" + getVariableName() + ")\n  +-- " + getExpression() +
                "\nVariableAssignment : void\n  |\n  +-- Variable(" + getVariableName2() + ")\n  +-- " + getValue2() +
                "\n" + getClass().getSimpleName() + " (" + getOperation() + "): " + getType() + "\n  |\n  +-- Variable(" + getVariableName() + ")\n  +-- Variable(" + getVariableName2() + ")";
    }
}
