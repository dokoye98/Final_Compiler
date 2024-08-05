package project.compiler.nodes;

public class PrintStatementNode extends Node {


    private final ExpressionNode expression;


    public PrintStatementNode(ExpressionNode expression) {
        this.expression = expression;
    }

    public ExpressionNode getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "PrintStatement : void\n  |\n  +-- " + expression;
    }
}
