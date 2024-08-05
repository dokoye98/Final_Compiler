package project.compiler.nodes.extendedVarBinary;

import project.compiler.nodes.BinaryVarExtended;
import project.compiler.nodes.ExpressionNode;

public class MulVarExtended extends BinaryVarExtended {


    public MulVarExtended(String variableName, String variableName2, ExpressionNode expression, ExpressionNode value2, String operation) {
        super(variableName, variableName2, expression, value2, operation);
    }

    @Override
    public String getType() {
        return "Integer";
    }

    @Override
    public String toString() {
        return "VariableAssignment : void\n  |\n  +-- Variable(" + getVariableName() + ")\n  +-- " + getExpression() +
                "\nVariableAssignment : void\n  |\n  +-- Variable(" + getVariableName2() + ")\n  +-- " + getValue2() +
                "\n" + getClass().getSimpleName() + " (" + getOperation() + "): " + getType() + "\n  |\n  +-- Variable(" + getVariableName() + ")\n  +-- Variable(" + getVariableName2()+ ")";
    }

}
