package project.compiler.nodes;

public class VariableNode extends ExpressionNode{


    private final String name;

    public VariableNode(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Variable(\"" + name + "\") : " + getType();
    }

    @Override
    public String getType() {
        return "String"; // as features are added will make an external type casting method
    }
}
