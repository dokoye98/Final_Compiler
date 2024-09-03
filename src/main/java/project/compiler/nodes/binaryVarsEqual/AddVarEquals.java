package project.compiler.nodes.binaryVarsEqual;

import project.compiler.nodes.BinaryAssignNode;
import project.compiler.nodes.ExpressionNode;
import project.compiler.nodes.VariableAssignmentNode;

public class AddVarEquals extends BinaryAssignNode {

        public AddVarEquals(String operation, String variableName, VariableAssignmentNode variableOne, VariableAssignmentNode variableTwo) {
            super(operation, variableName, variableOne, variableTwo);
        }


}
