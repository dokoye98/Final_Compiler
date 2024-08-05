package project.compiler.nodes;

public class LiteralNode extends ExpressionNode {
    private final String value;

    public LiteralNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String getType() {
        return "String";
    }

    @Override
    public String toString() {
        return "Literal(\"" + value + "\") : " + getType();
    }
}
