package project.compiler.nodes.extendedVarBinary;

import project.compiler.nodes.BinaryVarExtended;
import project.compiler.nodes.ExpressionNode;

public class DivVarExtended extends BinaryVarExtended {


    public DivVarExtended(String variableName, String variableName2, ExpressionNode expression, ExpressionNode value2, String operation) {
        super(variableName, variableName2, expression, value2, operation);
    }

    @Override
    public String getType() {
        return "Integer";
    }


}
