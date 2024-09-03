package project.compiler.nodes;

public class PrintVariableNode extends ExpressionNode {
    private final String variableName;
    private final ExpressionNode variableValue;

    public String getPrinter() {
        return printer;
    }

    private String printer;

    public PrintVariableNode(String variableName, ExpressionNode variableValue,String printer) {
        this.variableName = variableName;
        this.variableValue = variableValue;
        this.printer = printer;

    }
    public String getVariableName() {
        return variableName;
    }

    public ExpressionNode getVariableValue() {
        return variableValue;
    }
    @Override
    public String toString() {
        return "PrintVariableNode("+ printer +"):\n  |\n  +-- Variable(\"" + variableName + "\")\n  +-- " + variableValue.toString() + " : " + variableValue.getClass().getSimpleName();
    }

    @Override
    public String getType() {
        return null;
    }
}
