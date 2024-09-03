package project.compiler.nodes.extendedVarBinary;

import project.compiler.nodes.BinaryVarExtended;
import project.compiler.nodes.ExpressionNode;

public class SubVarExtended extends BinaryVarExtended {


    public SubVarExtended(String variableName, String variableName2, ExpressionNode expression, ExpressionNode value2, String operation) {
        super(variableName, variableName2, expression, value2, operation);
    }

    @Override
    public String getType() {
        return "Integer";
    }



}
