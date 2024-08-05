package project.compiler.nodes;

public class PrintVarNode extends ExpressionNode{
;
    private final ExpressionNode value;

    public PrintVarNode(ExpressionNode value) {
        this.value = value;
    }


    @Override
    public String getType() {
        return "String";
    }



    public ExpressionNode getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " : " + getType() + "\n  |\n  +-- Function( PRINT )\n  +-- Literal(" + value + ") : String";
    }

}
